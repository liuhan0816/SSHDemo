package com.arthur.web.aop;

import com.arthur.web.core.spring.bean.BeanManager;
import com.arthur.web.pojo.protal.TUdpUser;
import com.arthur.web.service.user.UserManagerService;
import com.arthur.web.utils.MD5;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

/**
 * @ClassName TestAop
 * @Description 测试aop
 * @Author liuhan
 * @Date 2019/7/8 14:41
 * @Version 1.0
 **/
public class TestAop {
    @Test
    public void TestAopAspect(){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-config1.xml");
//        Sleepable sleeper = (Sleepable) context.getBean("human");
//        sleeper.sleep();
    }

    @Test
    public void Test(){
        BeanManager beanManager=BeanManager.getInstance();
        beanManager.showBeanDefinitionNames();
        UserManagerService userManagerService = (UserManagerService)  beanManager.getBean("userManagerService");
        TUdpUser user=new TUdpUser();
        user.setUserCode("aaaaaa");
        String password="123456";
        user.setPassword(MD5.encode(password));
        userManagerService.saveRegUser(user);
    }

    @Test
    public void Test2(){
        BeanManager beanManager=BeanManager.getInstance();
        beanManager.showBeanDefinitionNames();
    }
}
