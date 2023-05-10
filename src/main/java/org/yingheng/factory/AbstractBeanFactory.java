package org.yingheng.factory;

import org.yingheng.beanDefinition.BeanDefinition;

/**
 * @author: yeyingheng
 * @date: 2023/5/10 15:32
 *
 * 使用模板模式的设计方式
 */
public abstract class AbstractBeanFactory extends DefaultListableBeanFactory implements BeanFactory {

    @Override
    public Object getBean(String beanName) {
        return null;
    }

    @Override
    public Object getBean(Class<?> clazz) {
        return null;
    }

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition);
}
