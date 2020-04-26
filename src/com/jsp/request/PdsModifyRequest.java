package com.jsp.request;

import com.jsp.dto.PdsVO;

public class PdsModifyRequest {
	
	private int pno;
	private String title;
	private String content;
	
	public PdsModifyRequest(int pno, String title, String content) {
		this.pno = pno;
		this.title = title;
		this.content = content;
	}

	public int getPno() {
		return pno;
	}

	public void setPno(int pno) {
		this.pno = pno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public PdsVO toPdsModifyVO() {
		PdsVO pds = new PdsVO();
		pds.setPno(pno);
		pds.setTitle(title);
		pds.setContent(content);
		return pds;
	}
	
}
