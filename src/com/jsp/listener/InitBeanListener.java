package com.jsp.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.dao.BoardDAO;
import com.jsp.dao.MemberDAO;
import com.jsp.dao.MemberDAOImpl;
import com.jsp.dao.ReplyDAO;
import com.jsp.dao.ReplyDAOImpl;
import com.jsp.service.BoardService;
import com.jsp.service.BoardServiceImpl;
import com.jsp.service.MemberService;
import com.jsp.service.MemberServiceImpl;
import com.jsp.service.ReplyService;
import com.jsp.service.ReplyServiceImpl;

public class InitBeanListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent ctxEvent) {
    	String sqlSessionFactoryType = 
       		 ctxEvent.getServletContext().getInitParameter("sqlSessionFactory");
    	String daoType = ctxEvent.getServletContext().getInitParameter("memberDAO");
    	String boardDao = ctxEvent.getServletContext().getInitParameter("boardDAO");
    	String replyDao = ctxEvent.getServletContext().getInitParameter("replyDAO");
    	/*String pdsDao = ctxEvent.getServletContext().getInitParameter("pdsDAO");    	
    	String attachDao = ctxEvent.getServletContext().getInitParameter("attachDAO");*/
    	
		MemberDAO memberDAO=null;
		BoardDAO boardDAO=null;
		ReplyDAO replyDAO=null;
	/*	PdsDAO pdsDAO =null;
		AttachDAO attachDAO =null;*/
	
			try {
				
				SqlSessionFactory sqlSessionFactory = 
			        		 (SqlSessionFactory)Class.forName(sqlSessionFactoryType).newInstance();
				
				memberDAO=(MemberDAO)Class.forName(daoType).newInstance();
				((MemberDAOImpl)memberDAO).setSessionFactory(sqlSessionFactory);
				boardDAO=(BoardDAO)Class.forName(boardDao).newInstance();
				replyDAO=(ReplyDAO)Class.forName(replyDao).newInstance();
				((ReplyDAOImpl)replyDAO).setSessionFactory(sqlSessionFactory);
				
				/*pdsDAO = (PdsDAO)Class.forName(pdsDao).newInstance();				
				attachDAO = (AttachDAO)Class.forName(attachDao).newInstance();*/
				
			} catch (Exception e){
				e.printStackTrace();
				System.out.println("Bean 조립 실패");
				return;
			}	
		
				
		//인스턴스 할당
		MemberService service = MemberServiceImpl.getInstance();
	BoardService boardService = BoardServiceImpl.getInstance();	
		ReplyService replyService = ReplyServiceImpl.getInstance();
	//PdsService pdsService = PdsServiceImpl.getInstance();
		
		//조립
		((MemberServiceImpl)service).setMemberDAO(memberDAO);
		
		((BoardServiceImpl)boardService).setBoardDAO(boardDAO);		
//		((BoardServiceImpl)boardService).setReplyDAO(replyDAO);
		
		((ReplyServiceImpl)replyService).setReplyDAO(replyDAO);
		
		//((PdsServiceImpl)pdsService).setAttachDAO(attachDAO);
		//((PdsServiceImpl)pdsService).setPdsDAO(pdsDAO);
		
		System.out.println("with Listener : MemberDao 조립 완성 : "+memberDAO);
		System.out.println("with Listener : BoardDAO : "+boardDAO);
		System.out.println("with Listener : ReplyDAO : "+replyDAO);
		//System.out.println("with Listener : PdsDAO : "+pdsDAO);
		//System.out.println("with Listener : AttachDAO : "+attachDAO);
    }

    public void contextDestroyed(ServletContextEvent arg0) {
    }
	
}
