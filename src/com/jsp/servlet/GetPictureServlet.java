package com.jsp.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.utils.GetUploadPath;

/**
 * Servlet implementation class GetPictureServlet
 */
//@WebServlet("/member/picture/get")
public class GetPictureServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileName = request.getParameter("picture");
		String savedPath = GetUploadPath.getUploadPath("member.picture.upload");
		
		String filePath = savedPath+fileName;
		
		sendFile(response, filePath);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void sendFile(HttpServletResponse response, String filePath) throws ServletException, IOException{
		
		//보낼 파일 설정.
		File downloadFile = new File(filePath);
		FileInputStream inStream = new FileInputStream(downloadFile);
		
		ServletContext context = getServletContext();
		
		//파일 포맷으로 MIME를 결정한다.
		String mimeType = context.getMimeType(filePath);
		if(mimeType == null) {
			mimeType = "application/octet-stream";
		}
		System.out.println("MIME type : " + mimeType);
		
		// response 수정.
		response.setContentType(mimeType);
		response.setContentLength((int) downloadFile.length());
		
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
		response.setHeader(headerKey, headerValue);
		
		// 파일 내보내기
		OutputStream outStream = response.getOutputStream();
		byte[] buffer = new byte[4096];
		int bytesRead = -1;
		
		while((bytesRead = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}
		
		if(inStream != null) inStream.close();
		if(outStream != null) outStream.close();
		
	}

}
