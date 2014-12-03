package com.demo.service;

import java.util.List;

import com.demo.entity.Indent;
import com.demo.entity.Indentproduct;
import com.demo.entity.Product;

/**
 * ������ϸҵ��ӿ���
 * @author FeiFei
 *
 */
public interface IndentproductServiceInf {

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
	 * @param i
	 * @return 
	 */
	public List<Indentproduct> findByIndent(Indent i);
	
	public List<Product> tongji();
	public List<Indentproduct> tongjinumber();
}
