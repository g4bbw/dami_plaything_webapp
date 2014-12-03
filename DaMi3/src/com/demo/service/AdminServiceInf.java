package com.demo.service;

import java.util.List;

import com.demo.entity.Master;

/**
 * ����Աҵ��ӿ�
 * @author FeiFei
 *
 */
public interface AdminServiceInf {

	public void delete(int number);//��������ɾ����¼
	public List<Master> findAll();       //��ѯ���м�¼
	public Master findById(int number); //����������Ų�ѯ��¼
	public void insert(Master u); //�������û���Ϣ
	public void update(Master u);  //�����û���Ϣ
	public Master findByNamePwd(Master u); //��֤�û���Ϣ
	public boolean findByName(String name); //����û����Ƿ�ռ��
	public List<Master> select();
	
}
