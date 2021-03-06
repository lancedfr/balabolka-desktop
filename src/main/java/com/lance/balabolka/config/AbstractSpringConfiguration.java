package com.lance.balabolka.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class AbstractSpringConfiguration implements BeanFactoryPostProcessor, BeanPostProcessor, ApplicationContextAware {

    private ApplicationContext applicationContext;
    private Map<Class<?>, Method> returnTypeToDependencyInjector;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Method m = returnTypeToDependencyInjector.get(bean.getClass());
        if (m != null) {
            try {
                m.invoke(this, bean);
            }
            catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        returnTypeToDependencyInjector = createMapOfDependencyInjectors();
    }

    private ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    protected <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    private Map<Class<?>, Method> createMapOfDependencyInjectors() {
        Map<Class<?>, Method> map = new HashMap<>();
        Method[] methods = getClass().getMethods();
        for (Method method : methods) {
            if (method.getAnnotation(DependencyInjector.class) != null) {
                map.put(method.getReturnType(), method);
            }
        }
        return map;
    }
}
