package com.jsp.action.member;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;
import com.jsp.service.MemberServiceImpl;

public class MemberModifyFormAction implements Action{
	
	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = "member/modify";
		
		String id = request.getParameter("id");
		
		MemberVO member = null; 
		try {
			member = memberService.getMember(id);
		} catch (SQLException e) {
			e.printStackTrace();
			url = "error/500_error";
		}
		
		request.setAttribute("member", member);
		
		return url;
	}

}
