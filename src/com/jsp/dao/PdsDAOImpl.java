package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.dto.PdsVO;
import com.jsp.request.SearchCriteria;

public class PdsDAOImpl implements PdsDAO {
		
	private SqlSessionFactory sessionFactory;
	public void setSessionFactory(SqlSessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<PdsVO> selectPdsCriteria(SearchCriteria cri) throws SQLException {
		SqlSession session = sessionFactory.openSession();
		
		int offset=cri.getPageStartRowNum();
		int limit=cri.getPerPageNum();
		RowBounds rowBounds=new RowBounds(offset,limit);
		
		List<PdsVO> pdsList=
		   session.selectList("Pds-Mapper.selectSearchPdsList",cri,rowBounds);	
			
		session.close();
		return pdsList;
	}

	@Override
	public int selectPdsCriteriaTotalCount(SearchCriteria cri) throws SQLException {
		SqlSession session = sessionFactory.openSession();
		
		int count= session.selectOne("Pds-Mapper.selectSearchPdsListCount",cri);
		
		session.close();
		return count;
	}

	@Override
	public PdsVO selectPdsByPno(int pno) throws SQLException {
		SqlSession session = sessionFactory.openSession();
		
		PdsVO pds=session.selectOne("Pds-Mapper.selectPdsByPno",pno);
		
		session.close();
		return pds;
	}

	@Override
	public void insertPds(PdsVO pds) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		
		session.update("Pds-Mapper.insertPds",pds);
		
		session.close();

	}

	@Override
	public void updatePds(PdsVO pds) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		
		session.update("Pds-Mapper.updatePds",pds);
		
		session.close();

	}

	@Override
	public void deletePds(int pno) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		
		session.update("Pds-Mapper.deletePds",pno);
		
		session.close();

	}

	@Override
	public void increaseViewCnt(int pno) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		
		session.update("Pds-Mapper.increaseViewCnt",pno);
		
		session.close();

	}

	@Override
	public int getSeqNextValue() throws SQLException {
		SqlSession session = sessionFactory.openSession();
		
		int pno=session.selectOne("Pds-Mapper.selectPdsSeqNext");
		
		session.close();
		return pno;
	}

}
