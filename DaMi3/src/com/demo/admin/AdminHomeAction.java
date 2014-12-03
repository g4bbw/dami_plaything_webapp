package com.demo.admin;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.annotation.Resource;
import com.demo.entity.Product;
import com.demo.entity.Psort;
import com.demo.service.ProductServiceInf;
import com.demo.util.DwindlePic;
import com.demo.util.Page;
import com.demo.util.Result;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;


/**
 * ����Աҳ��
 * @author FeiFei
 */
@Controller("AdminHomeAction")
public class AdminHomeAction implements Action {

	
	@Resource(name="ProductServiceImpl")
	private ProductServiceInf productservice;	
	private List<Psort> psort ; // ��Ʒ���༯��
	private Product newproduct ;//Ҫ��������Ʒ
	private int currentPage;			//��ǰҳ
	private Page page;
	private List<Product> ps; //��Ʒ����
	private int pid ;
	//�ϴ��ļ�����    
	@SuppressWarnings("unused")
	private static final long serialVersionUID = -8204063374280945416L;
    private List<File>upload;// ·��
    private List<String> uploadFileName; //�ļ���
    private List<String> uploadContentType;	//�ļ�·��
    private String imageMainPath;//����չʾͼ·��
    private String imagePath;// ����ͼƬ·��
    private String image_smallPath;// ����СͼƬ·��
    private String file_zipPath;// �����ļ�·��
    private static final int MaxCurrent = 20 ;
	private String pname; //����
	/**
	 * �����Ʒ���ϴ���ƷͼƬ
	 * @return
	 * @throws Exception
	 */
	public String addProduct() throws Exception {
		
		 if(upload == null){
			 System.out.println("�ļ�Ϊ�գ�");
		 }
		
		 String imgUrl = ""; //������ݿ�Ĵ�ͼ��ַ
		 String imgsamllUrl = "";//������ݿ��Сͼ��ַ
		 
		List<String> uploadName = getUploadContentType();
		for (int i = 0; i < upload.size(); i++) {			
			// FileUtils.copyFile(doc, target);
			 File imagemain = new File(getImageMainPath());
		      if (!imagemain.exists()) {
		    	  imagemain.mkdir();
		     }
	        File image = new File(getImagePath());
	        if (!image.exists()) {
	            image.mkdir();
	        }
	        File image_small = new File(getImage_smallPath());
	        if (!image_small.exists()) {
	            image_small.mkdir();
	        }
	        File file_zip = new File(getFile_zipPath());
	        if (!file_zip.exists()) {
	            file_zip.mkdir();
	        }
	        
	        //System.out.println(i + "��"+upload.get(i));
			//System.out.println("�ļ����ͣ�"+uploadName.get(i));
			
			String lastuploadName = ""; //�ļ�����
			String fileNewName = ""; //�ļ�������
	      //�����չʾͼ
			if(i==0){
				lastuploadName = uploadName.get(i).substring(uploadName.get(i).indexOf("/") + 1, uploadName.get(i).length());
				//System.out.println(lastuploadName);
				// �õ��ļ���������
				fileNewName = generateFileName(getUploadFileName().get(i));
				
				newproduct.setPimg("http://127.0.0.1:8080/DaMi3"+imageMainPath+"/" + fileNewName);//�洢չʾͼƬ
				if (lastuploadName.equals("jpeg")) {
					
					// �����ļ�
					FileOutputStream fos = new FileOutputStream(getImagePath() + "/" + fileNewName);
					FileInputStream fis = new FileInputStream(getUpload().get(i));
					byte[] buffer = new byte[1024];
					int len = 0;
					while ((len = fis.read(buffer)) > 0) {
	                fos.write(buffer, 0, len);
					}
					
					DwindlePic mypic = new DwindlePic();
					// s_pic(��ͼƬ·��,����СͼƬ·��,��ͼƬ�ļ���,����СͼƬ����,����СͼƬ���,����СͼƬ�߶�)
					mypic.s_pic(getImagePath() + "/", getImageMainPath() + "/", fileNewName, fileNewName, 180, 180, true);
					//�ر�������......
					fos.close();
					fis.close();
				} else {
					//System.out.println("���������ļ�");
					// �����ļ�
					FileOutputStream fos = new FileOutputStream(getFile_zipPath() + "/" + fileNewName);	           
					FileInputStream fis = new FileInputStream(getUpload().get(i));
					byte[] buffer = new byte[1024];
					int len = 0;
					while ((len = fis.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
					}
					//�ر��ļ���.
					fos.close();
					fis.close();
	        }
				
			}
			//���������Ʒͼ
			else{
				lastuploadName = uploadName.get(i).substring(uploadName.get(i).indexOf("/") + 1, uploadName.get(i).length());
				//System.out.println(lastuploadName);
				// �õ��ļ���������
				fileNewName = generateFileName(getUploadFileName().get(i));
				if (lastuploadName.equals("jpeg")) {
					
					// �����ļ�
					FileOutputStream fos = new FileOutputStream(getImagePath() + "/" + fileNewName);
					FileInputStream fis = new FileInputStream(getUpload().get(i));
					byte[] buffer = new byte[1024];
					int len = 0;
					while ((len = fis.read(buffer)) > 0) {
	                fos.write(buffer, 0, len);
					}
					DwindlePic mypic = new DwindlePic();
					// s_pic(��ͼƬ·��,����СͼƬ·��,��ͼƬ�ļ���,����СͼƬ����,����СͼƬ���,����СͼƬ�߶�)
					mypic.s_pic(getImagePath() + "/", getImage_smallPath() + "/", fileNewName, fileNewName, 60, 60, true);
	            
					//�ر�������......
					fos.close();
					fis.close();
				} else {
					System.out.println("���������ļ�");
					// �����ļ�
					FileOutputStream fos = new FileOutputStream(getFile_zipPath() + "/" + fileNewName);	           
					FileInputStream fis = new FileInputStream(getUpload().get(i));
					byte[] buffer = new byte[1024];
					int len = 0;
					while ((len = fis.read(buffer)) > 0) {
						fos.write(buffer, 0, len);
					}
					//�ر��ļ���.
					fos.close();
					fis.close();
	        }
			
	        imgUrl += "#http://127.0.0.1:8080/DaMi3"+imagePath+"/" + fileNewName; //�ۼӴ�ͼ��ַ
	        imgsamllUrl += "#http://127.0.0.1:8080/DaMi3"+image_smallPath+"/" + fileNewName; //�ۼ�Сͼ��ַ

			}
		}
		newproduct.setPimgbig(imgUrl);
		newproduct.setPimgsmall(imgsamllUrl);
//		System.out.println("��ͼ��ַ" + imgUrl);
//		System.out.println("Сͼ��ַ" + imgsamllUrl);

		productservice.insert(newproduct);
		
		return showProductList();
	}
	
	 /**
     * �������ļ�
     * 
     * @param fileName
     * @return
     */
    private String generateFileName(String fileName) {
        DateFormat format = new SimpleDateFormat("yyMMddHHmmss");
        String formatDate = format.format(new Date());

        int random = new Random().nextInt(10000);

        int position = fileName.lastIndexOf(".");
        String extension = fileName.substring(position);

        return formatDate + random + extension;
    }
	
	/**
	 * ������Ʒ���ҳ�棬������Ʒ����
	 * @return
	 * @throws Exception
	 */
	public String showAddProduct() throws Exception {
		//������Ʒ����
		psort = productservice.findAllPsort();		
//		for(Psort p : psort ){
//			System.out.println(p.getSort());
//		}
		
		return SUCCESS;
	}
	
	/**
	 * ������Ʒ����.  ������Ʒ�б�
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String showProductList() throws Exception{
		
		pid = 0 ; //��շ����ѯ���
		pname = null; //����������
		Page newPage = new Page();				//���÷�ҳ��Ϣ
		newPage.setCurrentPage(1);	//���õ�ǰҳ
		newPage.setEveryPage(MaxCurrent);				//����ÿҳ��ʾ
		Result result = productservice.findAllCommodity(newPage);//��ȡ��ѯ���
		page = result.getPage();		//��ȡ��ҳ��Ϣ
		ps = result.getList();	//��ȡ��Ʒ��Ϣ�б�
		psort = productservice.findAllPsort(); //��ȡ������Ʒ����
		
		
		return SUCCESS;
	}
	
	/**
	 * ��ҳģ����ѯ
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String findProductList() throws Exception{
		
		Page newPage = new Page();				//���÷�ҳ��Ϣ
		newPage.setCurrentPage(1);	//���õ�ǰҳΪ��һҳ
		newPage.setEveryPage(MaxCurrent);				//����ÿҳ��ʾ
		Result result = productservice.findByName(pname,newPage);//��ȡ��ѯ���
		page = result.getPage();		//��ȡ��ҳ��Ϣ
		ps = result.getList();	//��ȡ��Ʒ��Ϣ�б�
		psort = productservice.findAllPsort(); //��ȡ������Ʒ����
		
		return SUCCESS;
	}
	
	
	
	public ProductServiceInf getProductservice() {
		return productservice;
	}
	public void setProductservice(ProductServiceInf productservice) {
		this.productservice = productservice;
	}
	public List<Psort> getPsort() {
		return psort;
	}
	public void setPsort(List<Psort> psort) {
		this.psort = psort;
	}
	public Product getNewproduct() {
		return newproduct;
	}
	public void setNewproduct(Product newproduct) {
		this.newproduct = newproduct;
	}
	public List<File> getUpload() {
		return upload;
	}
	public void setUpload(List<File> upload) {
		this.upload = upload;
	}
	public List<String> getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(List<String> uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public List<String> getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(List<String> uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String getImagePath() {
		return ServletActionContext.getServletContext().getRealPath(imagePath);
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getImage_smallPath() {
		 return ServletActionContext.getServletContext().getRealPath(image_smallPath);
	}
	public void setImage_smallPath(String image_smallPath) {
		this.image_smallPath = image_smallPath;
	}
	public String getFile_zipPath() {
		 return ServletActionContext.getServletContext().getRealPath(file_zipPath);
	}
	public void setFile_zipPath(String file_zipPath) {
		this.file_zipPath = file_zipPath;
	}
	public String getImageMainPath() {
	      return ServletActionContext.getServletContext().getRealPath(imageMainPath);
	}
	public void setImageMainPath(String imageMainPath) {
		this.imageMainPath = imageMainPath;
	}

	@Override
	public String execute() throws Exception {
		
		System.out.println("Ĭ�Ϸ���~");
		
		return null;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<Product> getPs() {
		return ps;
	}

	public void setPs(List<Product> ps) {
		this.ps = ps;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public static int getMaxcurrent() {
		return MaxCurrent;
	}

	

	

}
