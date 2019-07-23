package com.arthur.web.core.spring.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * @ClassName BeanManager
 * @Description 用于获取springBean的工厂实例--适用于未启用web.xml加载过spring配置时的测试
 * (由于BeanManger会重新初始化spring上下文环境所以用springUtil代替)
 * @Author liuhan
 * @Date 2019/7/15 9:28
 * @Version 1.0
**/
public class BeanManager {

	private static final Log log = LogFactory.getLog(BeanManager.class);

	private ApplicationContext beanFactory;

	private static BeanManager beanManager = new BeanManager();

	public ApplicationContext getBeanFactory() {
		return beanFactory;
	}

	public void setBeanFactory(ApplicationContext beanFactory) {
		this.beanFactory = beanFactory;
	}

	public Object getBean(String id) {
		return beanFactory.getBean(id);

	}
	
	public Object getBeanForType(Class cls){
		Map beansOfType =beanFactory.getBeansOfType(cls);
		
		if(beansOfType.size()==1){
			return beansOfType.values().iterator().next();                  
		}
		if(beansOfType.size()>1){
			Object t=null;
			int n=0;
			for (Object obj : beansOfType.values()) {
				
				  if(n==0){
					  t=obj;
				  }
		          if(AopUtils.getTargetClass(obj).getAnnotation(Primary.class)!=null){
		        	  return  obj;
		          }
			}
			return t;
		}
		return null;
	}

	public static BeanManager getInstance() {
		if (beanManager == null) {
			beanManager = new BeanManager();
		}
		return beanManager;
	}

	private BeanManager() {

		if (BeanConfig.loadForWeb == false) {
			log.info("--------- begin init arthur system -----------");
			String sf="classpath:applicationContext.xml";
			beanFactory = new ClassPathXmlApplicationContext(sf);
			for (int i = 0; i < beanFactory.getBeanDefinitionNames().length; i++) {
				log.info("-------------init   "+ beanFactory.getBeanDefinitionNames()[i]+ "------------");
			}
			log.info("-------- finish init arthur system------------");
		}
	}
	
	/**
	 * @Author liuhan
	 * @Description 根据springConfig应用上下文内容生成beanFactory
	 * @Date 2019/7/10 14:12
	 * @Param 
	 * @Return 
	 */
	public BeanManager(ApplicationContext beanFactory){
		this.beanFactory=beanFactory;
	}
	/**
	 * @Author liuhan
	 * @Description 在控制台显示beanFactory展示加载的bean
	 * @Date 2019/7/10 14:12
	 * @Param 
	 * @Return 
	 */
	public void showBeanDefinitionNames(){
		for (int i = 0; i < beanFactory.getBeanDefinitionNames().length; i++) {
			System.out.println("-------------init   "+ beanFactory.getBeanDefinitionNames()[i]+ "------------");
		}
	}
}
