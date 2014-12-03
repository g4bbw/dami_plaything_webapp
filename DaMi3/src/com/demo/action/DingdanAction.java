package com.demo.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.demo.entity.Indent;
import com.demo.entity.Indentproduct;
import com.demo.entity.Loginuser;
import com.demo.entity.Master;
import com.demo.entity.Ordergoods;
import com.demo.entity.Product;
import com.demo.entity.Shopcart;
import com.demo.service.IndentServiceInf;
import com.demo.service.IndentproductServiceInf;
import com.demo.service.ShopCartServiceInf;
import com.demo.util.DingdanStatus;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

/**
 * �û���������
 * @author FeiFei
 *
 */
@Controller("DingdanAction")
public class DingdanAction implements Action {

	//ȷ������
	private String password ;
	private String indentid;//����id�����ڲ�ѯ��������
	private String status ; //������״̬��ѯ
	
	@Resource(name="ShopCartServiceImpl")
	private ShopCartServiceInf shopcartservice ;
	@Resource(name="IndentServiceImpl")
	private IndentServiceInf indentdservice;
	@Resource(name="IndentproductServiceImpl")
	private IndentproductServiceInf productservice;
	private Indent indent;
	private List<Product> list;
	
	public List<Product> getList() {
		return list;
	}
	public void setList(List<Product> list) {
		this.list = list;
	}
	public Indent getIndent() {
		return indent;
	}
	public void setIndent(Indent indent) {
		this.indent = indent;
	}
	public ShopCartServiceInf getShopcartservice() {
		return shopcartservice;
	}
	public void setShopcartservice(ShopCartServiceInf shopcartservice) {
		this.shopcartservice = shopcartservice;
	}
	public IndentServiceInf getIndentdservice() {
		return indentdservice;
	}
	public void setIndentdservice(IndentServiceInf indentdservice) {
		this.indentdservice = indentdservice;
	}
	public IndentproductServiceInf getProductservice() {
		return productservice;
	}
	public void setProductservice(IndentproductServiceInf productservice) {
		this.productservice = productservice;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIndentid() {
		return indentid;
	}
	public void setIndentid(String indentid) {
		this.indentid = indentid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * ����ȷ���ҵĶ���
	 */
	public String execute() throws Exception {
		
		Loginuser user = (Loginuser) ActionContext.getContext().getSession().get("u");
		
		if(user ==null){
			return "login";
		}	
		
		status = 5+"";//��ն�����ѯ״̬
		
		List<Indent> indents = indentdservice.findById(user.getUserid());
			
		List<Ordergoods> Order = new ArrayList<Ordergoods>();
		for(int i = 0 ; i < indents.size() ; i++){
			//System.out.println(indents.get(i).getAddressname());
			Ordergoods os = new Ordergoods();
			os.setIndent(indents.get(i));
			//System.out.println("����id��" + indents.get(i).getId());
			os.setIndentproduct(productservice.findByIndent(indents.get(i)));
			Order.add(os);
			
		}
		
		ActionContext.getContext().getSession().put("order", Order ); //�ѷ�װ�õĶ�������Ž�session
		//ActionContext.getContext().getSession().put("indents" , indents);
		
		return SUCCESS;
	}
	
	/**
	 * ���붩������
	 * @return
	 * @throws Exception
	 */
	public String indentInfo() throws Exception{
		
		Loginuser user = (Loginuser) ActionContext.getContext().getSession().get("u");
		Master admin = (Master)ActionContext.getContext().getSession().get("admin");
		
		if(user ==null && admin == null){
			return "login";
		}	
	
		Indent indent = indentdservice.findIndent(Integer.parseInt(indentid));
		
		//��װ��������
		Ordergoods os = new Ordergoods();
		os.setIndent(indent);
		os.setIndentproduct(productservice.findByIndent(indent));
		
		//��ֹ�����ӳټ���
		for(Indentproduct i : os.getIndentproduct())
		{
			i.getProduct().getPimg();			
		}
		
		ActionContext.getContext().getSession().put("os", os ); //�ѷ�װ�õĶ�������Ž�session
		
		return SUCCESS;
	}
	
	/**
	 * ����ȷ�϶���ҳ��
	 * @return
	 * @throws Exception
	 */
	public String addIndent() throws Exception{

		Loginuser user = (Loginuser) ActionContext.getContext().getSession().get("u");
		//System.out.println(user.getUsernickname());
		
		//����û�û�е�¼
		if(user == null){
			return "nologin";
		}
		
		//����û��Ѿ���¼�������ݿ�ȡ�����ﳵ
		List<Shopcart> shoppingcarts= shopcartservice.getallCartsByuserid(user.getUserid());
		
		if(shoppingcarts.size() == 0){
			return ERROR;
		}
		
		//����ܼ۸�
		int total=0;
		Iterator<Shopcart> cartite=shoppingcarts.iterator();
		
		while(cartite.hasNext()){
			
			Shopcart sp=cartite.next();
			int pcount=Integer.parseInt(sp.getCartItem().getPcount());
			int price=Integer.parseInt(sp.getCartItem().getProduct().getPrice());
			total=pcount*price+total;
		}
		
		//System.out.println("���ﳵ��Ʒ�ܼۣ�"+ total ) ;
		
		Indent i = new Indent();
		i.setLoginuser(user);
		i.setTotalprices(total+"");//���ﳵ�ܼ�
		i.setAddressname(indent.getAddressname());//����
		i.setAddressadd(indent.getAddressadd());//��ַ
		i.setZipcode(indent.getZipcode());//�ʱ�
		i.setTelnumber(indent.getTelnumber());//�绰
		
		Indent indent  = indentdservice.addIndent(i ,shoppingcarts);
		
		//��չ��ﳵ
		ActionContext.getContext().getSession().put("cart" , null);
		
		ActionContext.getContext().getSession().put("indent" , indent);
		
		return SUCCESS;
	}
	
	/*
	 * ȷ�϶���
	 */
	public String okIndent() throws Exception{
		
		Loginuser user = (Loginuser) ActionContext.getContext().getSession().get("u");
		
		//��֤����
		if(!user.getUserpsw().equals(password)){
			
			return NONE;
		}
		
		
		
		Indent i = (Indent)ActionContext.getContext().getSession().get("indent");
		i.setInfo(DingdanStatus.Pre);//�����ȷ�ϣ��ȴ��̼�ȷ�϶���
		
		indentdservice.updateIndent(i);
		
		//���
		ActionContext.getContext().getSession().put("indent" , null);
		
		return SUCCESS;
	}
	
	public String findByStatus() throws Exception {
		
		Loginuser user = (Loginuser) ActionContext.getContext().getSession().get("u");
		
		if(user ==null){
			return "login";
		}	
		
		List<Indent> indents =indentdservice.findByStatus(user.getUserid(), Integer.parseInt(status));
		
		List<Ordergoods> Order = new ArrayList<Ordergoods>();
		for(int i = 0 ; i < indents.size() ; i++){
			//System.out.println(indents.get(i).getAddressname());
			Ordergoods os = new Ordergoods();
			os.setIndent(indents.get(i));
			//System.out.println("����id��" + indents.get(i).getId());
			os.setIndentproduct(productservice.findByIndent(indents.get(i)));
			Order.add(os);
			
		}
		
		ActionContext.getContext().getSession().put("order", Order ); //�ѷ�װ�õĶ�������Ž�session
		return SUCCESS;
	}
	public String tongji(){
		list = productservice.tongji();
		return "sell";
	}

	public String calloff() throws Exception {
		Loginuser user = (Loginuser) ActionContext.getContext().getSession().get("u");		
		if(user ==null){
			return "login";
		}	
	
		Indent indent = indentdservice.findIndent(Integer.parseInt(indentid));
		indent.setInfo(DingdanStatus.Run); //ȡ������
		indentdservice.updateIndent(indent);
		return SUCCESS;
	}

}
