package com.arthur.web.core.common.dao;

import com.arthur.web.core.spring.bean.SpringBeanUtil;

/**
 *@ClassName DAOFactory
 *@Description dao工厂支持jdbc+hibernate
 *@Author liuhan
 *@Date 2019/6/28 16:28
 *@Version 1.0
 **/
public class DAOFactory {

	/**
	* @Author liuhan
	* @Description 获取HibernateDAO
	* @Date 2019/6/28 16:29
	* @Param [id]
	* @Return com.arthur.web.core.common.dao.HibernateDAO
	*/
	public static HibernateDAO getHibernateDAO(String id){
		return SpringBeanUtil.getBean(id,HibernateDAO.class);
//		BeanManager beanManager= BeanManager.getInstance();
//		return (HibernateDAO)beanManager.getBean(id);
	}

	/**
	* @Author liuhan
	* @Description 获取JdbcDAO
	* @Date 2019/6/28 16:29
	* @Param [id]
	* @Return com.arthur.web.core.common.dao.JdbcDAO
	*/
	public static JdbcDAO getJdbcDAO(String id){
//		BeanManager beanManager=BeanManager.getInstance();
//		return (JdbcDAO)beanManager.getBean(id);
		return SpringBeanUtil.getBean(id,JdbcDAO.class);
	}
}

