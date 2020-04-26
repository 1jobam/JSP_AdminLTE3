package com.jsp.request;

import com.jsp.dto.PdsVO;

public class PdsRegistRequest {
	
	private String title;
	private String content;
	private String writer;
	
	public PdsRegistRequest(String title, String content, String writer) {
		this.title = title;
		this.content = content;
		this.writer = writer;
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

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	@Override
	public String toString() {
		return "PdsRegistRequest [title=" + title + ", content=" + content + ", writer=" + writer + "]";
	}

	public PdsVO toPdsVO() {
		PdsVO pds = new PdsVO();
		pds.setTitle(title);
		pds.setContent(content);
		pds.setWriter(writer);
		
		return pds;
	}

}
