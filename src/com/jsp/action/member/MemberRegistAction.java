package com.jsp.action.member;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.MemberVO;
import com.jsp.request.MemberRegistRequest;
import com.jsp.service.MemberServiceImpl;

public class MemberRegistAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "member/regist_success";

		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String picture = request.getParameter("picture");
		String[] phone = request.getParameterValues("phone");
		String authority = request.getParameter("authority");
		String name = request.getParameter("name");

		MemberRegistRequest memberReq = new MemberRegistRequest(id, pwd, authority, email, phone, picture, name);

		MemberVO member = memberReq.toMemberVO();

		try {
			MemberServiceImpl.getInstance().regist(member);
		} catch (SQLException e) {
			e.printStackTrace();
			url = "member/regist_fail";
		}

		return url;
	}

}
