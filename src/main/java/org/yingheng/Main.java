package org.yingheng;

import org.yingheng.beanFactory.DefaultListableBeanFactory;
import org.yingheng.beanPostProcessor.AutowiredAnnotationBeanPostProcessor;
import org.yingheng.useTest.Order;
import org.yingheng.useTest.OrderServiceImpl;
import org.yingheng.useTest.UserService;
import org.yingheng.useTest.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
            factory.register(UserServiceImpl.class);
            factory.register(OrderServiceImpl.class);
            factory.register(AutowiredAnnotationBeanPostProcessor.class);
            factory.registerBeanPostProcessors();
            UserService userService = (UserService) factory.getBean("UserServiceImpl");
            List<Order> userOrders = userService.getUserOrder(1L);
            System.out.println(userOrders);
            System.out.println();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}