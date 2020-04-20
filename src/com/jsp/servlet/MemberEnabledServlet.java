package com.jsp.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.View;

import com.jsp.dto.MemberVO;
import com.jsp.service.MemberServiceImpl;
import com.jsp.utils.ViewResolver;

/**
 * Servlet implementation class MemberEnabledServlet
 */
//@WebServlet("/member/enabled")
public class MemberEnabledServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "member/enabled_success";

		String id = request.getParameter("id");

		try {
			MemberServiceImpl.getInstance().enabledMember(id);
		} catch (SQLException e) {
			e.printStackTrace();
			url = "member/enabled_fail";
		}

		ViewResolver.view(request, response, url);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
