package com.jsp.action.member;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.action.Action;
import com.jsp.dto.MemberVO;
import com.jsp.request.MemberModifyRequest;
import com.jsp.service.MemberService;
import com.jsp.service.MemberServiceImpl;
import com.jsp.utils.GetUploadPath;

public class MemberModifyAction implements Action {
	
	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "member/modify_success";

		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String picture = request.getParameter("picture");
		String[] phones = request.getParameterValues("phone");
		String authority = request.getParameter("authority");
		String name = request.getParameter("name");

		String phone1 = phones[0].substring(0, 3);
		String phone2 = phones[0].substring(4, 8);
		String phone3 = phones[0].substring(9);
		String[] phone = { phone1, phone2, phone3 };

		MemberModifyRequest memberReq = new MemberModifyRequest(id, pwd, authority, email, phone, picture, name);

		MemberVO member = memberReq.ModifyMember();

		try {
			memberService.modify(member);

			HttpSession session = request.getSession();

			MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
			if (member.getId().equals(loginUser.getId())) {
				session.setAttribute("loginUser", member);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			url = "member/modify_fail";
			String oldFileName = member.getPicture();
			String uploadPath = GetUploadPath.getUploadPath("member.picture.upload");
			File oldFile = new File(uploadPath + File.separator + oldFileName);
			if (oldFile.exists()) {
				oldFile.delete();
			}
		}

		request.setAttribute("id", id);

		return url;
	}

}
