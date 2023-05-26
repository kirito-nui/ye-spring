package org.yingheng.beanpostprocessor;

import org.yingheng.aware.BeanFactoryAware;
import org.yingheng.factory.BeanFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author: yeyingheng
 * @date: 2023/4/26 14:52
 */
public class AutowiredAnnotationBeanPostProcessor implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private BeanFactory beanFactory;

    private final Set<Class<? extends Annotation>> autowiredAnnotationTypes = new LinkedHashSet<>(4);


    public AutowiredAnnotationBeanPostProcessor() {
        this.autowiredAnnotationTypes.add(Autowired.class);
        this.autowiredAnnotationTypes.add(Value.class);
    }

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
