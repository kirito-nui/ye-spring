package org.yingheng.useTest;

import org.yingheng.useTest.User;

import java.util.List;

public interface UserService {

    boolean register(User user);

    boolean login(User user);

    User getUser(Long userId);
    List<Order> getUserOrder(Long userId);
}
