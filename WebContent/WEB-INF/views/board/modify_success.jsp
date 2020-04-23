<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<script>
	alert("${param.bno}번째 게시판 수정을 성공하셨습니다.");
	location.href="detail.do?bno=${param.bno}";
	window.opener.location.reload(true);
</script>