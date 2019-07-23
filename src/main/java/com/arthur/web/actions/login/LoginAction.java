package com.arthur.web.actions.login;

import com.arthur.web.core.action.BaseAction;
import org.springframework.stereotype.Controller;

/**
 * @ClassName LoginAction
 * @Description TODO
 * @Author liuhan
 * @Date 2019/7/17 16:53
 * @Version 1.0
 **/
@Controller
public class LoginAction extends BaseAction {
    public String doInitialize(){
        return "index";
    }
}
