package org.yingheng.factory.config;

/**
 * @author: yeyingheng
 * @date: 2023/5/10 17:06
 *
 *
 * 用来注册bean和获取bean
 */
public interface SingletonBeanRegistry {

    void registryBean(String beanName, Object bean);


    Object getSingleton(String beanName);

}
