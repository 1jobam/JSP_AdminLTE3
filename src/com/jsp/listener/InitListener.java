package com.jsp.listener;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.dao.MemberDAOImpl;
import com.jsp.service.MemberServiceImpl;

@WebListener
public class InitListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent sce)  {
    	
    }

    public void contextInitialized(ServletContextEvent sce)  {
    	String SqlSessionType = sce.getServletContext().getInitParameter("sqlSessionFactory");
    	String DaoType = sce.getServletContext().getInitParameter("memberDAO");
    	
    	try {
    		
    		MemberDAOImpl memberDAOImpl = (MemberDAOImpl) Class.forName(DaoType).newInstance();
    		SqlSessionFactory sessionFactory = (SqlSessionFactory)Class.forName(SqlSessionType).newInstance();
    		
    		//리프렉션
    		
//    		Class<?> cls = Class.forName(DaoType);
//    		
//    		Method setSqlSessionFactory = cls.getMethod("setSessionFactory", SqlSessionFactory.class);
//    		
//    		Object obj = cls.newInstance();
//    		setSqlSessionFactory.invoke(obj, sessionFactory);
			
			memberDAOImpl.setSessionFactory(sessionFactory);
			MemberServiceImpl.getInstance().setMemberDAO(memberDAOImpl);
			//InvocationTargetException | NoSuchMethodException | 			
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | IllegalArgumentException | SecurityException e) {
			e.printStackTrace();
		}
    	
    	
    }
    
	
}
