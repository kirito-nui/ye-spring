package org.yingheng.beandefinition;

public interface BeanDefinitionRegistry {

    void register(String beanName ,BeanDefinition beanDefinition);
    void register(Class<?> clazz);

}
