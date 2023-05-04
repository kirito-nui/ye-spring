package org.yingheng.useTest;

import org.yingheng.beanPostProcessor.Autowired;

import java.util.List;

/**
 * @author: yeyingheng
 * @date: 2023/5/4 16:45
 */
public class CarServiceImpl implements CarService{

    @Autowired
    private OrderService orderService;

    @Override
    public List<Car> getAllCarByUserId(Long userId) {
        return null;
    }
}
