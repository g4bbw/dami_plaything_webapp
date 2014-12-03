package com.demo.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.demo.dao.LoginUserDaoInf;
import com.demo.entity.Loginuser;

@Service("LoginUserServiceImpl")
public class LoginUserServiceImpl implements LoginUserServiceInf {

	@Resource(name="LoginUserDaoImpl")
	private LoginUserDaoInf userdao;
	public LoginUserDaoInf getUserdao() {
		return userdao;
	}

	public void setUserdao(LoginUserDaoInf userdao) {
		this.userdao = userdao;
	}
	
	/**
	 * ɾ���û�
	 * @param �û�ID
	 */
	public void delete(Integer number) {
		
		userdao.delete(number);

	}

	/**
	 * ��ѯ�������û�
	 * @return ���ص����û���
	 */
	public List<Loginuser> findAll() {
		return userdao.findAll();
		
	}

	/**
	 * ����id��ѯ�û�
	 * @param �û�id
	 * @return �û��Ƿ����
	 */
	public Loginuser findById(Integer number) {

		return userdao.findById(number);
	}

	/**
	 * ������û�
	 *  @param Ҫ��ӵ��û�����
	 */
	public void insert(Loginuser u) {

		//��ȡ��ǰʱ��
		Calendar c = Calendar.getInstance();
		//System.out.println(c.getTime()); //Wed Mar 12 10:11:21 CST 2008    ���������ʽ
		SimpleDateFormat simpleDateTimeFormat =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
		c = Calendar.getInstance(Locale.CHINESE);
		//System.out.println(simpleDateTimeFormat.format(c.getTime()));//���������ʽ 2008-03-12 10:11:21 
		Timestamp ts = Timestamp.valueOf(simpleDateTimeFormat.format(c.getTime()));
		
		u.setUsercode(0);
		u.setUsertime(ts);
		u.setUsermail(u.getUsername());
		
		userdao.insert(u);

	}

	/**
	 * �����û���Ϣ
	 *  @param Ҫ���µ��û�����
	 */
	public void update(Loginuser u) {

		userdao.update(u);

	}

	/**
	 * ��֤�û��������Ƿ���ȷ
	 * @param Ҫ��֤���û�����
	 * @return ���ھͷ��ض��󣬷���Ϊ��
	 */
	public Loginuser findByNamePwd(Loginuser u) {
		
		return userdao.findByNamePwd(u);
	}

	/**
	 * ����û����Ƿ�ռ��
	 * @param Ҫ�����û���
	 * @return �Ƿ�ռ��
	 */
	public boolean findByName(String name) {
		
		return userdao.findByName(name);
	}	

}
