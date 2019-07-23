package com.arthur.web.core.common.dao.jdbc;

import com.arthur.web.core.common.dao.JdbcDAO;

public class SqlServerJdbcDAO extends JdbcDAO {
	public  String createPageSql(String sql, int begin, int rowCount){
		int length = sql.indexOf("order by");
		String order = sql.substring(length, sql.length());
		String other = sql.substring(0, length);
		String str = "SELECT * FROM (select ROW_NUMBER() Over(" + order + ")"
				+ " as rowNum, * from( " + other
				+ " ) as t) as t1 where rowNum> " + begin + " and rowNum<="
				+ (begin + rowCount);
		return str;
	}
}
