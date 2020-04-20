package com.jsp.action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.action.Action;
import com.jsp.dto.MemberVO;

public class CheckPasswordAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String result = null;

		String pwd = request.getParameter("pwd");

		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");

		if (pwd.equals(loginUser.getPwd())) {
			result = "SUCCESS";
			
		}

		response.getWriter().print(result);
		
		return null;
	}

}
