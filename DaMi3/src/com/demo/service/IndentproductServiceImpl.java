package com.demo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.dao.IndentproductDaoInf;
import com.demo.entity.Indent;
import com.demo.entity.Indentproduct;
import com.demo.entity.Product;

/**
 * ������ϸҵ��ʵ����
 * @author FeiFei
 *
 */
@Service("IndentproductServiceImpl")
public class IndentproductServiceImpl implements IndentproductServiceInf {

	@Resource(name="IndentproductDaoImpl")
	private IndentproductDaoInf indentproductdao;
	public IndentproductDaoInf getIndentproductdao() {
		return indentproductdao;
	}
	public void setIndentproductdao(IndentproductDaoInf indentproductdao) {
		this.indentproductdao = indentproductdao;
	}
	/**
	 * ������������
	 * @param i
	 */
	public void addIndent(Indentproduct i) {
		indentproductdao.addIndent(i);

	}

	/**
	 * ɾ������
	 * @param id
	 */
	public void deleteIndent(int id) {
		indentproductdao.deleteIndent(id);

	}

	/**
	 * �޸Ķ�������
	 * @param i
	 */
	public void updateIndent(Indentproduct i) {
		indentproductdao.updateIndent(i);

	}
	
	/**
	 * ���ݶ�����ѯ������ϸ
	 * @param i ����
	 * @return 
	 */
	public List<Indentproduct> findByIndent(Indent i) {
		return indentproductdao.findByIndent(i);
		
	}
	public List<Product> tongji() {
		return indentproductdao.tongji();
	}
	public List<Indentproduct> tongjinumber() {
		return indentproductdao.tongjinumber();
	}



}
