package com.demo.service;

import java.util.List;

import com.demo.entity.Indent;
import com.demo.entity.Loginuser;
import com.demo.entity.Page;

public interface SelectindentServiceInf {

	public List<Indent> find(Page page,Indent indent);//��ѯȫ��������Ϣ
	
	public long count(Indent indent);
	
	public List<Indent> finds(Page page,Indent indent,int info);//��������ѯ������Ϣ
	
	public long counts(Indent indent,int info);
	
	public List<Indent> findss(Page page,Indent indent,int info,String totalprices);//������������ѯ������Ϣ
	
	public long countss(Indent indent,int info,String totalprices);
	
	public List<Indent> findall(Page page,Indent indent,String totalprices);//������������ѯȫ��������Ϣ
	
	public long countall(Indent indent,String totalprices);
}
