<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<head>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/bootstrap/plugins/summernote/summernote-bs4.css" />
</head>

<!-- Summernote -->
<script src="<%=request.getContextPath()%>/resources/bootstrap/plugins/summernote/summernote-bs4.min.js"></script>

<script>
	$(function(){
		$('#content').summernote({
			
			placeholder:'여기에 내용을 적으세요.',
			height:250,
			callbacks:{
				onImageUpload : function(files, editor, welEditable){
// 					alert("image selected!");
					for(var i = files.length -1; i >= 0; i--){
						if(files[i].size > 1024*1024*5){
							alert("이미지는 5MB 미만 입니다.");
							return;
						}
						for(var i = files.length - 1; i >= 0; i--){
							sendFile(files[i], this);
						}
					}
				},
				onMediaDelete : function(target){
					deleteFile(target[0].src);
				}
			}
			
		});
		
	});
		function sendFile(file, el){
			
			var form_data = new FormData();
			form_data.append("file", file);
			
			$.ajax({
				data : form_data,
				type : "post",
				url : '<%=request.getContextPath()%>/uploadImg.do',
				cache : false,
				contentType : false,
				processData : false,
				success : function(img_url){
					$(el).summernote('editor.insertImage', img_url);
				}
			});		
	};
		function deleteFile(src){
			var splitSrc = src.split("=");
			var fileName = splitSrc[splitSrc.length-1];
			
					//"fileName":fileName 이와 같음
			var fileData = {
					fileName:fileName
			};
			
			$.ajax({
				url:"<%=request.getContextPath()%>/deleteImg.do",
				data:JSON.stringify(fileData),
				type:"post",
				success:function(res){
					console.log(res);
				}
			});
		};
</script>