package com.demo.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.demo.entity.Loginuser;
import com.demo.entity.Product;
import com.demo.entity.Shopcart;
import com.demo.util.ShoppingCartStatus;

/**
 * ���ﳵ������
 * @author Damon
 *
 */
@Repository("ShopCartDaoImpl")
public class ShopCartDaoImpl extends HibernateDaoSupport implements ShopCartDaoInf {

	@Resource(name="sessionFactory")
	public void setSupperSessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	
	/**
	 * ����
	 * @param shopcart
	 */
	public void addShoppingCart(Shopcart shopcart) {
		
		getHibernateTemplate().save(shopcart);

	}

	/**
	 * �����û�id��ѯ
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Shopcart> listallcartByUserid(int id) {
		
		String hql="from Shopcart s where s.loginuser.userid="+id+" and s.shoppingcartStatus="+ShoppingCartStatus.Pre;
		List<Shopcart> shoppingcarts = getHibernateTemplate().find(hql);		
		return shoppingcarts;
	}

	/**
	 * ��ѯ
	 * @param id
	 * @return
	 */
	public List<Shopcart> listall(int id) {

		
		return null;
	}

	/**
	 * ɾ��
	 * @param spid
	 */
	public void deleteShoppingCart(int spid) {
		
		Shopcart sp = getHibernateTemplate().get(Shopcart.class, spid);
		getHibernateTemplate().delete(sp);
		

	}

	/**
	 * ��ѯ
	 * @param id
	 * @return
	 */
	public Shopcart getuniqueSpByid(int id) {
		
		return getHibernateTemplate().get(Shopcart.class, id);
	}

	/**
	 * �޸Ĺ��ﳵ״̬
	 * @param sp
	 */
	public void modifyspStatus(Shopcart sp) {
		
		sp.setShoppingcartStatus(ShoppingCartStatus.After);
		getHibernateTemplate().update(sp);
		
	}


	/**
	 * ��ѯ���ﳵ�Ƿ��Ѿ���������Ʒ
	 * @param u	�û�
	 * @param p ��Ʒ
	 * @return
	 */
	public Shopcart findCartProduct(Loginuser u, Product p) {
		
		Shopcart sp = null ;
		String hql="from Shopcart s where s.loginuser.userid="+u.getUserid()+" and s.cartItem.product.psid= "+p.getPsid()+" and s.shoppingcartStatus="+ShoppingCartStatus.Pre;
		Query query=getSession().createQuery(hql);
		sp = (Shopcart)query.uniqueResult();
		
		return sp;
	}


	/**
	 * �޸Ĺ��ﳵ�������Ʒ������
	 */
	public void setNum(Shopcart sc , int num) {
		
		int i = Integer.parseInt(sc.getCartItem().getPcount()) + num;
		
		sc.getCartItem().setPcount(i + "");
		getHibernateTemplate().update(sc);
		
	}


	/**
	 * ��չ��ﳵ
	 */
	public void deleteAll(List<Shopcart> sp) {
		getHibernateTemplate().deleteAll(sp);
		
	}

}
