package com.jsp.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dto.MemberVO;
import com.jsp.utils.ViewResolver;

public class MemberDisabledFilter implements Filter {

	private List<String> exURLs = new ArrayList<String>();
	
	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpRes = (HttpServletResponse) response;

		String uri = httpReq.getRequestURI();
//		HttpSession session = httpReq.getSession();
		MemberVO loginUser = (MemberVO) httpReq.getSession().getAttribute("loginUser");
		
		/**
		 * 쌤방식
		 */
		if(loginUser != null && loginUser.getEnabled() == 1) {
			chain.doFilter(request, response);
			return;
		}
		
		for(String url : exURLs) {
			if(uri.contains(url)) {
				String urss = "commons/memberDisabledCheck";
				ViewResolver.view(httpReq, httpRes, urss);
				return;
			}
		}
			
		chain.doFilter(request, response);
		
		/**
		 * 내방식
		 */
/*		if(loginUser == null) {
			chain.doFilter(request, response);
			return;
		}
		
		if(loginUser.getEnabled() == 1) {
			chain.doFilter(request, response);
			return;
		}

		String reqUrl = httpReq.getRequestURI().substring(httpReq.getContextPath().length());
		System.out.println("reqURl : " + reqUrl);
		if (!(excludeCheck(reqUrl))) {
			chain.doFilter(request, response);
			return;
		}else {
			String url = "commons/memberDisabledCheck";
			ViewResolver.view(httpReq, httpRes, url);	
			return;
		}*/
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
		String excludeURLNames = fConfig.getInitParameter("checkURL");
		StringTokenizer st = new StringTokenizer(excludeURLNames, ",");
		while (st.hasMoreElements()) {
			String urlKey = st.nextToken();
			exURLs.add(urlKey);
		}
	}
	
//	private boolean excludeCheck(String url) {
//		for (String exURL : exURLs) {
//			if (url.contains(exURL)) {
//				return true;
//			}
//		}
//		return false;
//	}

}
