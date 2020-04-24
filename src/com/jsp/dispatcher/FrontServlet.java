package com.jsp.dispatcher;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;

public class FrontServlet extends HttpServlet {
	
	private HandlerMapper handlerMapper;
	private ViewResolver viewResolver;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		String handlerMapperType = config.getInitParameter("handlerMapper");
		String viewResolverType = config.getInitParameter("viewResolver");
		
		try {
			this.handlerMapper = (HandlerMapper)injectionBean(handlerMapperType);
			System.out.println("[FrontServlet]" + handlerMapper + "가 준비되었습니다.");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("[FrontServlet]" + handlerMapper + "가 준비되지 않았습니다.");
		}
		
		try {
			this.viewResolver = (ViewResolver)injectionBean(viewResolverType);
			System.out.println("[FrontServlet]" + viewResolver + "가 준비되었습니다.");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("[FrontServlet]" + viewResolver + "가 준비되지 않았습니다.");
		}
		
		super.init(config);
	}
	
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
		
		act = handlerMapper.getAction(command);
		
		if(act == null) {
			System.out.println("!!not found : " + command);
		}else {
			view = act.execute(request, response);
			
			if(view != null)
				viewResolver.view(request, response, view);
		}
		
	}
	
	private Object injectionBean(String classType) throws Exception{
		
		Class<?> cls = Class.forName(classType);
		return cls.newInstance();
	}

}
