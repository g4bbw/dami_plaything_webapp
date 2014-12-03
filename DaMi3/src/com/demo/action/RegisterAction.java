package com.demo.action;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.demo.entity.Loginuser;
import com.demo.service.LoginUserServiceInf;
import com.demo.util.SecurityCode;
import com.demo.util.SecurityImage;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

/**
 * ע��
 * @author Damon
 *
 */
@Controller("RegisterAction")
public class RegisterAction implements Action {

	@Resource(name="LoginUserServiceImpl")
	private LoginUserServiceInf login;
	//��֤�Ƿ��Ѿ�����
	private String id;
	//ע�����û�����
	private Loginuser u ;
	//ҳ���ύ����֤��
	private String fromcode;
	//ͼƬ��
    private ByteArrayInputStream imageStream;
 
	public String execute() throws Exception {
		
		 //�������Hardģʽ�����Բ����ִ�Сд
//      String securityCode = SecurityCode.getSecurityCode(4,SecurityCodeLevel.Hard, false).toLowerCase();
      
      //��ȡĬ���ѶȺͳ��ȵ���֤��
      String securityCode = SecurityCode.getSecurityCode();
      imageStream = SecurityImage.getImageAsInputStream(securityCode);
      //����session��
      ActionContext.getContext().getSession().put("securityCode" ,securityCode);
		return SUCCESS;
	}
	
	/**
	 * �û�ע��
	 * @return
	 * @throws Exception
	 */
	public String register() throws Exception {
		
		//��ȡ��ȷ��֤��
		String Code = (String)ActionContext.getContext().getSession().get("securityCode");
		
		
		//�ȶ���֤��
		if(!fromcode.equals(Code)){
			return "fromerror";
		}
		
		//�����û��ı��ε�¼ip
		u.setIp(ServletActionContext.getRequest().getRemoteAddr());
		
		login.insert(u);
		
		ActionContext.getContext().getSession().put("mail" ,u);
			
		return SUCCESS;
	
	}
	
	/**
	 * ��֤�����Ƿ��Ѿ�����
	 * @throws Exception
	 */
	public void checking() throws Exception {

		int i =1 ;
		
		if(!login.findByName(id)){
			i=0;
		}
		
		try {
			PrintWriter out= ServletActionContext.getResponse().getWriter();
			
			out.print(i);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	   public ByteArrayInputStream getImageStream() {
	        return imageStream;
	    }
	   
	public void setImageStream(ByteArrayInputStream imageStream) {
		this.imageStream = imageStream;
	}
	public Loginuser getU() {
		return u;
	}
	public void setU(Loginuser u) {
		this.u = u;
	}
	public String getFromcode() {
		return fromcode;
	}
	public void setFromcode(String fromcode) {
		this.fromcode = fromcode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public LoginUserServiceInf getLogin() {
		return login;
	}
	public void setLogin(LoginUserServiceInf login) {
		this.login = login;
	}

	  
}
