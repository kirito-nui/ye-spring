package org.yingheng.beanDefinition;

public interface BeanDefinitionRegistry {

    void register(String beanName ,BeanDefinition beanDefinition);
    void register(Class<?> clazz);

}
