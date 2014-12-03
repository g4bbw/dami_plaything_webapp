package com.demo.dao;

import java.util.List;
import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import com.demo.entity.Product;
import com.demo.entity.Psort;
import com.demo.util.Page;

/**
 * ��Ʒ������
 * @author Damon
 *
 */
@Repository("ProductDaoImpl")
public class ProductDaoImpl extends HibernateDaoSupport implements ProductDaoInf {

	@Resource(name="sessionFactory")
	public void setSupperSessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	/**
	 * ��������ɾ����¼
	 */
	public void delete(Integer number) {

		Product product = (Product)getHibernateTemplate().load(Product.class, number);
		getHibernateTemplate().delete(product);

	}

	/**
	 * ��ѯ���м�¼
	 */
	@SuppressWarnings("unchecked")
	public List<Product> findAll() {

		return getHibernateTemplate().find("from Product");
	}

	/**
	 * ����������Ų�ѯ��¼
	 */
	public Product findById(Integer number) {
		
		Product product = (Product)getHibernateTemplate().get(Product.class, number);
		
		return product;
	}

	/**
	 * ��������Ʒ��Ϣ
	 */
	public void insert(Product p) {
		
		getHibernateTemplate().save(p);
		
	}

	/**
	 * ������Ʒ��Ϣ
	 */
	public void update(Product p) {
		
		getHibernateTemplate().update(p);

	}
	
	/**
	 * ���ؼ�¼��
	 */
	public int findAllCount() {

		return getHibernateTemplate().find("from Product").size();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Product> findAll(Page page) {
		Query query = this.getSession().createQuery("from Product");
		query.setFirstResult(page.getBeginIndex());	//���ò�ѯ���λ��
		query.setMaxResults(page.getEveryPage());	//���ò�ѯ���ֵ
		return query.list();		//���ز�ѯ���
	}
	
	/**
	 * ������Ʒ�����ѯ���(��ҳ)
	 * @param ��Ʒ���࣬��ҳ
	 * @return ��ѯ�������
	 */
	@SuppressWarnings("unchecked")
	public List<Product> findByClass(Psort psort, Page page) {
		
		Query query = this.getSession().createQuery("from Product where psort =:psort");
		query.setEntity("psort", psort);
		query.setFirstResult(page.getBeginIndex());	//���ò�ѯ���λ��
		query.setMaxResults(page.getEveryPage());	//���ò�ѯ���ֵ
		return query.list();		//���ز�ѯ���
	}
	
	
	
	/**
	 * ������Ʒ����ģ����ҳ��ѯ��Ʒ
	 * @param productame ��ѯ�ַ�
	 * @param page ��ҳ
	 * @return ��ѯ��Ʒ���List����
	 */
	@SuppressWarnings("unchecked")
	public List<Product> findByName(String productame, Page page) {
		
		String hql="from Product as p where p.pname like :pname"; //�������Ʋ�ѯ 
		Query query = this.getSession().createQuery(hql);
		query.setString("pname", "%"+productame+"%"); 
		query.setFirstResult(page.getBeginIndex());	//���ò�ѯ���λ��
		query.setMaxResults(page.getEveryPage());	//���ò�ѯ���ֵ
		return query.list();
	}
	
	/**
	 * ��ѯ���������¼��
	 * @param pname �����ֶ�
	 * @return ���ؼ�¼����
	 */
	public int findByNameCount(String pname) {
		String hql="from Product as p where p.pname like :pname"; //�������Ʋ�ѯ 
		Query query = this.getSession().createQuery(hql);
		query.setString("pname", "%"+pname+"%"); 
		return query.list().size();
	}
	
	
	public int findAllPsortCount(Psort psort) {
		
		Query query = this.getSession().createQuery("from Product where psort =:psort");
		query.setEntity("psort", psort);
		return query.list().size();		//���ز�ѯ���
	}
	public Product selectproductid(int pd) {
		Criteria ct = getSession().createCriteria(Product.class)
								.add(Restrictions.eq("psid", pd));
		return (Product)ct.uniqueResult();
	}
	
}
