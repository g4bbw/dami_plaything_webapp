package com.demo.util;

import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServlet;


public class SendEmail {
	
	public void send(String to){
	
		
		
		
		String subject="�����ֻ������̳�������֤����";
		String content="��л����"+timeutil.getnowdate()+"�ڴ����ֻ��̳�ע�ᣬ�����������ӣ�������ɰ�ȫ��֤��"
		+"http://127.0.0.1/DaMi3/registbyEmail.action?checkemail="+to	
		
		+"Ϊ���������ʺŰ�ȫ������24Сʱ�ڵ�������ӣ���Ҳ���Խ����Ӹ��Ƶ��������ַ�����ʡ�"+
"����û���������֤���� ���������Դ��ʼ����ɴ˸��������Ĳ������½⡣";
		
		
		  String host = "smtp.163.com";      //������ʹ�÷��ʼ��ĵ������������ 
	      
		  String from = "zx540744679@163.com";     //���ʼ��ĳ����أ������˵����䣩 
	      
		  Properties props = System.getProperties(); 
	      // �����ʼ������� 
	      props.put("mail.smtp.host", host); 
	      // ȡ��session 
	      props.put("mail.smtp.auth", "true"); //��������ͨ����֤ 
	      MyAuthenticator myauth = new MyAuthenticator("zx540744679@163.com", "cwz123456789"); 
	      Session session = Session.getDefaultInstance(props, myauth); 
	      //session.setDebug(true); 
	      // ����message 
	      MimeMessage message = new MimeMessage(session); 
	      try{
	      
	      // �趨�����ʼ��ĵ�ַ 
	      message.setFrom(new InternetAddress(from)); 
	      // �趨�����ʼ��ĵ�ַ 
	      message.addRecipient(Message.RecipientType.TO, 
	        new InternetAddress(to)); 
	      // �趨�ʼ�����
	      message.setSubject(subject); 
	      // �趨�ʼ�����
	      BodyPart mdp=new MimeBodyPart();//�½�һ������ż����ݵ�BodyPart����
	      mdp.setContent(content,"text/html;charset=gbk");//��BodyPart�����������ݺ͸�ʽ/���뷽ʽ
	      Multipart mm=new MimeMultipart();//�½�һ��MimeMultipart�����������BodyPart��
	     //��(��ʵ�Ͽ��Դ�Ŷ��)
	     mm.addBodyPart(mdp);//��BodyPart���뵽MimeMultipart������(���Լ�����BodyPart)
	     message.setContent(mm);//��mm��Ϊ��Ϣ���������
	     //   message.setText(content); 
	      message.saveChanges(); 
	      Transport.send(message);  
	      }catch(Exception e){
	    	  
	    	  e.printStackTrace();
	      }
	      
	   }

	
}

class MyAuthenticator  extends javax.mail.Authenticator {
			private String strUser;
			private String strPwd;
			public MyAuthenticator(String user, String password) {
				this.strUser = user;
				this.strPwd = password;
			}

			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(strUser, strPwd);
			}
}


