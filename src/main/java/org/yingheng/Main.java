package org.yingheng;

import org.yingheng.factory.DefaultListableBeanFactory;
import org.yingheng.beanpostprocessor.AutowiredAnnotationBeanPostProcessor;
import org.yingheng.propertyresolver.factory.DefaultPropertySourceFactory;
import org.yingheng.propertyresolver.source.MutablePropertySources;
import org.yingheng.propertyresolver.source.PropertySource;
import org.yingheng.resource.EncodedResource;
import org.yingheng.resource.PathMatchingResourcePatternResolver;
import org.yingheng.resource.Resource;
import org.yingheng.usetest.*;

public class Main {
    public static void main(String[] args) {
        try {
//            DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
//            factory.register(UserServiceImpl.class);
//            factory.register(OrderServiceImpl.class);
//            factory.register(CarServiceImpl.class);
//            factory.register(AutowiredAnnotationBeanPostProcessor.class);
//            factory.registerBeanPostProcessors();
//            UserService userService = (UserService) factory.getBean("UserServiceImpl");
//            OrderService orderService = (OrderService) factory.getBean("OrderServiceImpl");
//            CarService carService = (CarService) factory.getBean("CarServiceImpl");
//            userService.getUserOrder(1L);
//            orderService.getUserByOrderId(1L);
//            System.out.println("userService:" + userService);
//            System.out.println("orderService:" + orderService);
//            System.out.println("carService:" + carService);
//            System.out.println();

            String location = "classpath*:*.properties";
            PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = patternResolver.getResources(location);
            DefaultPropertySourceFactory factory = new DefaultPropertySourceFactory();
            MutablePropertySources propertySources = new MutablePropertySources();
            PropertySource<?> propertySource;

            for (Resource resource : resources) {
                propertySource = factory.createPropertySource(null, new EncodedResource(resource));
                propertySources.addLast(propertySource);
            }
            System.out.println();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}