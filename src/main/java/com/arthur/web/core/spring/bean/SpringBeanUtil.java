package com.arthur.web.core.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @ClassName SpringBeanUtil
 * @Description 获取springBean工具类
 * @Author liuhan
 * @Date 2019/7/15 9:10
 * @Version 1.0
 **/
@Component
public class SpringBeanUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringBeanUtil.applicationContext == null) {
            SpringBeanUtil.applicationContext = applicationContext;
        }
        System.out.println("========ApplicationContext配置成功,在普通类可以通过调用SpringUtil.getAppContext()获取applicationContext对象,applicationContext=" + SpringBeanUtil.applicationContext + "========");
    }

    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    //通过name获取 Bean.
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    //通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    /**
     * @Author liuhan
     * @Description 在控制台显示beanFactory展示加载的bean
     * @Date 2019/7/10 14:12
     * @Param
     * @Return
     */
    public void showBeanDefinitionNames(){
        for (int i = 0; i < getApplicationContext().getBeanDefinitionNames().length; i++) {
            System.out.println("-------------init   "+ getApplicationContext().getBeanDefinitionNames()[i]+ "------------");
        }
    }
}
