package com.demo.service;
import java.util.List;

import com.demo.entity.Loginuser;
import com.demo.entity.Product;
import com.demo.entity.Shopcart;

public interface ShopCartServiceInf {

	public void addShoppingCart(Shopcart cart);
	
	public List<Shopcart> getallCartsByuserid(int id);
	
	public void deleteShoppingcart(int spid);
	
	public Shopcart getuniqueSpByid(int id);
	
	public void modifyspStatus(Shopcart sp);
	
	public List<Shopcart> getallmy(int id);
	
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
}
