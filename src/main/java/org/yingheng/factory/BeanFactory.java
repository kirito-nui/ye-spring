package org.yingheng.factory;

public interface BeanFactory {

    Object getBean(String beanName);
    Object getBean(Class<?> clazz);
}
