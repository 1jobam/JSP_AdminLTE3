<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<script>
	alert("${param.bno}번 게시판이 정상적으로 등록되었습니다.");
	window.close();
	window.opener.location.reload(true);
</script>