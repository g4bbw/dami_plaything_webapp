package com.demo.entity;

import java.util.List;

/**
 * �����������ڷ�װ��������ϸ����
 * @author FeiFei
 */
public class Ordergoods {

	private Indent indent ;
	private List<Indentproduct> indentproduct ;
	public Indent getIndent() {
		return indent;
	}
	public void setIndent(Indent indent) {
		this.indent = indent;
	}
	public List<Indentproduct> getIndentproduct() {
		return indentproduct;
	}
	public void setIndentproduct(List<Indentproduct> indentproduct) {
		this.indentproduct = indentproduct;
	}
	
	
}
