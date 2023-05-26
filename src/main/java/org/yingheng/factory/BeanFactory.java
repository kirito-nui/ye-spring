package org.yingheng.factory;

/**
 * Bean工厂，获取bean实例接口
 */
public interface BeanFactory {

    Object getBean(String beanName);
    Object getBean(Class<?> clazz);
}
