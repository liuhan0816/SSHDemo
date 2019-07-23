package com.arthur.web.core.common.dao.jdbc;

import com.arthur.web.core.common.dao.JdbcDAO;

public class OracleJdbcDAO extends JdbcDAO {
	public  String createPageSql(String sql, int begin, int rowCount){
		int end = begin + rowCount;
		StringBuffer sqlBuffer = new StringBuffer(
				"select * from ( select row_.*, rownum rownum_ from ( ");
		sqlBuffer.append(sql);
		sqlBuffer.append(" )");
		sqlBuffer.append(" row_ where rownum <=" + end + ") where rownum_ > "
				+ begin);
		return sqlBuffer.toString();
	}
}
