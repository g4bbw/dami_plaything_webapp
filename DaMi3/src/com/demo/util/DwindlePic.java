package com.demo.util;

import java.io.*;
import com.sun.image.codec.jpeg.*;
import java.awt.image.*;
import java.awt.*;
import java.applet.*;

//����ͼ�࣬  
//��java���ܽ�jpgͼƬ�ļ������еȱȻ�ǵȱȵĴ�Сת����  
//����ʹ�÷���  
//s_pic(��ͼƬ·��,����СͼƬ·��,��ͼƬ�ļ���,����СͼƬ����,����СͼƬ���,����СͼƬ�߶�,�Ƿ�ȱ�����(Ĭ��Ϊtrue))  
public class DwindlePic {

    String InputDir; // ����ͼ·��
    String OutputDir; // ���ͼ·��
    String InputFileName; // ����ͼ�ļ���
    String OutputFileName; // ���ͼ�ļ���
    int OutputWidth = 80; // Ĭ�����ͼƬ��
    int OutputHeight = 80; // Ĭ�����ͼƬ��
    int rate = 0;
    boolean proportion = true; // �Ƿ�ȱ����ű��(Ĭ��Ϊ�ȱ�����)

    public DwindlePic() {
        // ��ʼ������
        InputDir = "";
        OutputDir = "";
        InputFileName = "";
        OutputFileName = "";
        OutputWidth = 80;
        OutputHeight = 80;
        rate = 0;
    }

    public boolean s_pic() {
        // BufferedImage image;
        // String NewFileName;
        // ��������ļ�����
        File file = new File(OutputDir + OutputFileName);
        FileOutputStream tempout = null;
        try {
            tempout = new FileOutputStream(file);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        Image img = null;
        Toolkit tk = Toolkit.getDefaultToolkit();
        Applet app = new Applet();
        MediaTracker mt = new MediaTracker(app);
        try {
            img = tk.getImage(InputDir + InputFileName);
            mt.addImage(img, 0);
            mt.waitForID(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (img.getWidth(null) == -1) {
            System.out.println(" can't read,retry!" + "<BR>");
            return false;
        } else {
            int new_w;
            int new_h;
            if (this.proportion == true) { // �ж��Ƿ��ǵȱ�����.
                // Ϊ�ȱ����ż��������ͼƬ��ȼ��߶�
                double rate1 = ((double) img.getWidth(null)) / (double) OutputWidth + 0.1;
                double rate2 = ((double) img.getHeight(null)) / (double) OutputHeight + 0.1;
                double rate = rate1 > rate2 ? rate1 : rate2;
                new_w = (int) (((double) img.getWidth(null)) / rate);
                new_h = (int) (((double) img.getHeight(null)) / rate);
            } else {
                new_w = OutputWidth; // �����ͼƬ���
                new_h = OutputHeight; // �����ͼƬ�߶�
            }
            BufferedImage buffImg = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);

            Graphics g = buffImg.createGraphics();

            g.setColor(Color.white);
            g.fillRect(0, 0, new_w, new_h);

            g.drawImage(img, 0, 0, new_w, new_h, null);
            g.dispose();

            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(tempout);
            try {
                encoder.encode(buffImg);
                tempout.close();
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }
        return true;
    }

    public boolean s_pic(String InputDir, String OutputDir, String InputFileName, String OutputFileName) {
        // ����ͼ·��
        this.InputDir = InputDir;
        // ���ͼ·��
        this.OutputDir = OutputDir;
        // ����ͼ�ļ���
        this.InputFileName = InputFileName;
        // ���ͼ�ļ���
        this.OutputFileName = OutputFileName;
        return s_pic();
    }

    public boolean s_pic(String InputDir, String OutputDir, String InputFileName, String OutputFileName, int width, int height, boolean gp) {
        // ����ͼ·��
        this.InputDir = InputDir;
        // ���ͼ·��
        this.OutputDir = OutputDir;
        // ����ͼ�ļ���
        this.InputFileName = InputFileName;
        // ���ͼ�ļ���
        this.OutputFileName = OutputFileName;
        // ����ͼƬ����
        setW_H(width, height);
        // �Ƿ��ǵȱ����� ���
        this.proportion = gp;
        return s_pic();
    }

    public void setInputDir(String InputDir) {
        this.InputDir = InputDir;
    }

    public void setOutputDir(String OutputDir) {
        this.OutputDir = OutputDir;
    }

    public void setInputFileName(String InputFileName) {
        this.InputFileName = InputFileName;
    }

    public void setOutputFileName(String OutputFileName) {
        this.OutputFileName = OutputFileName;
    }

    public void setOutputWidth(int OutputWidth) {
        this.OutputWidth = OutputWidth;
    }

    public void setOutputHeight(int OutputHeight) {
        this.OutputHeight = OutputHeight;
    }

    public void setW_H(int width, int height) {
        this.OutputWidth = width;
        this.OutputHeight = height;
    }

    public static void main(String[] a) {
        // s_pic(��ͼƬ·��,����СͼƬ·��,��ͼƬ�ļ���,����СͼƬ����,����СͼƬ���,����СͼƬ�߶�)
        DwindlePic mypic = new DwindlePic();
        System.out.println(mypic.s_pic("d:/", "d:/", "200.jpg", "bbb.jpg", 130, 80, true));
    }
}