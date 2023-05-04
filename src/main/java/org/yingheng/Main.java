package org.yingheng;

import org.yingheng.beanFactory.DefaultListableBeanFactory;
import org.yingheng.beanPostProcessor.AutowiredAnnotationBeanPostProcessor;
import org.yingheng.useTest.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
            factory.register(UserServiceImpl.class);
            factory.register(OrderServiceImpl.class);
            factory.register(CarServiceImpl.class);
            factory.register(AutowiredAnnotationBeanPostProcessor.class);
            factory.registerBeanPostProcessors();
            UserService userService = (UserService) factory.getBean("UserServiceImpl");
            OrderService orderService = (OrderService) factory.getBean("OrderServiceImpl");
            CarService carService = (CarService) factory.getBean("CarServiceImpl");
            userService.getUserOrder(1L);
            orderService.getUserByOrderId(1L);
            System.out.println("userService:" + userService);
            System.out.println("orderService:" + orderService);
            System.out.println("carService:" + carService);
            System.out.println();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}