<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<script>
	var formObj = $("form[role='form']");
	
	$("#modifyBtn").on('click',function(data){
		formObj.attr("action", "modifyForm.do");
		formObj.submit();
	});
	
	$('#removeBtn').on('click',function(data){
		formObj.attr("action","remove.do");
		formObj.attr("method","post");
		formObj.submit();
	});
	
	$('#listBtn').on('click',function(data){
		window.close();
		window.opener.location.href="list.do";
	});
</script>