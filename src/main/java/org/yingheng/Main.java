package org.yingheng;

import org.yingheng.beanDefinition.RootBeanDefinition;
import org.yingheng.beanFactory.DefaultListableBeanFactory;
import org.yingheng.beanPostProcessor.MyAutowiredAnnotationBeanPostProcessor;
import org.yingheng.useTest.Order;
import org.yingheng.useTest.UserService;
import org.yingheng.useTest.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
            RootBeanDefinition beanDefinition = new RootBeanDefinition();
            beanDefinition.setClassName(UserServiceImpl.class.getName());
            factory.register("userServiceImpl", beanDefinition);
            factory.register(MyAutowiredAnnotationBeanPostProcessor.class);
            UserService userService = (UserService) factory.getBean("userServiceImpl");
            List<Order> userOrders = userService.getUserOrder(1L);
            System.out.println(userOrders);
            System.out.println();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}