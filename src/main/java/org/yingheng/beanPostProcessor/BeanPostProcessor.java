package org.yingheng.beanPostProcessor;

public interface BeanPostProcessor {

    /**
     * bean初始化前
     * @param beanName
     * @param bean
     * @return
     */
    default Object postProcessBeforeInitialization(String beanName, Object bean){
        return bean;
    }

    /**
     * bean初始化后
     * @param beanName
     * @param bean
     * @return
     */
    default Object postProcessAfterInitialization(String beanName, Object bean){
        return bean;
    }
}
