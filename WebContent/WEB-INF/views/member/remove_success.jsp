<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<script>
	alert("${param.id} 님을 삭제합니다.");
	window.close();
	window.opener.location.reload(true);
</script>