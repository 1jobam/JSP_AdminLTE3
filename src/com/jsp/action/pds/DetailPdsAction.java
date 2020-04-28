package com.jsp.action.pds;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.AttachVO;
import com.jsp.dto.PdsVO;
import com.jsp.request.PageMaker;
import com.jsp.request.SearchCriteria;
import com.jsp.service.PdsService;
import com.jsp.utils.CreatePageMaker;
import com.jsp.utils.MakeFileName;

public class DetailPdsAction implements Action{

	private PdsService pdsService;
	
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "pds/detail";
		
		int pno = Integer.parseInt(request.getParameter("pno"));
		
		String from = request.getParameter("from");
		
		try {
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		try {
			PdsVO pds = null;
			if(from != null && from.equals("modify")) {
				pds = pdsService.read(pno);
			}else {
				pds = pdsService.getPds(pno);				
			}
			
			List<AttachVO> renamedAttachList = MakeFileName.parseFileNameFromAttachs(pds.getAttachList(), "\\$\\$");
			pds.setAttachList(renamedAttachList);
			request.setAttribute("pds", pds);
			
			PageMaker pageMaker = CreatePageMaker.pageMaker(request);
			
			request.setAttribute("pageMaker", pageMaker);
		} catch (Exception e) {
			e.printStackTrace();
			url = "error/500_error";
		}
		
		return url;
	}

}
