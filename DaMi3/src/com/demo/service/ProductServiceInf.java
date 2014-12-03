package com.demo.service;

import java.util.List;
import com.demo.entity.Product;
import com.demo.entity.Psort;
import com.demo.util.Page;
import com.demo.util.Result;

public interface ProductServiceInf {

	public void delete(Integer number);//��������ɾ����¼
	public List<Product> findAll();       //��ѯ���м�¼
	public Result find(Psort sort , Page page);       //���ݷ����ѯ��¼
	public Product findById(Integer number); //����������Ų�ѯ��¼
	public void insert(Product p); //��������Ʒ��Ϣ
	public void update(Product p);  //������Ʒ��Ϣ
	public Result findAllCommodity(Page page);
	public Result findByName(String productame, Page page) ;//������Ʒ����ģ����ҳ��ѯ��Ʒ
	public List<Psort> findAllPsort(); //��ѯ������Ʒ����
	public Psort findPsort(int pid);//���ݷ���id��ȡ�������
	public Product selectproductid(int pd);

	
}
