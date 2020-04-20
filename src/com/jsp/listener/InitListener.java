package com.jsp.listener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.action.board.BoardListAction;
import com.jsp.dao.BoardDAOImpl;
import com.jsp.dao.MemberDAOImpl;
import com.jsp.service.BoardServiceImpl;
import com.jsp.service.MemberServiceImpl;

@WebListener
public class InitListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent sce)  {
    	
    }

    public void contextInitialized(ServletContextEvent sce)  {
    	String SqlSessionType = sce.getServletContext().getInitParameter("sqlSessionFactory");
    	String DaoType = sce.getServletContext().getInitParameter("memberDAO");
    	String BoardDaoType = sce.getServletContext().getInitParameter("boardDAO");
//    	String BoardServiceType = sce.getServletContext().getInitParameter("boardService");
    	
    	try {
    		
    		MemberDAOImpl memberDAOImpl = (MemberDAOImpl) Class.forName(DaoType).newInstance();
    		BoardDAOImpl boardDAOImpl = (BoardDAOImpl) Class.forName(BoardDaoType).newInstance();
//    		BoardServiceImpl boardServiceImpl = (BoardServiceImpl) Class.forName(BoardServiceType).newInstance();
    		SqlSessionFactory sessionFactory = (SqlSessionFactory)Class.forName(SqlSessionType).newInstance();
    		
    		//리프렉션
    		
//    		Class<?> cls = Class.forName(DaoType);
//    		
//    		Method setSqlSessionFactory = cls.getMethod("setSessionFactory", SqlSessionFactory.class);
//    		
//    		Object obj = cls.newInstance();
//    		setSqlSessionFactory.invoke(obj, sessionFactory);
			
			memberDAOImpl.setSessionFactory(sessionFactory);
			boardDAOImpl.setSessionFactory(sessionFactory);
			BoardServiceImpl.getInstance().setBoardDAO(boardDAOImpl);
			MemberServiceImpl.getInstance().setMemberDAO(memberDAOImpl);
			
			
			//InvocationTargetException | NoSuchMethodException | 			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException | SecurityException e) {
			e.printStackTrace();
		}
    	
    	
    }
    
	
}
