package com.arthur.web.aop;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName LoginHelper
 * @Description 方法初始化
 * @Author liuhan
 * @Date 2019/7/11 16:37
 * @Version 1.0
**/
@Component
public class InitHelper implements MethodBeforeAdvice, AfterReturningAdvice {
    @Override
    public void before(Method arg0, Object[] arg1, Object arg2)
            throws Throwable {
        Date sysDate =new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(sdf.format(sysDate)+"----  init start  ----");
    }

    @Override
    public void afterReturning(Object arg0, Method arg1, Object[] arg2,
                               Object arg3) throws Throwable {
        Date sysDate =new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(sdf.format(sysDate)+"----  init end  ----");
    }
}

