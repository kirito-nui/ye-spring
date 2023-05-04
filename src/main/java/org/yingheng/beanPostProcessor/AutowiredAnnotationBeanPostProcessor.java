package org.yingheng.beanPostProcessor;

import org.yingheng.aware.BeanFactoryAware;
import org.yingheng.beanFactory.BeanFactory;

import java.lang.reflect.Field;

/**
 * @author: yeyingheng
 * @date: 2023/4/26 14:52
 */
public class AutowiredAnnotationBeanPostProcessor implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private BeanFactory beanFactory;

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) {
        System.out.println(beanName);
        Field[] declaredFields = bean.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            if (declaredField.isAnnotationPresent(Autowired.class)) {
                declaredField.setAccessible(true);
                Autowired annotation = declaredField.getAnnotation(Autowired.class);
                Class<?> declaringClass = declaredField.getType();
                String fieldName = declaredField.getName();
                Object fieldBean = this.beanFactory.getBean(declaringClass);
                try {
                    declaredField.set(bean, fieldBean);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return InstantiationAwareBeanPostProcessor.super.postProcessAfterInstantiation(bean, beanName);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
}
