package com.demo.dao;

import java.util.List;
import com.demo.entity.Psort;
import com.demo.util.Page;

/**
 * ��Ʒ��������ӿ�
 * @author Damon
 *
 */
public interface PsortDaoInf {

	public void save(Psort psort);//������Ʒ����
	public void update(Psort p);  //������Ʒ��Ϣ
	public List<Psort> findAll(Page page);//��ҳ��ѯ���е���Ʒ����
	public int findAllCount();//��ѯ���м�¼��
	public List<Psort> findAll();//��ѯ���е���Ʒ����
	public Psort findByID(int commodityClassID);
	
}
