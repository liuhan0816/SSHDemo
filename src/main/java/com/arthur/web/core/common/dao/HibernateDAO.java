package com.arthur.web.core.common.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName HibernateDAO
 * @Description Hibernate操作数据库
 * @Author liuhan
 * @Date 2019/6/28 16:45
 * @Version 1.0
**/
public class HibernateDAO extends HibernateDaoSupport{

	private static final Log log = LogFactory.getLog(HibernateDAO.class);

	@Resource(name="sessionFactory")
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		log.info("注入sessionFactory:"+sessionFactory);
		super.setSessionFactory(sessionFactory);
	}

	public HibernateDAO() {
		super();
	}

	/**
	 * save object 插入一条数据
	 * 
	 * @param obj
	 */
	public void save(Object obj) {
	
		log.debug("save  instance");
		try {
			getHibernateTemplate().save(obj);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/**
	 * 更新一条记录
	 * 
	 * @param obj
	 */
	public void update(Object obj) {
		log.debug("update  instance");
		try {
			getHibernateTemplate().update(obj);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;

		}
	}

	/**
	 * 插入或更新一条记录
	 * 
	 * @param obj
	 */
	public void saveOrUpdate(Object obj) {
		log.debug("update  instance");
		try {
			
			getHibernateTemplate().saveOrUpdate(obj);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;

		}
	}

	
	/**
	 * 脱离对象管理
	 * 
	 * @param obj
	 */
	public void evict(Object obj) {
		log.debug("evict  instance");
		try {

			getHibernateTemplate().evict(obj);
			log.debug("evict successful");
		} catch (RuntimeException re) {
			log.error("evict failed", re);
			throw re;

		}
	}
	/**
	 * 删除一条记录
	 * 
	 * @param obj
	 */
	public void delete(Object obj) {
		log.debug("deleting  instance");
		try {
			getHibernateTemplate().delete(obj);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/**
	 * merge一条记录
	 * 
	 * @param obj
	 * @return
	 */
	public Object merge(Object obj) {
		log.debug("merging  instance");
		try {
			Object result = (Object) getHibernateTemplate().merge(obj);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	protected void initDao() {
		// do nothing
	}


	/**
	 * 根据对象进行查找
     *
     * @param instance
	 * @return
	 */
	public List findByExample(final Object instance) {

		return (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				String className = instance.getClass().getName();

				log.debug("finding " + className + " instance by example");

				try {
			/*		//1.创建CriteriaBuilder对象
					//2.获取CriteriaQuery对象
					//注意导入的包是import javax.persistence.criteria.CriteriaQuery;
					CriteriaQuery<?> criteriaQuery=session.getCriteriaBuilder().createQuery(instance.getClass());
					//3.指定根条件
					criteriaQuery.from(instance.getClass());
					//4执行查询
					List list=session.createQuery(criteriaQuery).getResultList();*/
					List results = session.createCriteria(className).add(
							Example.create(instance)).list();

					log.debug("find by example successful, result size: "
							+ results.size());
					return results;
				} catch (RuntimeException re) {
					log.error("find by example failed", re);
					throw re;
				}
			}
		});
	}

	/**
	 * 根据hql语句进行查询
	 * 
	 * @param hql
	 * @return
	 */
	public List executeQuery(final String hql) {
		return executeQuery(hql,null);
	}
	
	/**
	 * 根据hql语句进行查询，支持占位符参数
	 * @param hql
	 * @param values
	 * @return
	 */
	public List executeQuery(final String hql,final Object[] values) {
		log.debug("executeQuery hql by " + hql);
		try {
			List list = getHibernateTemplate().find(hql, values);
	
			return list;
		} catch (RuntimeException re) {
			log.error("executeQuery hql failed by " + hql, re);
			throw re;
		}
	}
	
	/**
	 * 根据hql语句分页查询（带查询参数）
	 * @param hql
	 * @param values
	 * @param begin
	 * @param rowCount
	 * @return
	 */
	public List executeQuery(final String hql,final Object[] values,final int begin, final int rowCount){
		return (List) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						Query query = session.createQuery(hql);
						if (values != null) {
							for (int i = 0; i < values.length; i++) {
								query.setParameter(i, values[i]);
							}
						}
						query.setFirstResult(begin);
						query.setMaxResults(rowCount);
						List results = query.list();
			
						return results;

					}
				});
	}
	
	/**
	 * 根据hql语句分页查询
	 * @param hql
	 * @param begin
	 * @param rowCount
	 * @return
	 */
	public List executeQuery(final String hql,final int begin, final int rowCount){
		return executeQuery(hql,null,begin,rowCount);
	}
	
	
    /**
     * 根据hql语句得到行数
     * @param hql
     * @return
     */
	public Integer getRowCount(final String hql) {

		return getRowCount(hql,null);
	}
	
	
	 /**
	  * 根据hql语句得到行数（带查询参数）
	  * @param hql
	  * @return
	  */
	public Integer getRowCount(final String hql,final Object[] values) {

		return (Integer) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException {
						String counthql = hql.substring(hql.toLowerCase()
								.indexOf("from"));
						counthql = "select count(*) " + counthql;
						Query query = session.createQuery(counthql);
						if (values != null) {
							for (int i = 0; i < values.length; i++) {
								query.setParameter(i, values[i]);
							}
						}
						Object obj = query.list().get(0);
						Integer totalCount = new Integer(obj.toString());						
						return totalCount;
					}
				});
	}

	
	
	
	/**
	 * 根据hql更新
	 * @param hql
	 */
	public int update( String hql){
		return getHibernateTemplate().bulkUpdate(hql);
		

	}
	
	/**
	 * 根据hql更新
	 * @param hql
	 */
    public void executeUpdate(String hql)
    {
        getHibernateTemplate().bulkUpdate(hql);
    }
    
    /**
	 * 对hibernate 的flush
     */
    public void flush()
    {
        getHibernateTemplate().flush();
    }
	
	/**
	 * 根据hql更新（带查询参数）
	 * @param hql
	 */
	public int executeUpdate( String hql, final Object[] values){
		return getHibernateTemplate().bulkUpdate(hql,values);
		

	}
		
	
	/**
	 * 根据id进行查找
	 * @param entityClass
	 * @param id
	 * @return
	 */	
	public Object load(final Class entityClass, final Serializable id){
		Object object=getHibernateTemplate().get(entityClass, id);
		return object;
	}
	
	
	public Session getHibernateSession(){
		return this.getSessionFactory().getCurrentSession();
	}
}
