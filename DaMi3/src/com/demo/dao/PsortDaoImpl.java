package com.demo.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import com.demo.entity.Psort;
import com.demo.util.Page;

/**
 * ��Ʒ�������ʵ����
 * @author Damon
 *
 */
@Repository("PsortDaoImpl")
public class PsortDaoImpl extends HibernateDaoSupport implements PsortDaoInf {

	@Resource(name="sessionFactory")
	public void setSupperSessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	/**
	 * �����·���
	 */
	public void save(Psort psort) {
		
		getHibernateTemplate().save(psort);

	}
	
	/**
	 * ������Ʒ����
	 */
	public void update(Psort p) {
	
		getHibernateTemplate().update(p);
		
	}

	
	@SuppressWarnings("unchecked")
	public List<Psort> findAll(Page page) {
		Query query = this.getSession().createQuery("from Psort");
		query.setFirstResult(page.getBeginIndex());	//���ò�ѯ���λ��
		query.setMaxResults(page.getEveryPage());	//���ò�ѯ���ֵ
		return query.list();		//���ز�ѯ���
	}

	/**
	 * ���ؼ�¼��
	 */
	public int findAllCount() {
		
		return getHibernateTemplate().find("from Psort").size();
	}

	/**
	 *��ѯ���е���Ʒ����
	 */
	@SuppressWarnings("unchecked")
	public List<Psort> findAll() {
	
		return getHibernateTemplate().find("from Psort");
	}

	/**
	 *����id��ѯ
	 */
	public Psort findByID(int commodityClassID) {
		
		return  this.getHibernateTemplate().load(Psort.class, commodityClassID);
		
	}

	

}
