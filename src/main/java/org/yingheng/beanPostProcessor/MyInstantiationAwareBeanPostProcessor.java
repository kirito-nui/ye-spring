package org.yingheng.beanPostProcessor;

/**
 * @author: yeyingheng
 * @date: 2023/4/26 14:46
 */
public interface MyInstantiationAwareBeanPostProcessor extends MyBeanPostProcessor{


    default Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName){
        return null;
    }

    /**
     * bean实例化后
     * @param bean
     * @param beanName
     * @return
     */
    default boolean postProcessAfterInstantiation(Object bean, String beanName){
        return true;
    }
}
