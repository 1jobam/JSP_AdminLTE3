package com.jsp.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.jsp.dto.MemberVO;
import com.jsp.exception.InvalidPasswordException;
import com.jsp.exception.NotFoundIDException;
import com.jsp.request.SearchCriteria;

public interface MemberService {

	//로그인
	void login(String id, String pwd)throws SQLException,NotFoundIDException,InvalidPasswordException;
	
	//회원리스트 조회
	List<MemberVO> getMemberList()throws SQLException;
	Map<String, Object> getMemberList(SearchCriteria cri) throws SQLException;
	
	//회원정보조회
	MemberVO getMember(String id)throws SQLException;
	
	//회원등록
	void regist(MemberVO member)throws SQLException;
	
	//회원수정
	void modify(MemberVO member)throws SQLException;
	
	//회원삭제
	void remove(String id)throws SQLException;
	
	//회원 정지
	void disabledMember(String id) throws SQLException;
	
	//회원 정지해제
	void enabledMember(String id) throws SQLException;
}
