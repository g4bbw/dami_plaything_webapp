package com.demo.service;

import java.util.List;

import com.demo.entity.Product;
import com.demo.entity.Psort;

public interface ProServiceInf {
	public Product findProductid(int psid);//������ƷID
	public void updateProduct(Product p);//�޸���Ʒ���¼ܵ�״̬
	//����ID������ص���Ʒ��Ϣ
	public Product findProductId(Product p);
	//�޸���Ʒ��Ϣ
	public void updateProductmsg(Product p);
	//����--��Ʒ��--����Ʒ���������--��Ʒ�����--
	public List<Psort> queryPsort();
}
