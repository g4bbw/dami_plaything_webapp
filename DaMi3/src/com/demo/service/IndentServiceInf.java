 package com.demo.service;

import java.util.List;

import com.demo.entity.Indent;
import com.demo.entity.Indentproduct;
import com.demo.entity.Shopcart;
import com.demo.util.Page;
import com.demo.util.Result;

/**
 * ����ҵ��ӿ���
 * @author FeiFei
 *
 */
public interface IndentServiceInf {


		/**
		 * �����û�id��ѯ���ж���
		 * @return
		 */
		public List<Indent> findById(int id);
		/**
		 * ��������
		 * @param i
		 */
		public Indent addIndent(Indent i ,List<Shopcart> sp);
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
		 * ���ݶ���״̬��ѯ
		 * @param id �û�id
		 * @param Status ����״̬
		 * @return
		 */
		public List<Indent> findByStatus(int id , int Status);
		
		/**
		 * ���ݶ���״̬��ѯ
		 * @param Status
		 * @return
		 */
		public List<Indent> findByStatus(int Status);
		/**
		 * ��ѯ������ϸ
		 * @param indent ����
		 * @return
		 */
		public List<Indentproduct> findIndentproduct(Indent indent);
		
		/**
		 * ���ݶ���id��ѯ����
		 * @param id
		 * @return
		 */
		public Indent findIndent(int id);
		
		/**
		 * ��ҳ��ѯ���ж���
		 * @param page
		 * @return
		 */
		public Result findIndentByPage(Page page);
		
	
	}
