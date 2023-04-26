package org.yingheng.beanDefinition;

public interface BeanDefinitionRegister {

    void register(String beanName ,BeanDefinition beanDefinition);
    void register(Class<?> clazz);

}
