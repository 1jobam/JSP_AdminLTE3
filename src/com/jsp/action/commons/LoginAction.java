package com.jsp.action.commons;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.action.Action;
import com.jsp.dto.MemberVO;
import com.jsp.exception.InvalidPasswordException;
import com.jsp.exception.NotFoundIDException;
import com.jsp.service.MemberService;
import com.jsp.service.MemberServiceImpl;

public class LoginAction implements Action {
	
	private MemberService memberService = MemberServiceImpl.getInstance();
	
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "redirect:/member/list.do";

		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");

		HttpSession session = request.getSession();

		try {

			memberService.login(id, pwd);

			MemberVO loginUser = memberService.getMember(id);
			session.setAttribute("loginUser", loginUser);
			System.out.println("세션테스트");
			session.setMaxInactiveInterval(60 * 6);
			System.out.println("세션테스트");

		} catch (SQLException e) {

			e.printStackTrace();
			url = "error/500_error";
			request.setAttribute("exception", e);

		} catch (NotFoundIDException | InvalidPasswordException e) {

			// e.printStackTrace();
			url = "redirect:/commons/loginForm.do";
//			session.setAttribute("id", id);
			request.setAttribute("msg", e.getMessage());

		}

		return url;
	}

}
