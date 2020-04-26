package com.jsp.action.pds;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.PdsVO;
import com.jsp.request.PdsModifyRequest;
import com.jsp.service.PdsService;

public class ModifyPdsAction implements Action{
	
	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "pds/modify_success";
		
		int pno = Integer.parseInt(request.getParameter("pno"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		PdsModifyRequest pdsRequest = new PdsModifyRequest(pno, title, content);
		
		PdsVO pds = pdsRequest.toPdsModifyVO();
		
		try {
			pdsService.modify(pds);
		} catch (SQLException e) {
			e.printStackTrace();
			url = "pds/modify_fail";
		}
		
		return url;
	}

}
