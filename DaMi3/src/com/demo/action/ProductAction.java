package com.demo.action;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;

import com.demo.entity.ImgsList;
import com.demo.entity.Product;
import com.demo.entity.Psort;
import com.demo.service.ProductServiceInf;
import com.demo.util.Page;
import com.demo.util.Result;
import com.opensymphony.xwork2.Action;

/**
 * ��Ʒ����
 * @author Damon
 *
 */
@Controller("ProductAction")
public class ProductAction implements Action {

	@Resource(name="ProductServiceImpl")
	private ProductServiceInf productservice;
	public ProductServiceInf getProductservice() {
		return productservice;
	}
	public void setProductservice(ProductServiceInf productservice) {
		this.productservice = productservice;
	}
	private int currentPage;			//��ǰҳ
	private Page page;
	private List<Product> ps; //��Ʒ����
	private String pname;
	private Product product; //һ����Ʒ����
	private List<String> pimg = new ArrayList<String>(); //һ����Ʒ�����ͼ����
	private List<ImgsList> imgs= new ArrayList<ImgsList>(); 
	private List<Product> rimg= new ArrayList<Product>(); 
	private Integer productid; //һ����Ʒid
	private List<Psort> psort ; //��Ʒ���༯��
	private int pid ;
	private static final int MaxCurrent = 20 ;
	
	
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public List<Product> getPs() {
		return ps;
	}
	public void setPs(List<Product> ps) {
		this.ps = ps;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getProductid() {
		return productid;
	}
	public void setProductid(Integer productid) {
		this.productid = productid;
	}
	public List<Psort> getPsort() {
		return psort;
	}
	public void setPsort(List<Psort> psort) {
		this.psort = psort;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}

	/**
	 * ��ʾ������Ʒ
	 * @return ��ҳ�淵����Ʒ����
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
		
		pid = 0 ; //��շ����ѯ���
		pname = null; //����������
		Page newPage = new Page();				//���÷�ҳ��Ϣ
		newPage.setCurrentPage(1);	//���õ�ǰҳ
		newPage.setEveryPage(MaxCurrent);				//����ÿҳ��ʾ
		Result result = productservice.findAllCommodity(newPage);//��ȡ��ѯ���
		page = result.getPage();		//��ȡ��ҳ��Ϣ
		ps = result.getList();	//��ȡ��Ʒ��Ϣ�б�
		psort = productservice.findAllPsort(); //��ȡ������Ʒ����
		
		return SUCCESS;
	}

	/**
	 * ��ҳ��ʾ��Ʒ
	 * @return ��ҳ�淵����Ʒ����
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String showProduct() throws Exception {
		
		Page newPage = new Page();				//���÷�ҳ��Ϣ
		newPage.setCurrentPage(currentPage);	//���õ�ǰҳ
		newPage.setEveryPage(MaxCurrent);				//����ÿҳ��ʾ
		Result result;
		
		//����н��з���Ҳû�н���������ѯ
		if(pid != 0 && pname==null){
			Psort pt = productservice.findPsort(pid);
			result = productservice.find(pt , newPage);//��ȡ��ѯ���
		}
		//���û�н���������ѯ
		else if(pname==null){
			result = productservice.findAllCommodity(newPage);//��ȡ��ѯ���
			
		}
		//����������ѯ
		else{
			result = productservice.findByName(pname,newPage);//��ȡ��ѯ���
		}
		page = result.getPage();		//��ȡ��ҳ��Ϣ
		ps = result.getList();	//��ȡ��Ʒ��Ϣ�б�
		
		return SUCCESS;
		
		
	}
	
	/**
	 * ģ����ѯ��Ʒ
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String findByName() throws Exception {
		
		Page newPage = new Page();				//���÷�ҳ��Ϣ
		newPage.setCurrentPage(1);	//���õ�ǰҳΪ��һҳ
		newPage.setEveryPage(MaxCurrent);				//����ÿҳ��ʾ
		Result result = productservice.findByName(pname,newPage);//��ȡ��ѯ���
		page = result.getPage();		//��ȡ��ҳ��Ϣ
		ps = result.getList();	//��ȡ��Ʒ��Ϣ�б�
		psort = productservice.findAllPsort(); //��ȡ������Ʒ����
		
		return SUCCESS;
		
		
	}
	
	/**
	 * ��ʾһ����Ʒ����ϸ��Ϣ
	 * @return ��ת����ϸҳ��
	 * @throws Exception
	 */
	public String showProductInfo() throws Exception{
		
		product = productservice.findById(productid);
		
		//System.out.println("��Ʒ��ͼ��"+product.getPimgbig());
		
		String[] str1 =  product.getPimgbig().split("#");
			
		pimg = new ArrayList<String>();
		//�ѵ�ַ�Ž�����
		for(int i = 1; i<str1.length; i++){						
			pimg.add(i-1, str1[i]);
		}
		rimg = new ArrayList<Product>(); 
		
		if(product.getPxx() != "/"){
			String[] str2= product.getPxx().split("#");
			for(int r = 1; r<str2.length; r++){
				/*System.out.println(productservice.findById(Integer.parseInt(str2[r])).getPname());*/
				rimg.add(productservice.findById(Integer.parseInt(str2[r])));						
			}
		}

		return SUCCESS;
	}
	
	/**
	 * ����ͼ��
	 * @return
	 * @throws Exception
	 */
	public String productGetImg() throws Exception{
		
		product = productservice.findById(productid);
				
		String[] str1 =  product.getPimgbig().split("#");
		String[] str2 =  product.getPimgsmall().split("#");
			
		imgs = new ArrayList<ImgsList>();
		//�ѵ�ַ�Ž�����
		for(int i = 1; i<str1.length; i++){						
			ImgsList is = new ImgsList();
			is.setBigimg(str1[i]);
			is.setSamllimg(str2[i]);
			imgs.add(is);
			
		}

		return SUCCESS;
		
	}
	
	/**
	 * ������Ʒ���ൽ�����ҳ
	 * @return ��ת����ϸҳ��
	 * @throws Exception
	 */
	public String showPeijianInfo() throws Exception{
		
		psort = productservice.findAllPsort(); //��ȡ������Ʒ����
		return SUCCESS;
	}
	
	/**
	 * ���ݷ����ѯ��Ʒ
	 * @return	�ɹ���תҳ��
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String findPsortProduct() throws Exception{
		pname = null; //����������
		Psort pt = productservice.findPsort(pid);//��ȡ�������
		
		Page newPage = new Page();				//���÷�ҳ��Ϣ
		newPage.setCurrentPage(1);	//���õ�ǰҳΪ��һҳ
		newPage.setEveryPage(MaxCurrent);				//����ÿҳ��ʾ
		Result result = productservice.find(pt , newPage);//��ȡ��ѯ���
		page = result.getPage();		//��ȡ��ҳ��Ϣ
		ps = result.getList();	//��ȡ��Ʒ��Ϣ�б�
		
		return SUCCESS;
	}
	public List<String> getPimg() {
		return pimg;
	}
	public void setPimg(List<String> pimg) {
		this.pimg = pimg;
	}
	public List<ImgsList> getImgs() {
		return imgs;
	}
	public void setImgs(List<ImgsList> imgs) {
		this.imgs = imgs;
	}
}
