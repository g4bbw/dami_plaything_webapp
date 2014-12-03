package com.demo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.dao.ProductDaoInf;
import com.demo.dao.PsortDaoInf;
import com.demo.entity.Phone;
import com.demo.entity.Product;
import com.demo.entity.Psort;
import com.demo.util.Page;
import com.demo.util.PageUtil;
import com.demo.util.ProductStatus;
import com.demo.util.Result;

/**
 * ��Ʒҵ��ʵ����
 * @author Damon
 */
@Service("ProductServiceImpl")
public class ProductServiceImpl implements ProductServiceInf {

	@Resource(name="ProductDaoImpl")
	public ProductDaoInf productdao ;
	
	@Resource(name="PsortDaoImpl")
	public PsortDaoInf psortdao;
	
	public ProductDaoInf getProductdao() {
		return productdao;
	}
	public void setProductdao(ProductDaoInf productdao) {
		this.productdao = productdao;
	}
	public PsortDaoInf getPsortdao() {
		return psortdao;
	}
	public void setPsortdao(PsortDaoInf psortdao) {
		this.psortdao = psortdao;
	}
	
	/**
	 * ��������ɾ����¼
	 * @param Ҫɾ������Ʒ������ֵ
	 */
	public void delete(Integer number) {
		
		productdao.delete(number);

	}

	/**
	 * ��ѯ���м�¼
	 * @return ����������Ʒ��list����
	 */
	public List<Product> findAll() {
		
		return productdao.findAll();
	}

	/**
	 * ���ݷ����ѯ��¼
	 * @param ��������
	 * @return ��������ѯ�ķ������ƷList����
	 */
	public Result find(Psort sortid ,  Page page) {
		page = PageUtil.createPage(page, 
				productdao.findAllPsortCount(sortid));	//������ҳ����
			List<Product> commodityes
				= productdao.findByClass(sortid , page);	//ִ�в�ѯ
		Result result = new Result();	//�����������
		result.setPage(page);			//���÷�ҳ��Ϣ
		result.setList(commodityes);//������Ʒ�����б�
		return result;
	}

	/**
	 * ����������Ų�ѯ��¼
	 * @param Ҫ��ѯ��Ʒ��������
	 * @return ���ز�ѯ������Ʒ����
	 */
	public Product findById(Integer number) {
		
		return productdao.findById(number);
	}

	/**
	 * ��������Ʒ��Ϣ
	 * @param ����Ʒ����
	 */
	public void insert(Product p) {
		
		Phone phone = new Phone();
		phone.setId(2);
		phone.setSort(1);
		p.setPhone(phone);
		p.setPsort(psortdao.findByID(p.getPsort().getId()));
		p.setPstatus(ProductStatus.ON+""); //������Ʒ״̬
		p.setPstock(999);
		p.setPtotal(999);
		
        System.out.println("1"+p.getPimg());
        System.out.println("2"+p.getPimgbig());
        System.out.println("3"+p.getPimgmiddle());
        System.out.println("4"+p.getPimgsmall());
        System.out.println("5"+p.getPinfo());
        System.out.println("6"+p.getPmessage());
        System.out.println("7"+p.getPmoblie());
        System.out.println("8"+p.getPname());
        System.out.println("9"+p.getPnameid());
        System.out.println("10"+p.getPnorms());
        System.out.println("11"+p.getPrice());
        System.out.println("12"+p.getPselect());
        System.out.println("13"+p.getPstatus());
        System.out.println("14"+p.getPxx());
        System.out.println("15"+p.getPsid());
        System.out.println("16"+p.getPstock());
        System.out.println("stre"+p.getPtotal());
        System.out.println("17"+p.getPhone().getId());
        System.out.println("18"+p.getPhone().getSort());
        System.out.println("19"+p.getPsort().getSort());
		
		productdao.insert(p);

	}

	/**
	 * �����û���Ϣ
	 * @param Ҫ������Ʒ����
	 */
	public void update(Product p) {
	
		productdao.update(p);
		
	}
	
	public Result findAllCommodity(Page page) {
		
		page = PageUtil.createPage(page, 
				productdao.findAllCount());	//������ҳ����
		List<Product> commodityes
				= productdao.findAll(page);	//ִ�в�ѯ
		Result result = new Result();	//�����������
		result.setPage(page);			//���÷�ҳ��Ϣ
		result.setList(commodityes);//������Ʒ�����б�
		return result;
		
	}
	
	/**
	 * ������Ʒ����ģ����ҳ��ѯ��Ʒ
	 * @param productame ��ѯ�ַ�
	 * @param page ��ҳ
	 * @return ��ѯ��Ʒ���List����
	 */
	public Result findByName(String productame, Page page) {
		page = PageUtil.createPage(page, 
				productdao.findByNameCount(productame));	//������ҳ����
		List<Product> commodityes
				= productdao.findByName(productame , page);	//ִ�в�ѯ
		Result result = new Result();	//�����������
		result.setPage(page);			//���÷�ҳ��Ϣ
		result.setList(commodityes);//������Ʒ�����б�
		return result;
	}
	
	
	/**
	 * ��ѯ�����е���Ʒ����
	 * @return ��Ʒ���༯��
	 */
	public List<Psort> findAllPsort() {

		return psortdao.findAll();
	}
	
	/**
	 * ���ݷ���id��ȡ�������
	 * @param pid ����id
	 * @return �������
	 */
	public Psort findPsort(int pid) {

		Psort psort = psortdao. findByID(pid);
		
		//��ȡһ��psort����ֵ�����������Ա����ӳټ����������Ĵ���
		//����could not initialize proxy - no Session
		psort.getSort();
		
		return psort;
	}
	public Product selectproductid(int pd) {
		return productdao.selectproductid(pd);
	}

	
	



}
