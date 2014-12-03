package com.demo.dao;

import java.util.List;

import com.demo.entity.Indent;
import com.demo.util.Page;

/**
 * ���������ӿ�
 * @author FeiFei
 *
 */
public interface IndentDaoInf {

	/**
	 * ��ҳ��ѯ���ж���
	 * @return
	 */
	public List<Indent> findAll(Page page);
	/**
	 * ���ض�����¼��
	 */
	public int findAllCount() ;
	/**
	 * �����û�id��ѯ���ж���
	 * @return
	 */
	public List<Indent> findById(int id);
	/**
	 * ���ݶ���״̬��ѯ
	 * @param id �û�id
	 * @param Status ����״̬
	 * @return
	 */
	public List<Indent> findByStatus(int id , int Status);
	public List<Indent> findByStatus(int Status);
	/**
	 * ��������
	 * @param i
	 */
	public Indent addIndent(Indent i);
	/**
	 * ɾ�����ݿ�
	 * @param id
	 */
	public void deleteIndent(int id);
	/**
	 * �޸Ķ���
	 * @param i
	 */
	public void updateIndent(Indent i);
	
	/**
	 * ���ݶ���id��ѯ����
	 * @param id
	 * @return
	 */
	public Indent findIndent(int id);
	
}
