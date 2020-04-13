package com.jsp.service;

import java.sql.SQLException;
import java.util.List;

import com.jsp.dao.MemberDAO;
import com.jsp.dao.MemberDAOImpl;
import com.jsp.dto.MemberVO;
import com.jsp.exception.InvalidPasswordException;
import com.jsp.exception.NotFoundIDException;

public class MemberServiceImpl implements MemberService {
	
	private static MemberServiceImpl instance = new MemberServiceImpl();
	
	private MemberServiceImpl() {}
	
	public static MemberServiceImpl getInstance() {
		return instance;
	}
	
	private MemberDAO memberDAO = MemberDAOImpl.getInstance();
	
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public void login(String id, String pwd) throws SQLException, NotFoundIDException, InvalidPasswordException {
		MemberVO member = memberDAO.selectMemberById(id);
		if(member == null) throw new NotFoundIDException();
		if(!pwd.equals(member.getPwd())) throw new InvalidPasswordException();
	}

	@Override
	public List<MemberVO> getMemberList() throws SQLException {
		List<MemberVO> memberList = memberDAO.selectMemberList();
		return memberList;
	}

	@Override
	public MemberVO getMember(String id) throws SQLException {
		MemberVO member = memberDAO.selectMemberById(id);
		return member;
	}

	@Override
	public void regist(MemberVO member) throws SQLException {
		memberDAO.insertMember(member);
	}

	@Override
	public void modify(MemberVO member) throws SQLException {
		memberDAO.updateMember(member);
	}

	@Override
	public void remove(String id) throws SQLException {
		memberDAO.deleteMember(id);
	}

	@Override
	public void disabledMember(String id) throws SQLException {
		memberDAO.disabledMember(id);
	}

	@Override
	public void enabledMember(String id) throws SQLException {
		memberDAO.enabledMember(id);
	}

}
