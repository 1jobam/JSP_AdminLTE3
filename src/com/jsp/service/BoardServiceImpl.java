package com.jsp.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jsp.dao.BoardDAO;
import com.jsp.dto.BoardVO;
import com.jsp.dto.MemberVO;
import com.jsp.request.PageMaker;
import com.jsp.request.SearchCriteria;

public class BoardServiceImpl implements BoardService {

	private static BoardServiceImpl instance = new BoardServiceImpl();
	
	private BoardServiceImpl() {}
	
	public static BoardServiceImpl getInstance() {
		return instance;
	}
	
	private BoardDAO boardDAO;

	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}

	@Override
	public Map<String, Object> getBoardList(SearchCriteria cri) throws SQLException {
		List<BoardVO> boardList = boardDAO.selectBoardCriteria(cri);

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(boardDAO.selectBoardCriteriaTotalCount(cri));

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("boardList", boardList);
		dataMap.put("pageMaker", pageMaker);

		System.out.println(boardList);
		System.out.println(pageMaker);
		System.out.println(dataMap);
		
		
		return dataMap;
	}

	@Override
	public BoardVO getBoard(int bno) throws SQLException {
		BoardVO board = boardDAO.selectBoardByBno(bno);
		return board;
	}

	@Override
	public BoardVO getBoardForModify(int bno) throws SQLException {
		BoardVO board = boardDAO.selectBoardByBno(bno);
		return board;
	}

	@Override
	public void write(BoardVO board) throws SQLException {
		boardDAO.insertBoard(board);
	}

	@Override
	public void modify(BoardVO board) throws SQLException {
		boardDAO.updateBoard(board);
	}

	@Override
	public void remove(int bno) throws SQLException {
		boardDAO.deleteBoard(bno);
	}

}
