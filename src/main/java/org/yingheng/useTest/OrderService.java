package org.yingheng.useTest;

import java.util.List;

/**
 * @author: yeyingheng
 * @date: 2023/4/26 11:51
 */
public interface OrderService {

    Order getOrder(Long orderId);

    List<Order> getUserOrder(Long userId);
}
