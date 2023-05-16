package org.yingheng.factory;

import org.yingheng.beanDefinition.BeanDefinition;
import org.yingheng.factory.config.DefaultSingletonBeanRegistry;

/**
 * @author: yeyingheng
 * @date: 2023/5/10 15:32
 *
 * 使用模板模式的设计方式，统一核心方法调用逻辑和标准定义
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String beanName) {
        Object bean = getSingleton(beanName);
        if (bean == null) {

            bean = createBean(beanName, null);
        }
        return bean;
    }

    @Override
    public Object getBean(Class<?> clazz) {
        return null;
    }

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition);
}
