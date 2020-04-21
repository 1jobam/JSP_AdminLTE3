package com.jsp.action.summernote;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsp.action.Action;
import com.jsp.request.DeleteImgRequest;
import com.jsp.utils.GetUploadPath;

public class DeleteImgAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = null;
		
		ObjectMapper mapper = new ObjectMapper();
		DeleteImgRequest delReq = mapper.readValue(request.getReader(), DeleteImgRequest.class);
		
		String savePath = GetUploadPath.getUploadPath("board.img");
		String fileName = delReq.getFileName();
		
		File target = new File(savePath+File.separator+fileName);
		
		response.setContentType("text/plain;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		if(!target.exists()) {
			out.print(fileName+" 이미지를 삭제할 수 없습니다.");
		}else {
			target.delete();
			out.print(fileName+" 이미지를 삭제했습니다.");
		}
		
		
		return url;
	}

}
