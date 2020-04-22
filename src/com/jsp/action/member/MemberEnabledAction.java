package com.jsp.action.member;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.service.MemberService;
import com.jsp.service.MemberServiceImpl;

public class MemberEnabledAction implements Action{
	
	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "member/enabled_success";

		String id = request.getParameter("id");

		try {
			memberService.enabledMember(id);
		} catch (SQLException e) {
			e.printStackTrace();
			url = "member/enabled_fail";
		}
		return url;
	}

}
