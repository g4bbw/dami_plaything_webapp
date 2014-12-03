package com.demo.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import com.demo.entity.Indent;
import com.demo.util.Page;


/**
 * ��������ʵ����
 * @author FeiFei
 *
 */
@Repository("IndentDaoImpl")
public class IndentDaoImpl extends HibernateDaoSupport implements IndentDaoInf {

	@Resource(name="sessionFactory")
	public void setSupperSessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	
	/**
	 * �����û�id��ѯ����
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Indent> findById(int id) {
		//�����û�id����ʱ�併���ѯ������
		String hql="from Indent i where i.loginuser.userid="+id +"order by i.time desc";
		List<Indent> indents = getHibernateTemplate().find(hql);		
		
		return indents;
	}

	/**
	 * ��������
	 * @param i
	 */
	public Indent addIndent(Indent i) {
		System.out.println("---------"+i.getInfo());
		int id =   (Integer) getHibernateTemplate().save(i);
		
		Indent indent = getHibernateTemplate().get(Indent.class, id);
		return indent;

	}

	/**
	 * ɾ�����ݿ�
	 * @param id
	 */
	public void deleteIndent(int id) {
		Indent i = getHibernateTemplate().get(Indent.class, id);
		getHibernateTemplate().delete(i);

	}

	/**
	 * �޸Ķ���
	 * @param i
	 */
	public void updateIndent(Indent i) {
		getHibernateTemplate().update(i);

	}


	/**
	 * ���ݶ���״̬��ѯ
	 * @param id �û�id
	 * @param Status ����״̬
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Indent> findByStatus(int id, int Status) {
			
		String hql="from Indent i where i.loginuser.userid="+id +"and i.info=" + Status+"order by i.time desc";
		List<Indent> indents = getHibernateTemplate().find(hql);		
		
		return indents;
	}


	/**
	 * ���ݶ���id��ѯ����
	 * @param id
	 * @return 
	 */
	public Indent findIndent(int id) {
		
	 	Indent i = (Indent)getHibernateTemplate().get(Indent.class, id);
		return i;
	}


	/**
	 * ��ҳ��ѯ���ж���
	 */
	@SuppressWarnings("unchecked")
	public List<Indent> findAll(Page page) {
		Query query = this.getSession().createQuery("from Indent i order by i.time desc");
		query.setFirstResult(page.getBeginIndex());	//���ò�ѯ���λ��
		query.setMaxResults(page.getEveryPage());	//���ò�ѯ���ֵ
		return query.list();	
	}


	/**
	 * �������ж�����¼��
	 */
	public int findAllCount() {
		
		return getHibernateTemplate().find("from Indent").size();
	}


	@SuppressWarnings("unchecked")
	public List<Indent> findByStatus(int Status) {
		String hql="from Indent i where i.info=" + Status+"order by i.time desc";
		List<Indent> indents = getHibernateTemplate().find(hql);		
		
		return indents;
	}

}
