package com.demo.entity;

public class Page {
	// ��ǰҳ
	private int pagenow;
	// ÿҳ��ʾ�ļ�¼��
	private int pagesize=10;
	// ��ҳ��
	private int pageCount;
	// �ܼ�¼��
	private int rowsize;
	public int getPagenow() {
		return pagenow;
	}
	public void setPagenow(int pagenow) {
		this.pagenow = pagenow;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int rowsize) {
		
		if(rowsize%this.pagesize==0){
			this.pageCount = rowsize/this.pagesize;	
		}else{
			this.pageCount = rowsize/this.pagesize+1;
		}
		
		
	}
	public int getRowsize() {
		return rowsize;
	}
	public void setRowsize(int rowsize) {
		this.rowsize = rowsize;
		//�ڻ�ȡ���ܼ�¼���󣬵�����ҳ����set���������ܼ�¼��������м���
		this.setPageCount(rowsize);
	}

}
