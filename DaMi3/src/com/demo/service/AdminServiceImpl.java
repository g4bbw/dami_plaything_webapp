package com.demo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.demo.dao.MasterDaoInf;
import com.demo.entity.Master;

/**
 * ����Աҵ��ʵ����
 * @author FeiFei
 *
 */
@Service("AdminServiceImpl")
public class AdminServiceImpl implements AdminServiceInf {

	@Resource(name="MasterDaoImpl")
	private MasterDaoInf admindao;
	public MasterDaoInf getAdmindao() {
		return admindao;
	}
	public void setAdmindao(MasterDaoInf admindao) {
		this.admindao = admindao;
	}
	
	/**
	 * ��������ɾ����¼
	 */
	public void delete(int number) {
		admindao.delete(number);

	}

	/**
	 * ��ѯ���м�¼
	 */
	public List<Master> findAll() {
		
		return admindao.findAll();
	}

	/**
	 * ����������Ų�ѯ��¼
	 */
	public Master findById(int number) {
		
		return admindao.findById(number);
	}

	/**
	 * �������û���Ϣ
	 */
	public void insert(Master u) {
		admindao.insert(u);

	}

	/**
	 * �����û���Ϣ
	 */
	public void update(Master u) {
		admindao.update(u);

	}

	/**
	 * ��֤�û���Ϣ
	 */
	public Master findByNamePwd(Master u) {
		
		return admindao.findByNamePwd(u);
	}

	/**
	 * ����û����Ƿ�ռ��
	 */
	public boolean findByName(String name) {
		
		return admindao.findByName(name);
	}

	public List<Master> select() {
		return admindao.select();
	}

}
