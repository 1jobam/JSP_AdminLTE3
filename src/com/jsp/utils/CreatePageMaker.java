package com.jsp.utils;

import javax.servlet.http.HttpServletRequest;

import com.jsp.request.PageMaker;
import com.jsp.request.SearchCriteria;

public class CreatePageMaker {
	
	public static PageMaker pageMaker(HttpServletRequest request) throws Exception {
		int page = Integer.parseInt(request.getParameter("page"));
		int perPageNum = Integer.parseInt(request.getParameter("perPageNum"));
		String searchType = request.getParameter("searchType");
		String keyword = request.getParameter("keyword");
		
		SearchCriteria cri = new SearchCriteria();
		cri.setKeyword(keyword);
		cri.setPage(page);
		cri.setPerPageNum(perPageNum);
		cri.setSearchType(searchType);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		
		return pageMaker;
	}

}
