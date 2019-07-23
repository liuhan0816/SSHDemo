package com.arthur.web.core.common.dao.jdbc;

import com.arthur.web.core.common.dao.JdbcDAO;

public class MySqlJdbcDAO extends JdbcDAO {

	@Override
	public String createPageSql(String sql, int begin, int rowCount) {
		// TODO Auto-generated method stub
		StringBuffer sqlBuffer = new StringBuffer(sql);
		sqlBuffer.append(" LIMIT ").append(begin).append(",").append(rowCount);
		return sqlBuffer.toString();
	}

}
