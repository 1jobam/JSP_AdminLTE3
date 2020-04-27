package com.jsp.action.pds;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.AttachVO;
import com.jsp.dto.PdsVO;
import com.jsp.service.PdsService;

public class RemovePdsAction implements Action{

	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "pds/remove_success";

		int pno = Integer.parseInt(request.getParameter("pno"));
	
		try {
			//존재하는 파일삭제
			PdsVO pds = pdsService.read(pno);
			List<AttachVO> attach = pds.getAttachList();
			for(AttachVO attachList : attach) {
				String attachRemove = attachList.getUploadPath() + File.separator + attachList.getFileName();
				System.out.println(attachRemove);
				File file = new File(attachRemove);
				if(file.exists()) {
					file.delete();
				}
			}
			//db 삭제
			pdsService.remove(pno);
		} catch (SQLException e) {
			e.printStackTrace();
			url = "pds/remove_fail";
		}
		
		return url;
	}

}
