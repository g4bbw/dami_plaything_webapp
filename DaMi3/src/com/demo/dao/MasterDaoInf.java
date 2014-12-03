package com.demo.dao;

import java.util.List;
import com.demo.entity.Master;

/**
 * �Թ���Ա��Ĳ����ӿ�
 * @author Damon
 *
 */
public interface MasterDaoInf {

	public void delete(int number);//��������ɾ����¼
	public List<Master> findAll();       //��ѯ���м�¼
	public Master findById(int number); //����������Ų�ѯ��¼
	public void insert(Master u); //�������û���Ϣ
	public void update(Master u);  //�����û���Ϣ
	public Master findByNamePwd(Master u); //��֤�û���Ϣ
	public boolean findByName(String name); //����û����Ƿ�ռ��
	public List<Master> select();
}
