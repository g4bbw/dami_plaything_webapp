package com.demo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.dao.ProDaoInf;
import com.demo.entity.Product;
import com.demo.entity.Psort;

@Service("ProServiceImpl")
public class ProServiceImpl implements ProServiceInf {
	@Resource(name="ProDaoImpl")
	private ProDaoInf dao;

	public ProDaoInf getDao() {
		return dao;
	}

	public void setDao(ProDaoInf dao) {
		this.dao = dao;
	}
	/**
	 * ������Ʒ��ID
	 * */
	@Override
	public Product findProductid(int psid) {
		// TODO Auto-generated method stub
		return dao.findProductid(psid);
	}
	/**
	 * �޸���Ʒ��״̬
	 */
	@Override
	public void updateProduct(Product p) {
		// TODO Auto-generated method stub
		dao.updateProduct(p);
	}
	/**
	 * ����ID������ص���Ʒ��Ϣ
	 */
	@Override
	public Product findProductId(Product p) {
		// TODO Auto-generated method stub
		return dao.findProductId(p);
	}
	/**
	 * �޸���Ʒ��Ϣ
	 */
	@Override
	public void updateProductmsg(Product p) {
		// TODO Auto-generated method stub
		dao.updateProductmsg(p);
	}

    /**
     * 
     * ����--��Ʒ��--����Ʒ���������--��Ʒ�����--
     * */
	@Override
	public List<Psort> queryPsort() {
		// TODO Auto-generated method stub
		return dao.queryPsort();
	}
}
