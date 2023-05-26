package org.yingheng.usetest;

import org.yingheng.beanpostprocessor.Autowired;

import java.util.List;

/**
 * @author: yeyingheng
 * @date: 2023/4/26 11:51
 */
public class OrderServiceImpl implements OrderService{

    @Autowired
    private UserService userService;
    @Override
    public Order getOrder(Long orderId) {
        return null;
    }

    @Override
    public List<Order> getUserOrder(Long userId) {
        return null;
    }

    @Override
    public User getUserByOrderId(Long orderId) {
        return null;
    }
}
