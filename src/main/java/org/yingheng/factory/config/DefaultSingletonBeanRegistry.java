package org.yingheng.factory.config;

/**
 * @author: yeyingheng
 * @date: 2023/5/10 17:14
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry{
    @Override
    public void registryBean(String beanName, Object bean) {

    }

    @Override
    public Object getSingleton(String beanName) {
        return null;
    }
}
