package com.demo.dao;

import java.util.List;

import com.demo.entity.Loginuser;
import com.demo.entity.Page;

public interface SelectuserDaoInf {

	public List<Loginuser> find(Page page,Loginuser loginuser);//��ѯ�ͻ���Ϣ
	
	public long count(Loginuser loginuser);
	
	public List<Loginuser> selectuser(String name);//��������ģ����ѯ
}
