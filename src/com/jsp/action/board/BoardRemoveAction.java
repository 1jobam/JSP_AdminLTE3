package com.jsp.action.board;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.service.BoardService;
import com.jsp.service.BoardServiceImpl;

public class BoardRemoveAction implements Action{
	
	private BoardService boardService = BoardServiceImpl.getInstance();
	
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "board/remove_success";
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		try {
			boardService.remove(bno);
		} catch (SQLException e) {
			e.printStackTrace();
			url = "board/remove_fail";
		}
		
		return url;
	}

}
