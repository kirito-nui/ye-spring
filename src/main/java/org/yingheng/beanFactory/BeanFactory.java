package org.yingheng.beanFactory;

public interface BeanFactory {

    Object getBean(String beanName);
    Object getBean(Class<?> clazz);
}
