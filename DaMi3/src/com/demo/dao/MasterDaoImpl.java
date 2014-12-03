package com.demo.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import com.demo.entity.Master;

/**
 * ����Ա����ʵ����
 * @author FeiFei
 *
 */
@Repository("MasterDaoImpl")
public class MasterDaoImpl extends HibernateDaoSupport implements MasterDaoInf {

	@Resource(name="sessionFactory")
	public void setSupperSessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	/**
	 *��������ɾ����¼ 
	 */
	public void delete(int number) {
		Master user = (Master)getHibernateTemplate().load(Master.class, number);
		getHibernateTemplate().delete(user);

	}

	/**
	 * ��ѯ���м�¼
	 */
	@SuppressWarnings("unchecked")
	public List<Master> findAll() {
		return getHibernateTemplate().find("from Master");
	}

	/**
	 * ����������Ų�ѯ��¼
	 */
	public Master findById(int number) {
		Master user = (Master)getHibernateTemplate().load(Master.class, number);
		return user;
	}

	/**
	 * �������û���Ϣ
	 */
	public void insert(Master u) {
		getHibernateTemplate().save(u);


	}

	/**
	 * �����û���Ϣ
	 */
	public void update(Master u) {
		
		getHibernateTemplate().update(u);
	}

	/**
	 * ��֤�û���Ϣ
	 */
	public Master findByNamePwd(Master u) {
		Master user ;
		String hql="from Master where mastername='"+u.getMastername()+"' and masterpsw = '"+u.getMasterpsw()+"'";
		Query query=getSession().createQuery(hql);
		user = (Master)query.uniqueResult();
		
		
		return user;
	}

	/**
	 * ����û����Ƿ�ռ��
	 */
	@SuppressWarnings("unchecked")
	public boolean findByName(String name) {
		List<Master> list = getHibernateTemplate().find("from Master where mastername='"+name+"'");
		
		if(list.isEmpty()){
			return true;
		}
		else{
			return false;
		}
	}
	
	public List<Master> select() {
		Criteria ct = getSession().createCriteria(Master.class);
		return ct.list();
	}

}
