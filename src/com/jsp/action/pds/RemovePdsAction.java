package com.jsp.action.pds;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.service.PdsService;

public class RemovePdsAction implements Action{

	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "pds/remove_success";

		int pno = Integer.parseInt(request.getParameter("pno"));
		
		try {
			pdsService.remove(pno);
		} catch (SQLException e) {
			e.printStackTrace();
			url = "pds/remove_fail";
		}
		
		return url;
	}

}
