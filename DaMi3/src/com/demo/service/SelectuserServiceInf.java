package com.demo.service;

import java.util.List;

import com.demo.entity.Loginuser;
import com.demo.entity.Page;

public interface SelectuserServiceInf {

	public List<Loginuser> find(Page page,Loginuser loginuser);//��ѯ�ͻ���Ϣ
	
	public long count(Loginuser loginuser);
	
	public List<Loginuser> selectuser(String name);//��������ģ����ѯ
}
