package org.yingheng.beanPostProcessor;

/**
 * @author: yeyingheng
 * @date: 2023/4/26 14:52
 */
public class MyAutowiredAnnotationBeanPostProcessor implements MyInstantiationAwareBeanPostProcessor{

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) {
        System.out.println(beanName);
        return MyInstantiationAwareBeanPostProcessor.super.postProcessAfterInstantiation(bean, beanName);
    }
}
