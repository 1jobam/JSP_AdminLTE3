<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<script>
	alert("자료 삭제를 성공하셨습니다.");
	window.close();
// 	window.opener.location.href="list.do${pageMaker.makeQuery()}";
	opener.parent.searchList_go(${pageMaker.cri.page},"<%=request.getContextPath()%>/pds/list.do");
</script>