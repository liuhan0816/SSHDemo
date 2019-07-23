package com.arthur.web.service.user;

import com.arthur.web.core.common.dao.DAOFactory;
import com.arthur.web.core.common.dao.HibernateDAO;
import com.arthur.web.core.common.dao.SqlAppender;
import com.arthur.web.core.service.BaseService;
import com.arthur.web.pojo.protal.TUdpUser;
import com.arthur.web.utils.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class UserManagerService extends BaseService {
	private static final Log logger=LogFactory.getLog(UserManagerService.class);

	/**
	 * @Author liuhan
	 * @Description 注册普通用户
	 * @Date 2019/7/2 15:16
	 * @Param 
	 * @Return 
	 */
    public void saveRegUser(TUdpUser user) {
		HibernateDAO dao=DAOFactory.getHibernateDAO("hibernateDAO");
		Timestamp sysDate=new Timestamp(new Date().getTime());
		user.setPrivilege("0");
		user.setCreatedTime(sysDate);
		user.setOpdateTime(sysDate);
		logger.info(user.toString());
		//事务提交
		dao.save(user);
//
	}

	/**
	 * @Author liuhan
	 * @Description 登陆校验
	 * @Date 2019/7/2 15:17
	 * @Param 
	 * @Return 
	 */
	public boolean login(TUdpUser user) {
		if (user != null) {
			HibernateDAO dao = DAOFactory.getHibernateDAO("hibernateDAO");

			SqlAppender hql =new SqlAppender("from TUdpUser");
			hql.append(" where userCode=?",user.getUserCode());
			hql.append(" and password=?",user.getPassword());
			Integer num= dao.getRowCount(hql.getPsSql(),hql.getParamsValues());

			if (num>0) {//
				return true;
			}else{
				return false;
			}
		} else {
			return false;
		}
	}
	/**
	 * @Author liuhan
	 * @Description 校验账户ID是否存在
	 * @Date 2019/7/2 15:41
	 * @Param 
	 * @Return 
	 */
	public Map checkUserId(TUdpUser user) {
//		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
//		SessionFactory sessionFactory=(SessionFactory) ac.getBean("sessionFactory");
//		Session session = sessionFactory.openSession();
//		User user=(User) session.get(User.class,userId);
		String flag="F";
		String msg="";
		if(StringUtil.isEmpty(user.getUserCode())){
			flag="F";
			msg="账户名不能为空";
		}else{
			HibernateDAO dao= DAOFactory.getHibernateDAO("hibernateDAO");
			SqlAppender hql =new SqlAppender("from TUdpUser");
			hql.append(" where userCode=?",user.getUserCode());
			hql.append(" and id!=?",user.getId());

			Integer num=dao.getRowCount(hql.getPsSql(),hql.getParamsValues());

			if(num==0){
				flag="T";
				msg="账户未存在";
			}else{
				flag="F";
				msg="账户已存在";
			}
		}
		Map map = new HashMap();
		map.put("flag", flag);
		map.put("msg", msg);
		return map;
	}
}
