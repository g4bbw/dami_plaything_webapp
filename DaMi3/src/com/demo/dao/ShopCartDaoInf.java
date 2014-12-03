package com.demo.dao;

import java.util.List;

import com.demo.entity.Loginuser;
import com.demo.entity.Product;
import com.demo.entity.Shopcart;

public interface ShopCartDaoInf {
	
	/**
	 * ����
	 * @param shopcart
	 */
	public void addShoppingCart(Shopcart shopcart);
	
	/**
	 * �����û�id��ѯ
	 * @param id
	 * @return
	 */
	public List<Shopcart> listallcartByUserid(int id);
	
	/**
	 * ��ѯ
	 * @param id
	 * @return
	 */
	public List<Shopcart> listall(int id);
	
	/**
	 * ɾ��
	 * @param spid
	 */
	public void deleteShoppingCart(int spid);
	
	/**
	 * ��ѯ
	 * @param id
	 * @return
	 */
	public Shopcart getuniqueSpByid(int id);
	
	/**
	 * �޸Ĺ��ﳵ״̬
	 * @param sp
	 */
	public void modifyspStatus(Shopcart sp);
	
	/**
	 * ��ѯ���ﳵ�Ƿ��Ѿ���������Ʒ
	 * @param u	�û�
	 * @param p ��Ʒ
	 * @return
	 */
	public Shopcart findCartProduct(Loginuser u , Product p);
	
	/**
	 * �޸Ĺ��ﳵ�������Ʒ������
	 */
	public void setNum(Shopcart sc ,int num);
	
	/**
	 * ��չ��ﳵ
	 */
	public void deleteAll(List<Shopcart> sp);
}
