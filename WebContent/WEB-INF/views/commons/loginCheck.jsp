<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<script>
	if(window.opener){
		alert("세션이 만료되었습니다.\n다시 로그인 하시기 바랍니다.")
		window.close();
		window.opener.location.reload();
	}else{
		location.href="<%=request.getContextPath()%>/commons/loginForm.do";
	}
</script>