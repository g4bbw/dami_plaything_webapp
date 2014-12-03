package com.demo.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.stereotype.Controller;

import com.demo.entity.Indent;
import com.demo.entity.Ordergoods;
import com.demo.service.IndentServiceInf;
import com.demo.service.IndentproductServiceInf;
import com.demo.util.DingdanStatus;
import com.demo.util.Page;
import com.demo.util.Result;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

/**
 * ����Ա��������
 * @author FeiFei
 *
 */
@Controller("AdminOrderAction")
public class AdminOrderAction implements Action,ServletResponseAware {
	
	private javax.servlet.http.HttpServletResponse response;
	
	public void setServletResponse(HttpServletResponse response) {
		   this.response = response;
	}

	@Resource(name="IndentServiceImpl")
	private IndentServiceInf indentdservice;
	@Resource(name="IndentproductServiceImpl")
	private IndentproductServiceInf productservice;
	private int currentPage;			//��ǰҳ
	private Page page;
	private static final int MaxCurrent = 20 ;
	private int oid ;//�������
	private String status ; //������״̬��ѯ
	
	/**
	 * ���붩��ҳ��
	 */
	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
		
		Page newPage = new Page();				//���÷�ҳ��Ϣ
		newPage.setCurrentPage(1);	//���õ�ǰҳ
		newPage.setEveryPage(MaxCurrent);				//����ÿҳ��ʾ
		Result result = indentdservice.findIndentByPage(newPage);//��ȡ��ѯ���
		page = result.getPage();		//��ȡ��ҳ��Ϣ
		List<Indent> indents = result.getList();	//��ȡ��Ʒ��Ϣ�б�
		
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
	
	/**
	 * ����Ա����
	 * @return
	 * @throws Exception
	 */
	public String processing()  throws Exception {
		
		//System.out.println("����id:" + oid);
		Indent i  = new Indent();
		i = indentdservice.findIndent(oid);
		//System.out.println("����:" +  i.getAddressname());
		i.setInfo(DingdanStatus.After);//����
		indentdservice.updateIndent(i);
		return execute();
	}
	
	/**
	 * ����Աǩ��
	 * @return
	 * @throws Exception
	 */
	public String sign()  throws Exception {
		
		//System.out.println("����id:" + oid);
		Indent i  = new Indent();
		i = indentdservice.findIndent(oid);
		//System.out.println("����:" +  i.getAddressname());
		i.setInfo(DingdanStatus.OK);//����
		indentdservice.updateIndent(i);
		return execute();
	}
	
	/**
	 * ����Աǩ��
	 * @return
	 * @throws Exception
	 */
	public String off()  throws Exception {
		
		//System.out.println("����id:" + oid);
		Indent i  = new Indent();
		i = indentdservice.findIndent(oid);
		//System.out.println("����:" +  i.getAddressname());
		i.setInfo(DingdanStatus.End);//����
		indentdservice.updateIndent(i);
		return execute();
	}
	
	/**
	 * ����״̬��ѯ����
	 * @return
	 * @throws Exception
	 */
	public String findByStatus()  throws Exception {
		List<Indent> indents =indentdservice.findByStatus(Integer.parseInt(status));
		
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
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
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

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
