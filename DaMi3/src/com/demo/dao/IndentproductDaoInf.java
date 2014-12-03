package com.demo.dao;

import java.util.List;

import com.demo.entity.Indent;
import com.demo.entity.Indentproduct;
import com.demo.entity.Product;

/**
 * ������������ӿ�
 * @author FeiFei
 *
 */
public interface IndentproductDaoInf {

	/**
	 * ������������
	 * @param i
	 */
	public void addIndent(Indentproduct i);
	/**
	 * ɾ������
	 * @param id
	 */
	public void deleteIndent(int id);
	/**
	 * �޸Ķ�������
	 * @param i
	 */
	public void updateIndent(Indentproduct i);
	/**
	 * ���ݶ�����ѯ������ϸ
	 * @param indent
	 * @return
	 */
	public List<Indentproduct> findByIndent(Indent indent);
	
	public List<Product> tongji();
	public List<Indentproduct> tongjinumber();
}
