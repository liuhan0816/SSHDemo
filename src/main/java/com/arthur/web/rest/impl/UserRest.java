package com.arthur.web.rest.impl;

import com.arthur.web.pojo.protal.TUdpUser;
import com.arthur.web.service.user.UserManagerService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Map;

@Path("/user")
public class UserRest {
	private static final Log logger = LogFactory.getLog(UserRest.class);

	@Resource
	private UserManagerService userManagerService;

	@GET
	@Path("/checkId")
	@Produces(MediaType.APPLICATION_JSON)
	public Map checkId(@QueryParam("userId") String userId) {
		logger.info("验重userId:" + userId);
		TUdpUser user=new TUdpUser();
		user.setUserCode("liuhan");
		return userManagerService.checkUserId(user);
	}
}
