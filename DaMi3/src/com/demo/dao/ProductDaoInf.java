package com.demo.dao;

import java.util.List;
import com.demo.entity.Product;
import com.demo.entity.Psort;
import com.demo.util.Page;

/**
 * ��Ʒ�����ӿ�
 * @author Damon
 *
 */
public interface ProductDaoInf {

	public void delete(Integer number);//��������ɾ����¼
	public List<Product> findAll();       //��ѯ���м�¼
	public int findAllCount();//��ѯ���м�¼��
	public List<Product > findAll(Page page);//��ҳ��ѯ���е���Ʒ
	public List<Product> findByClass(Psort psort,Page page);
	public int findAllPsortCount(Psort psort);//��ѯ�����¼��
	public List<Product> findByName(String productame,Page page);//������Ʒ����ģ����ҳ��ѯ��Ʒ
	public Product findById(Integer number); //����������Ų�ѯ��¼
	public void insert(Product p); //��������Ʒ��Ϣ
	public void update(Product p);  //������Ʒ��Ϣ
	public int findByNameCount(String pname);//��ѯ���������¼��
	
	public Product selectproductid(int pd);
	
}
