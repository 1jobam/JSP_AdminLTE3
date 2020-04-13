<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<script>
	if(window.opener){
		window.close();
		window.opener.location.reload();
	}else{
		alert("세션이 만료되었습니다.");
		location.href="<%=request.getContextPath()%>/commons/login";
	}
</script>