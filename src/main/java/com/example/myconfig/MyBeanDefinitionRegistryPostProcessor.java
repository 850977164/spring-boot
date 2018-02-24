package com.example.myconfig;

import com.example.myconfig.impl.InitConfigImple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.*;

/**
 * Created by wuhaochao on 2017/7/21.
 * <p>
 * BeanDefinitionRegistryPostProcessor接口实现
 * <p>
 * 1.org.springframework.context.ApplicationContextAware
 * 的setApplicationContext 方法将在Spring启动之前第一个被调用
 * 2.org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor
 * 的postProcessBeanDefinitionRegistry和postProcessBeanFactory第二三调用,在Bean初始化创建之前启动.
 * 在这里注入spring我们自己的bean
 * <p>
 * BeanDefinitionRegistryPostProcessor接口实现
 * <p>
 * 1.org.springframework.context.ApplicationContextAware
 * 的setApplicationContext 方法将在Spring启动之前第一个被调用
 * 2.org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor
 * 的postProcessBeanDefinitionRegistry和postProcessBeanFactory第二三调用,在Bean初始化创建之前启动.
 * 在这里注入spring我们自己的bean
 */

/**
 * BeanDefinitionRegistryPostProcessor接口实现
 *
 *1.org.springframework.context.ApplicationContextAware
 的setApplicationContext 方法将在Spring启动之前第一个被调用
 *2.org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor
 * 的postProcessBeanDefinitionRegistry和postProcessBeanFactory第二三调用,在Bean初始化创建之前启动.
 * 在这里注入spring我们自己的bean

 */

/**
 * 实现自己实例化bean并注册为Spring管理
 */
//@Configuration
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    private static final Logger logger = LoggerFactory.getLogger(MyBeanDefinitionRegistryPostProcessor.class);
    private ScopeMetadataResolver scopeMetadataResover = new AnnotationScopeMetadataResolver();
    private BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();

    /**
     * spring启动后第二个被调用
     * @param beanDefinitionRegistry
     * @throws BeansException
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        logger.info("调用方法postProcessBeanFactory");
        registerBean(beanDefinitionRegistry, "MyInitConfig", InitConfigImple.class);
//        registerBean(beanDefinitionRegistry, "dataSourceA", com.zaxxer.hikari.HikariDataSource.class);
    }

    /**
     * spring启动后第三个被调用
     * @param configurableListableBeanFactory
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        logger.info("调用方法postProcessBeanDefinitionRegistry");
//        设置属性
        /*BeanDefinition beanDefinition=configurableListableBeanFactory.getBeanDefinition("dataSourceA");
        MutablePropertyValues mpv=beanDefinition.getPropertyValues();
        mpv.addPropertyValue("driverClassName", "com.mysql.jdbc.Driver");
        mpv.addPropertyValue("url", "jdbc:mysql://localhost:3306/stu_info");
        mpv.addPropertyValue("username", "root");
        mpv.addPropertyValue("password", "111111");*/
    }


    private void registerBean(BeanDefinitionRegistry beanDefinitionRegistry, String name,
                              Class<?> beanClass) {
        AnnotatedGenericBeanDefinition adb = new AnnotatedGenericBeanDefinition(beanClass);
        ScopeMetadata smd = this.scopeMetadataResover.resolveScopeMetadata(adb);
        adb.setScope(smd.getScopeName());
//      可以自动生成name
        String beanname = (name != null ? name : this.beanNameGenerator
                .generateBeanName(adb, beanDefinitionRegistry));
        AnnotationConfigUtils.processCommonDefinitionAnnotations(adb);
        BeanDefinitionHolder beanDefinitionHoler = new BeanDefinitionHolder(adb, beanname);
        BeanDefinitionReaderUtils.registerBeanDefinition(beanDefinitionHoler, beanDefinitionRegistry);

    }
}