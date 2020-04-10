<%@page import="com.jsp.dto.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/views/include/header.jsp" %>
						
	<div class="content-wrapper">
		<!-- Content Header (Page header) -->
		  <section class="content-header">
		  	<div class="container-fluid">
		  		<div class="row md-2">
		  			<div class="col-sm-6">
		  				<h1>회원리스트</h1>
		  			</div>
		  			<div class="col-sm-6">
		  				<ol class="breadcrumb float-sm-right">
					        <li class="breadcrumb-item">
					        	<a href="lis">
						        	<i class="fa fa-dashboard"></i> 회원관리
						        </a>
					        </li>
					        <li class="breadcrumb-item active">
					        	리스트
					        </li>		        
    	  				</ol>
  					</div>
  				</div>
  			</div>
  		</section>
       <!-- Main content -->
    	<section class="content">
    	  <div class="card">    
    	  	<div class="card-header with-border">
    	  		<c:if test="${loginUser.authority eq 'ROLE_ADMIN' }" >
    	  			<button type="button" class="btn btn-primary" 
    	  			onclick="OpenWindow('regist','회원등록',800,600);" >회원등록</button>
    	  		</c:if>
    	  		<div id="keyword" class="card-tools" style="width:350px;">
				  <div class="input-group row">		
				  <!-- search bar -->	  										
					<select class="form-control col-md-4" name="searchType" id="searchType">
						<option value=""  ${pageMaker.cri.searchType eq '' ? 'selected':''}>검색구분</option>
						<option value="i"  ${pageMaker.cri.searchType eq 'i' ? 'selected':''}>아이디</option>
						<option value="p"  ${pageMaker.cri.searchType eq 'p' ? 'selected':''}>전화번호</option>
						<option value="e"  ${pageMaker.cri.searchType eq 'e' ? 'selected':''}>이메일</option>
					</select>			
					<input  class="form-control" type="text" name="keyword" 
					placeholder="검색어를 입력하세요." value="${param.keyword }"/>
					<span class="input-group-append">
						<button class="btn btn-primary" type="button" 
						id="searchBtn" data-card-widget="search" onclick="searchList_go(1);">
							<i class="fa fa-fw fa-search"></i>
						</button>
					</span>
					<!-- end : search bar -->
				  </div>
				 </div>    	  		
    	  	</div>	  
    		<div class="card-body" style="text-align:center;">
    		  <div class="row">
	             <div class="col-sm-12">	
		    		<table class="table table-bordered"> 
	             		<tr>	
	             			<th>아이디</th>
	             			<th>이  름</th>
	             			<th>패스워드</th>
	             			<th>이메일</th>
	             			<th>전화번호</th>
	             		</tr>
	             		<%-- <%	             		
	             		List<MemberVO> memberList = (List<MemberVO>) request.getAttribute("memberList");	             		
	             		%> --%>
	             		
	             		<%-- <%
	             		if(memberList != null){
	             		%> --%>
	             		<c:if test="${!empty memberList }">
	             		
	             			<c:forEach var="member" items="${memberList }">
	             		<%-- <%
	             			for(MemberVO member : memberList){
	             				pageContext.setAttribute("member", member);
	             			%> --%>
	             			<tr>
	             				<td><a href="javascript:OpenWindow('detail?id=${member.id }','회원상세보기','800','600');" >${member.id}</a></td>
	             				<td>${member.name}</td>
	             				<td>${member.pwd}</td>
	             				<td>${member.email}</td>
	             				<td>${member.phone}</td>
	             			</tr>
	             		<%-- 	<%
	             			}
	             		%> --%>
	             			</c:forEach>
	             		</c:if>
	             		<%-- <%
	             		}else{
	             		%> --%>
	             		<c:if test="${empty memberList }">
	             			<tr>
	             				<td colspan="5">해당사항 없습니다.</td>
	             			</tr>
	             		</c:if>
	             		<%-- <%
	             		}
	             		%> --%>
				 	</table>	
            	</div>
           	</div>            
       	  </div>   
		  <div class="card-footer">
		  </div> <!-- card-footer -->
        </div> <!-- card  -->
      </section>	
    </div>

<%@ include file="/WEB-INF/views/include/footer.jsp" %>
