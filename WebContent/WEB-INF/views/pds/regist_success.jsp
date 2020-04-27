<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<script>
	alert("자료실 등록이 완료되었습니다.");
	window.close();
	window.opener.location.href="list.do?page=1&perPageNum=10";
</script>