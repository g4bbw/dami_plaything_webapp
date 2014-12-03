package com.demo.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.demo.entity.Loginuser;

/**
 * �û�����ʵ����
 * @author Damon
 *
 */
@Repository("LoginUserDaoImpl")
public class LoginUserDaoImpl extends HibernateDaoSupport implements LoginUserDaoInf {

	@Resource(name="sessionFactory")
	public void setSupperSessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	//��������ɾ����¼
	@Override
	public void delete(Integer number) {
		
		Loginuser user = (Loginuser)getHibernateTemplate().load(Loginuser.class, number);
		getHibernateTemplate().delete(user);

	}

	 //��ѯ���м�¼
	@SuppressWarnings("unchecked")
	public List<Loginuser> findAll() {
		
		return getHibernateTemplate().find("from Loginuser");
	}

	//����������Ų�ѯ��¼
	public Loginuser findById(Integer number) {
		
		Loginuser user = (Loginuser)getHibernateTemplate().load(Loginuser.class, number);
		return user;
	}

	//�������û���Ϣ
	public void insert(Loginuser u) {
		
		getHibernateTemplate().save(u);

	}

	//�����û���Ϣ
	public void update(Loginuser u) {
		
		getHibernateTemplate().update(u);

	}

	 //��֤�û���Ϣ
	public Loginuser findByNamePwd(Loginuser u) {

		Loginuser user ;
		String hql="from Loginuser where username='"+u.getUsername()+"' and userpsw = '"+u.getUserpsw()+"'";
		Query query=getSession().createQuery(hql);
		user = (Loginuser)query.uniqueResult();
		
		//List<Loginuser> list = getHibernateTemplate().find("from Loginuser where username='"+u.getUsername()+"' and userpsw = '"+u.getUserpsw()+"'");
		
		return user;
	}

	//����û����Ƿ�ռ��
	@SuppressWarnings("unchecked")
	public boolean findByName(String name) {
		List<Loginuser> list = getHibernateTemplate().find("from Loginuser where username='"+name+"'");
		
		if(list.isEmpty()){
			return true;
		}
		else{
			return false;
		}
	}
}
