package com.demo.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.demo.entity.Product;
import com.demo.entity.Psort;

@Repository("ProDaoImpl")
public class ProDaoImpl extends HibernateDaoSupport implements ProDaoInf {
	
	@Resource(name="sessionFactory")
	public void setSupperSessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}

	/**
	 * ������Ʒ��ID
	 * */
	@Override
	public Product findProductid(int psid) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(Product.class, psid);
	}
	/**
	 * �޸���Ʒ��״̬
	 */
	@Override
	public void updateProduct(Product p) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(p);
	}
	/**
	 * ����ID������ص���Ʒ��Ϣ
	 */
	@Override
	public Product findProductId(Product p) {
		// TODO Auto-generated method stub
		String hql="from Product p where p.psid=?";
		Query query=getSession().createQuery(hql);
		query.setInteger(0, p.getPsid());
		return (Product)query.uniqueResult();
	}
	/**
	 * �޸���Ʒ��Ϣ
	 */
	@Override
	public void updateProductmsg(Product p) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(p);
	}
     /**
      * 
      * ����--��Ʒ��--����Ʒ���������--��Ʒ�����--
      * */
	@Override
	public List<Psort> queryPsort() {
		// TODO Auto-generated method stub
		String hql="from Psort";
		Query query=getSession().createQuery(hql);
		return query.list();
	}

}
