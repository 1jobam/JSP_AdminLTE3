package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.dto.AttachVO;

public class AttachDAOImpl implements AttachDAO{
	
	private SqlSessionFactory sessionFactory;
	public void setSessionFactory(SqlSessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void insertAttach(AttachVO attach) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		session.update("Attach-Mapper.insertAttach",attach);
		session.close();		
	}

	@Override
	public void deleteAttach(int ano) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		session.update("Attach-Mapper.deleteAttach",ano);		
		session.close();
		
	}

	@Override
	public List<AttachVO> selectAttachesByPno(int pno) throws SQLException {
		SqlSession session = sessionFactory.openSession();
		List<AttachVO> attachList=
				session.selectList("Attach-Mapper.selectAttachByPno",pno);
		session.close();
		return attachList;
	}

	@Override
	public void deleteAllAttach(int pno) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		session.update("Attach-Mapper.deleteAllAttach",pno);		
		session.close();		
	}
	@Override
	public AttachVO selectAttachByAno(int ano) throws SQLException {
		SqlSession session = sessionFactory.openSession();
		
		AttachVO attach=
			session.selectOne("Attach-Mapper.selectAttachByAno",ano);
		
		session.close();
		return attach;
	}

}
