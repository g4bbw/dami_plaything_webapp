package com.demo.dao;

import com.demo.entity.CartItem;

public interface CartItemDaoInf {
	
	/**
	 * ��ӹ��ﳵ��ϸ
	 * @param cartitem
	 */
	public void addcartitem(CartItem cartitem);
	
	/**
	 * ��ѯ
	 * @param id
	 * @return
	 */
	public CartItem getuniquecart(int id);
	
	/**
	 * ɾ��
	 * @param id
	 */
	public void delectCart(CartItem cartinfo);
	
	
}
