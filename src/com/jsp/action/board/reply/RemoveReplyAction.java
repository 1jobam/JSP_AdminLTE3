package com.jsp.action.board.reply;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsp.action.Action;
import com.jsp.request.DeleteReplyRequest;
import com.jsp.request.PageMaker;
import com.jsp.request.SearchCriteria;
import com.jsp.service.ReplyService;

public class RemoveReplyAction implements Action{
	
	private ReplyService replyService;
	
	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = null;
		
		ObjectMapper mapper = new ObjectMapper();
		DeleteReplyRequest removeReq = mapper.readValue(request.getReader(), DeleteReplyRequest.class);
		
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		try {
			replyService.removeReply(removeReq.getRno());
			
			SearchCriteria cri = new SearchCriteria();
			
			Map<String,Object> dataMap = replyService.getReplyList(removeReq.getBno(), cri);
			PageMaker pageMaker = (PageMaker) dataMap.get("pageMaker");
			int page = removeReq.getPage();
			int realEndPage = pageMaker.getRealEndPage();
			if(page>realEndPage) {page=realEndPage;}
			
			out.print("SUCCESS,"+page);
			
		} catch (SQLException e) {
			e.printStackTrace();
			out.print("FAIL,1");
		}finally {
			if(out != null) out.close();
		}
		
		return url;
	}

}
