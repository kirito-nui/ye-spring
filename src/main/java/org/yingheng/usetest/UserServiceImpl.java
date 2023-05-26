package org.yingheng.usetest;

import org.yingheng.beanpostprocessor.Autowired;
import org.yingheng.beanpostprocessor.Value;

import java.util.List;

/**
 * @author: yeyingheng
 * @date: 2023/4/26 11:48
 */
public class UserServiceImpl implements UserService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CarService carService;

    @Value("${name}")
    private String name;

    @Override
    public boolean register(User user) {
        return false;
    }

    @Override
    public boolean login(User user) {
        return false;
    }

    @Override
    public User getUser(Long userId) {
        return null;
    }

    @Override
    public List<Order> getUserOrder(Long userId) {
        return orderService.getUserOrder(userId);
    }
}
