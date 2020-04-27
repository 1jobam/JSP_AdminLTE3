package com.jsp.action.pds;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.AttachVO;
import com.jsp.dto.PdsVO;
import com.jsp.service.PdsService;
import com.jsp.utils.MakeFileName;

public class GetAttachAction implements Action{
	
	private PdsService pdsService;
	
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = null;
		
		int pno = Integer.parseInt(request.getParameter("pno"));
		int ano = Integer.parseInt(request.getParameter("ano"));
		
		try {
			PdsVO pds = pdsService.getPds(pno);
			
			List<AttachVO> attachList = pds.getAttachList();
			
			String fileName = null;
			String filePath = null;
			
			for(AttachVO attach : attachList) {
				if(ano == attach.getAno()) {
					fileName = attach.getFileName();
					filePath = attach.getUploadPath();
					break;
				}
			}
			
				sendFile(request,response,fileName,filePath);
			
		} catch (Exception e) {
			e.printStackTrace();
			url = "error/500_error";
		}
		
		return url;
	}

	private void sendFile(HttpServletRequest request, HttpServletResponse response, String fileName, String filePath) throws Exception{
		
		filePath = filePath + File.separator + fileName;
		
		// 보낼 파일 설정.
		File downloadFile = new File(filePath);
		FileInputStream inStream = new FileInputStream(downloadFile);
		
		ServletContext context = request.getServletContext();
		//파일 포맷으로 MIME를 결정한다.
		String mimeType = context.getMimeType(filePath);
		if(mimeType == null) {
			mimeType = "application/octet-stream";
		}
		
		//response 수정.
		response.setContentType(mimeType);
		response.setContentLength((int)downloadFile.length());
		
		//파일명 한글깨짐 방지 : utf-8
		String downloadFileName = new String(downloadFile.getName().getBytes("utf-8"), "ISO-8859-1");
		downloadFileName = MakeFileName.parseFileNameFromUUID(downloadFileName, "\\$\\$");
		
		// response header setting
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", downloadFileName);
		response.setHeader(headerKey, headerValue);
		
		// 파일 내보내기
		OutputStream outStream = null;
		try {
			outStream = response.getOutputStream();
			byte[] buffer = new byte[4096];
			int bytesRead = -1;
			
			while((bytesRead = inStream.read(buffer)) != -1){
				outStream.write(buffer, 0, bytesRead);
			}
		}finally {
			if(inStream!=null)inStream.close();
			if(outStream!=null)outStream.close();
		}
	}

}
