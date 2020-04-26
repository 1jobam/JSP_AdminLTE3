<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<title>자료등록</title>
<body>
  <!-- Content Wrapper. Contains page content -->
  <div>
    <jsp:include page="content_header.jsp">
    	<jsp:param value="자료실" name="subject"/>
		<jsp:param value="등록" name="item"/>
		<jsp:param value="list.do" name="url"/>    	
    </jsp:include>
    <!-- Main content -->
    <section class="content container-fluid">
		<div class="row">
			<div class="col-md-12">
				<div class="card card-outline card-info">
					<div class="card-header">
						<h4>글등록</h4>
					</div><!--end card-header  -->
					<div class="card-body">
					<!-- 데이타폼형식 enctype="multipart/form-data" --> 
						<form enctype="multipart/form-data" role="form" method="post" action="regist.do" name="registForm">
							<div class="form-group">
								<label for="writer">작성자</label> 
								<input type="text" id="writer" readonly
									name="writer" class="form-control" value="${loginUser.id }">
							</div>
							<div class="form-group">
								<label for="title">제 목</label> 
								<input type="text" id="title"
									name='title' class="form-control" placeholder="제목을 쓰세요">
							</div>
							<div class="form-group">
								<label for="content">내 용</label>
								<textarea class="form-control" name="content" id="content" rows="5"
									placeholder="1000자 내외로 작성하세요."></textarea>
							</div>
							<div class="form-group">								
								<div class="card card-outline card-success">
									<div class="card-header">
										<h5 style="display:inline;line-height:40px;">첨부파일 : </h5>
										&nbsp;&nbsp;<button class="btn btn-xs btn-primary" 
										type="button" id="addFileBtn">Add File</button>
									</div>									
									<div class="card-footer fileInput">
									</div>
								</div>
							</div>
						</form>
					</div><!--end card-body  -->
					<div class="card-footer">
						<button type="button" class="btn btn-primary" id="registBtn">등 록</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn" id="cancelBtn" onclick="CloseWindow();">취 소</button>
					</div><!--end card-footer  -->
				</div><!-- end card -->				
			</div><!-- end col-md-12 -->
		</div><!-- end row -->
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

<%@ include file="/WEB-INF/views/commons/summernote.jsp" %>

<%-- <jsp:include page="regist_js.jsp" /> --%>
<jsp:include page="attach_js.jsp" />

<script>
	$('#registBtn').on('click',function(e){
		var form=document.registForm;
		
		form.encoding = "application/x-www-form-urlencoded"; 

		if(form.title.value == ""){
			alert("제목은 필수 입니다.");
			return;
		}
		
		if(form.content.value.length >= 1000){
			alert("내용은 1000자 이하입니다.");
			return;
		}
		
		form.submit();
	});
</script>

</body>






  
  