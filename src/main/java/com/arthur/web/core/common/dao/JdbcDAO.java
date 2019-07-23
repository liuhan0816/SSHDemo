package com.arthur.web.core.common.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

/**
 * @ClassName JdbcDAO
 * @Description Jdbc操作数据库
 * @Author liuhan
 * @Date 2019/6/28 16:45
 * @Version 1.0
**/
public abstract class JdbcDAO extends JdbcDaoSupport {
	

	public abstract String createPageSql(String sql, int begin, int rowCount);
	
	/**
	 * 根据sql语句查找
	 * @param sql
	 * @return
	 */
    public List executeQuery(String sql){
    	List list = this.getJdbcTemplate().queryForList(sql);
		return list;
    }

    public List executeQuery(String sql, Object[] objs){
    	List list = this.getJdbcTemplate().queryForList(sql,objs);
    	return list;
    }
    
	/**
	 * 根据sql语句分页查找
	 * 
	 * @param sql
	 * @param begin
	 * @param rowCount
	 * @return List
	 */
	public List executeQuery(String sql, int begin, int rowCount) {

		String str=createPageSql(sql,begin,rowCount);
		List list = this.getJdbcTemplate().queryForList(str);
		return list;
	}

	/**
	 * 根据sql语句分页查找（对应Oracle数据库）
	 * 
	 * @param sql
	 * @param objs
	 * @param begin
	 * @param rowCount
	 * @return List
	 */
	public List executeQuery(String sql, Object[] objs, int begin, int rowCount) {

		String str=createPageSql(sql,begin,rowCount);
		List list = this.getJdbcTemplate().queryForList(str,
				objs);
		return list;
	}

	/**
	 * 根据sql语句得到行数
	 * 
	 * @param sql
	 * @return Integer
	 */
	public Integer getRowCount(String sql) {
		sql = "select count(*) from (" + sql + ")  t";
		int count = this.getJdbcTemplate().queryForObject(sql,Integer.class);
		return new Integer(count);
	}

	/**
	 * 根据sql语句得到行数（带查询参数）
	 * 
	 * @param sql
	 * @param values
	 * @return Integer
	 */
	public Integer getRowCount(String sql, Object[] values) {
		sql = "select count(*) from (" + sql + ")  t";
		int count = this.getJdbcTemplate().queryForObject(sql, values,Integer.class);
		return new Integer(count);
	}
   
	/**
	 * 根据sql语句查找，返回list，list中包含的是cls的对象
	 * @param sql
	 * @param cls
	 * @return
	 */
	public List executeQuery(String sql, Class cls) {
		return executeQuery(sql, null, cls);
	}
	
	
    /**
     * 根据sql语句查找，返回list，list中包含的是cls的对象
     * @param sql
     * @param objs
     * @param cls
     * @return
     */
	public List executeQuery(String sql, Object[] objs, Class cls) {
		if (objs != null&&objs.length>0) {
			return this.getJdbcTemplate().query(sql, objs,
					new BeanPropertyRowMapper(cls));
		} else {
			return this.getJdbcTemplate().query(sql,
					new BeanPropertyRowMapper(cls));
		}
	}

	public List executeQuery(String sql, Class cls, int begin, int rowCount) {

		return executeQuery(sql, null, cls, begin, rowCount);
	}

	public List executeQuery(String sql, Object[] objs, Class cls, int begin,
			int rowCount) {
		int end = begin + rowCount;
		StringBuffer sqlBuffer = new StringBuffer(
				"select * from ( select row_.*, rownum rownum_ from ( ");
		sqlBuffer.append(sql);
		sqlBuffer.append(" )");
		sqlBuffer.append(" row_ where rownum <=" + end + ") where rownum_ > "
				+ begin);
		return executeQuery(sqlBuffer.toString(), objs, cls);
	}
	
	public  int update(String sql ,Object obj){
		NamedParameterJdbcTemplate  template=new NamedParameterJdbcTemplate(this.getDataSource());
		SqlParameterSource  source=new BeanPropertySqlParameterSource(obj);
		return template.update(sql, source);
		
		
	}
}
