package com.jsp.action.member;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.action.Action;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberServiceImpl;

public class MemberDisabledAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "member/disabled_success";
		
		String id = request.getParameter("id");
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if(id.equals(loginUser.getId())) {
			url = "member/disabled_denied";
		}else {
			try {
				MemberServiceImpl.getInstance().disabledMember(id);
			} catch (SQLException e) {
				e.printStackTrace();
				url = "member/disabled_fail";
			}	
		}
		return url;
	}

}
