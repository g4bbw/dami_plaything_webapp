package com.demo.service;

import java.util.List;

import com.demo.entity.Loginuser;

/**
 * �û�����ҵ����ӿ�
 * @author Damon
 *
 */
public interface LoginUserServiceInf {
	public void delete(Integer number);//��������ɾ����¼
	public List<Loginuser> findAll();       //��ѯ���м�¼
	public Loginuser findById(Integer number); //����������Ų�ѯ��¼
	public void insert(Loginuser u); //�������û���Ϣ
	public void update(Loginuser u);  //�����û���Ϣ
	public Loginuser findByNamePwd(Loginuser u); //��֤�û���Ϣ
	public boolean findByName(String name); //����û����Ƿ�ռ��
}
