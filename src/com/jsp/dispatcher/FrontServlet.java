package com.jsp.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;

public class FrontServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}
	
	private void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String command = request.getRequestURI(); // contextPath 포함.
		
		if(command.indexOf(request.getContextPath()) == 0) { // contextPath 삭제
			command = command.substring(request.getContextPath().length());
		}
		
		Action act = null;
		String view = null;
		
		act = HandlerMapper.getAction(command);
		
		if(act == null) {
			System.out.println("!!not found : " + command);
		}else {
			view = act.execute(request, response);
			
			if(view != null)
				ViewResolver.view(request, response, view);
		}
		
	}

}
