package com.jsp.action.commons;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;

public class LoginFormAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = "commons/loginForm";
		
		return url;
	}

}
