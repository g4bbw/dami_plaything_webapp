package com.demo.dao;

import java.util.List;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import com.demo.entity.CartItem;


/**
 * ���ﳵ��ϸ������
 * @author Damon
 *
 */
@Repository("CartItemDaoImpl")
public class CartItemDaoImpl extends HibernateDaoSupport implements CartItemDaoInf {

	@Resource(name="sessionFactory")
	public void setSupperSessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	/**
	 * ����
	 */
	public void addcartitem(CartItem cartitem) {
		
		getHibernateTemplate().save(cartitem);

	}

	/**
	 * ������Ʒid��ѯ
	 */
	@SuppressWarnings("unchecked")
	public CartItem getuniquecart(int id) {
		
		String hql="from CartItem c where c.product.psid="+id;
		List<CartItem> carts = getHibernateTemplate().find(hql);
		
		
		return carts.iterator().next();
	}

	@Override
	public void delectCart(CartItem cartinfo) {
		
		getHibernateTemplate().delete(cartinfo);
		
	}

}
