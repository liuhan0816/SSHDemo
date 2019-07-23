package com.arthur.web.actions.user;

import com.alibaba.fastjson.JSON;
import com.arthur.web.bean.MsgBean;
import com.arthur.web.core.action.BaseAction;
import com.arthur.web.pojo.protal.TUdpUser;
import com.arthur.web.service.user.UserManagerService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * @ClassName UserManagerAction
 * @Description 用户管理模块
 * @Author liuhan
 * @Date 2019/7/2 15:00
 * @Version 1.0
 **/
@Controller
public class UserManagerAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    static Log logger= LogFactory.getLog(UserManagerAction.class);

    private UserManagerService userManagerService;
    @Resource
    public void setUserManagerService(UserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }

    private TUdpUser user;

    /**
     * 获取字段 user
     *
     * @return 获取字段 user
     */
    public TUdpUser getUser() {
        return user;
    }

    /**
     * 赋予字段 user
     * <p>你可以使用getUser() 来获取字段的值 user</p>
     *
     * @param user user
     */
    public void setUser(TUdpUser user) {
        this.user = user;
    }

    public String doLogin(){return "login";}
    /**
     * @Author liuhan
     * @Description 登陆
     * @Date 2019/7/1 15:10
     * @Param
     * @Return
     */
    public void login() {
        PrintWriter out = null;
        try {
            logger.debug("login");
            StringBuilder josn = new StringBuilder();
            String flag="",msg="";
            if(userManagerService.login(user)){
                request.getSession().setAttribute("user", user);
                flag="T";
                msg="登陆成功";
            }else{
                flag="F";
                msg="账户密码错误";
            }
            MsgBean bean = new MsgBean();
            bean.setFlag(flag);
            bean.setContent(msg);
            response.setContentType("text/html;charset=UTF-8");
            out = response.getWriter();
            out.println(JSON.toJSONString(bean));
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(out!=null){
                out.close();
                out=null;
            }
        }
    }

    /**
     * @Author liuhan
     * @Description 退出
     * @Date 2019/7/1 15:10
     * @Param
     * @Return
     */
    public void loginout(){
        PrintWriter out = null;
        try {
            logger.debug("loginout");
            StringBuilder josn = new StringBuilder();
            if(userManagerService.login(user)){
                request.getSession().setAttribute("user", user);
                josn.append("{\"successMsg\":\"退出成功!\"}");
            }else{
                josn.append("{\"errorMsg\":\"退出失败!\"}");
            }
            response.setContentType("text/html;charset=UTF-8");
            out = response.getWriter();
            out.println(josn.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(out!=null){
                out.close();
                out=null;
            }
        }
    }

    /**
     * @Author liuhan
     * @Description 注册页面
     * @Date 2019/7/2 10:38
     * @Param
     * @Return
     */
    public String doRegister(){
        return "register";
    }
    
    /**
     * @Author liuhan
     * @Description 注册新用户(普通用户)
     * @Date 2019/7/2 15:29
     * @Param
     * @Return
     */
    public void register() {
        logger.debug("regUser");
        Map map = userManagerService.checkUserId(user);
        String flag = map.get("flag")==null ? "":map.get("flag").toString();
        String msg = map.get("msg")==null ? "":map.get("msg").toString();
        if("T".equals(flag)){
            userManagerService.saveRegUser(user);
            msg="注册成功";
        }
        MsgBean bean = new MsgBean();
        bean.setFlag(flag);
        bean.setContent(msg);
        try {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println(JSON.toJSONString(bean));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
