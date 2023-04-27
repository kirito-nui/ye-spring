package org.yingheng.useTest;

import org.yingheng.beanPostProcessor.Autowired;

import java.util.List;

/**
 * @author: yeyingheng
 * @date: 2023/4/26 11:48
 */
public class UserServiceImpl implements UserService {

    @Autowired
    private OrderService orderService;

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
