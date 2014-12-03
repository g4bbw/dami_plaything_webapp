package com.demo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

/**
 * �޸�ͼƬ������
 * @author FeiFei
 *
 */
public class uploadUtil {

    private final static String imagePath="/image";// ����ͼƬ·��
    private final static String image_smallPath="/image_small";// ����СͼƬ·��
    private final static String file_zipPath="/file";// �����ļ�·��
    @SuppressWarnings({ "resource", "unused", "deprecation" })
	public String uploadImage(HttpServletRequest request, File getUpload, String getUploadContentType,
            String getUploadFileName) throws IOException {
        String getImagePath = request.getRealPath(imagePath);
        String getImage_smallPath = request.getRealPath(image_smallPath);

        File image = new File(getImagePath);
        if (!image.exists()) {
            image.mkdir();
        }
        File image_small = new File(getImage_smallPath);
        if (!image_small.exists()) {
            image_small.mkdir();
        }
        // �õ��ϴ��ļ��ĺ�׺��
        String uploadName = getUploadContentType;
        String lastuploadName = uploadName.substring(uploadName.indexOf("/") + 1, uploadName.length());
        // �õ��ļ���������
        String fileNewName = generateFileName(getUploadFileName);

        FileOutputStream fos = new FileOutputStream(getImagePath + "/" + fileNewName);
        FileInputStream fis = new FileInputStream(getUpload);
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = fis.read(buffer)) > 0) {
            fos.write(buffer, 0, len);
        }
        DwindlePic mypic = new DwindlePic();
        // s_pic(��ͼƬ·��,����СͼƬ·��,��ͼƬ�ļ���,����СͼƬ����,����СͼƬ���,����СͼƬ�߶�)
        mypic.s_pic(getImagePath + "/", getImage_smallPath + "/", fileNewName, fileNewName, 120, 80, true);

        return imagePath + "/" + fileNewName;

    }

    @SuppressWarnings({ "resource", "unused", "static-access" })
	public String uploadfile(ServletActionContext context,File getUpload, String getUploadContentType,
            String getUploadFileName) throws IOException {
        
        String getFile_zipPath = context.getServletContext().getRealPath(file_zipPath);
        File file_zip = new File(getFile_zipPath);
        if (!file_zip.exists()) {
            file_zip.mkdir();
        }
        // �õ��ϴ��ļ��ĺ�׺��
        String uploadName = getUploadContentType;
        String lastuploadName = uploadName.substring(uploadName.indexOf("/") + 1, uploadName.length());
        // �õ��ļ���������
        String fileNewName = generateFileName(getUploadFileName);
        // �����ļ�
        FileOutputStream fos = new FileOutputStream(getFile_zipPath + "/" + fileNewName);
        FileInputStream fis = new FileInputStream(getUpload);
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = fis.read(buffer)) > 0) {
            fos.write(buffer, 0, len);
        }
        return file_zipPath + "/" + fileNewName;

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
}