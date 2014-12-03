package com.demo.action;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.demo.entity.CartItem;
import com.demo.entity.Loginuser;
import com.demo.entity.Product;
import com.demo.entity.Shopcart;
import com.demo.service.CartItemServiceInf;
import com.demo.service.ProductServiceInf;
import com.demo.service.ShopCartServiceInf;
import com.demo.util.ShoppingCartStatus;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

/**
 * ���ﳵ����
 * @author Damon
 */
@Controller("ShopCartAction")
public class ShopCartAction implements Action {
	
	
	@Resource(name="ProductServiceImpl")
	private ProductServiceInf productservice;
	@Resource(name="ShopCartServiceImpl")
	private ShopCartServiceInf shopcartservice ;
	@Resource(name="CartItemServiceImpl")
	private CartItemServiceInf cartitemservice;
	
	private int pid; //��Ʒid
	private String pcount;//��Ʒ����
	private int cartid;
	private Product product;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public ProductServiceInf getProductservice() {
		return productservice;
	}
	public void setProductservice(ProductServiceInf productservice) {
		this.productservice = productservice;
	}
	public ShopCartServiceInf getShopcartservice() {
		return shopcartservice;
	}
	public void setShopcartservice(ShopCartServiceInf shopcartservice) {
		this.shopcartservice = shopcartservice;
	}
	public CartItemServiceInf getCartitemservice() {
		return cartitemservice;
	}
	public void setCartitemservice(CartItemServiceInf cartitemservice) {
		this.cartitemservice = cartitemservice;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getPcount() {
		return pcount;
	}
	public void setPcount(String pcount) {
		this.pcount = pcount;
	}
	public int getCartid() {
		return cartid;
	}
	public void setCartid(int cartid) {
		this.cartid = cartid;
	}
	
	/**
	 * ���㹺�ﳵ�ܼ�
	 */
	public String totalPrices() throws Exception {

		Loginuser user = (Loginuser) ActionContext.getContext().getSession().get("u");
		//System.out.println(user.getUsernickname());
		
		//����û�û�е�¼
		if(user == null){
			return "nologin";
		}
		
			//����û��Ѿ���¼�������ݿ�ȡ�����ﳵ
			List<Shopcart> shoppingcarts= shopcartservice.getallCartsByuserid(user.getUserid());
			
			//����ܼ۸�
			int total=0;
			Iterator<Shopcart> cartite=shoppingcarts.iterator();
			
			while(cartite.hasNext()){
				
				Shopcart sp=cartite.next();
				int pcount=Integer.parseInt(sp.getCartItem().getPcount());
				int price=Integer.parseInt(sp.getCartItem().getProduct().getPrice());
				total=pcount*price+total;
			}
			ActionContext.getContext().getSession().put("mycarts" , shoppingcarts);
			ActionContext.getContext().getSession().put("total" , total);
			
			
			
			return SUCCESS;
		
	}
		
	/**
	 * ��ȡ���ﳵ
	 */
	public String execute() throws Exception {

		Loginuser user = (Loginuser) ActionContext.getContext().getSession().get("u");
		
		//����û�û�е�¼
		if(user == null){
			return "nologin";
		}
		
		
		//����û��Ѿ���¼�������ݿ�ȡ�����ﳵ
		List<Shopcart> shoppingcarts= shopcartservice.getallCartsByuserid(user.getUserid());
		
		//������ﳵΪ��
		if(shoppingcarts.size() == 0)
		{
			ActionContext.getContext().getSession().put("cart" , shoppingcarts);
			return "noshopcart";
		}
		else{
			ActionContext.getContext().getSession().put("cart" , shoppingcarts);
		
			return SUCCESS;
		}
	}

	/**
	 * �����Ʒ�����ﳵ
	 * @return
	 * @throws Exception
	 */
	public String addToCart() throws Exception{
	
		Loginuser user = (Loginuser) ActionContext.getContext().getSession().get("u");
		
		//����û�û�е�¼
		if(user == null){
			return "nologin";
		}
				
		//��ѯ����Ʒ����
		Product product= productservice.findById(pid);
		
		Shopcart shopcart = shopcartservice.findCartProduct(user , product);
		
		if(shopcart != null)
		{
			//System.out.println("��Ʒ���� :" +shopcart.getCartItem().getProduct().getPname());
			shopcartservice.setNum(shopcart, Integer.parseInt(pcount));
			
		}
		else{
			//��ӹ��ﳵ��ϸ
			CartItem cartitem=new CartItem();
			cartitem.setProduct(product);
			cartitem.setUniqueprice(product.getPrice());
			cartitem.setPcount(pcount);
			cartitemservice.addcartitem(cartitem);
			
			//��ӵ����ﳵ
			Shopcart shoppingcart=new Shopcart();
			shoppingcart.setCartItem(cartitem);
			shoppingcart.setLoginuser(user);
			//���ﳵ��û�ύ
			shoppingcart.setShoppingcartStatus(ShoppingCartStatus.Pre);
			shopcartservice.addShoppingCart(shoppingcart);
		}
		
		
		
		return SUCCESS;
	}

	/**
	 * ɾ�����ﳵ
	 * @return
	 * @throws Exception
	 */
	public String delectCart() throws Exception{
		Shopcart sc = (Shopcart)shopcartservice.getuniqueSpByid(cartid);
		Product pdt = productservice.selectproductid(sc.getCartItem().getProduct().getPsid());
		pdt.setPstock(pdt.getPstock()+Integer.parseInt(sc.getCartItem().getPcount()));
		productservice.update(pdt);
		shopcartservice.deleteShoppingcart(cartid);
		
		return SUCCESS;
	}
	
	public String updateproduct(){
		Product pdt = productservice.selectproductid(pid);
		int pcounts = Integer.parseInt(pcount);
		pdt.setPstock(pdt.getPstock()-pcounts);
		productservice.update(pdt);
		return "updateproduct";
	}
	
}
