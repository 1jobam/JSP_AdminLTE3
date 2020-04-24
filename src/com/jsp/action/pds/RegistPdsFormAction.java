package com.jsp.action.pds;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;

public class RegistPdsFormAction implements Action{

	/*private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService; 
	}*/
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "pds/regist";
		return url;
	}

}
