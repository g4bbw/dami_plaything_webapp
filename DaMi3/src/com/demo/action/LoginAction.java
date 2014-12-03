package com.demo.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;
import com.demo.entity.Loginuser;
import com.demo.entity.Shopcart;
import com.demo.service.LoginUserServiceInf;
import com.demo.service.ShopCartServiceInf;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

/**
 * ��¼��֤
 * @author Damon
 *
 */
@Controller("LoginAction")
public class LoginAction implements Action {

	@Resource(name="LoginUserServiceImpl")
	private LoginUserServiceInf login;
	
	@Resource(name="ShopCartServiceImpl")
	private ShopCartServiceInf shopcartservice ;
	private Loginuser user;
	private Loginuser userinfo;
	private String password;//�û�����
	/**
	 * ��֤�û������Ƿ���ȷ
	 * @return ��ȷ
	 */
	public String execute() throws Exception {

		 Loginuser u = login.findByNamePwd(user);
		
		if(u == null){
			
			return ERROR;
		}
		
		//����û��˺�û����
		else if(u.getUsercode() != 1){
			
			return NONE;
		}
		
		//����û���������λ���� 
		else if(u.getUsernickname()==null){
			ActionContext.getContext().getSession().put("user" , u);
			return "info";
			
		}
		
		//��¼�û��ϴε�¼��ip��ַ
		u.setLoginip(u.getIp());
		//��¼�û����ε�¼��ip��ַ
		u.setIp(ServletActionContext.getRequest().getRemoteAddr());
		login.update(u);
		ActionContext.getContext().getSession().put("u" , u);
		
		//����û��Ѿ���¼�������ݿ�ȡ�����ﳵ
		List<Shopcart> shoppingcarts= shopcartservice.getallCartsByuserid(u.getUserid());
				
			
		ActionContext.getContext().getSession().put("cart" , shoppingcarts);
		
		return SUCCESS;
	}
	
	/**
	 * �����û�����
	 * @return ���³ɹ�
	 * @throws Exception
	 */
	public String info() throws Exception {
		
		Loginuser user = (Loginuser)ActionContext.getContext().getSession().get("user");
		user.setUsernickname(userinfo.getUsernickname());
		user.setSex(userinfo.getSex());
		
		//��¼�û��ϴε�¼��ip��ַ
		user.setLoginip(user.getIp());
		//��¼�û����ε�¼��ip��ַ
		user.setIp(ServletActionContext.getRequest().getRemoteAddr());
		login.update(user);
		ActionContext.getContext().getSession().put("u" , user);
		
		return SUCCESS;
		
	}
	
	/**
	 * ���session���˳���¼
	 * @return 
	 * @throws Exception
	 */
	public String exit() throws Exception {
	
		ActionContext.getContext().getSession().put("u" , null);
		
		return SUCCESS;
	}
	
	/**
	 * 
	 * @return
	 */
	public String alterUser() throws Exception {
		
		Loginuser user = (Loginuser) ActionContext.getContext().getSession().get("u");
		//System.out.println(user.getUsernickname());
		
		//����û�û�е�¼
		if(user == null){
			return "nologin";
		}
		
		user.setUserpsw(password);
		login.update(user);
		
		return SUCCESS;
	}

	public LoginUserServiceInf getLogin() {
		return login;
	}
	public void setLogin(LoginUserServiceInf login) {
		this.login = login;
	}
	public Loginuser getUser() {
		return user;
	}
	public void setUser(Loginuser user) {
		this.user = user;
	}
	public Loginuser getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(Loginuser userinfo) {
		this.userinfo = userinfo;
	}

	public ShopCartServiceInf getShopcartservice() {
		return shopcartservice;
	}

	public void setShopcartservice(ShopCartServiceInf shopcartservice) {
		this.shopcartservice = shopcartservice;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
