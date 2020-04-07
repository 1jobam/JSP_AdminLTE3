package com.jsp.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class OracleMyBatisSqlSessionFactoryBuilder {

	private OracleMyBatisSqlSessionFactoryBuilder() {}
	
	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		String config = "com/jsp/mybatis/sqlConfig.xml";
		
		Reader reader;
		try {
			reader = Resources.getResourceAsReader(config);
			
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			
			/*reader.close();*/
			
			System.out.println("sqlSessionFactory 성공했습니다.");
		} catch (IOException e) {
			System.out.println("sqlSessionFactory 실패했습니다.");
			e.printStackTrace();
		}
		
	}
	
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	
}
