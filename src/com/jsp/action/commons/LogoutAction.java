package com.jsp.action.commons;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.action.Action;
import com.jsp.utils.ViewResolver;

public class LogoutAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "redirect:/commons/logout.do";
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		return url;
	}

}
