package org.yingheng;

import org.yingheng.beanDefinition.RootBeanDefinition;
import org.yingheng.beanFactory.DefaultListableBeanFactory;

public class Main {
    public static void main(String[] args) {
        System.out.println(Main.class.getName());
        /*DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanName("main");
        beanDefinition.setClassName(Main.class.getName());
        factory.register("main", );*/
    }
}