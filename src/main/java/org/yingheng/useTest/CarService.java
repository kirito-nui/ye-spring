package org.yingheng.useTest;

import java.util.List;

/**
 * @author: yeyingheng
 * @date: 2023/5/4 16:45
 */
public interface CarService {

    List<Car> getAllCarByUserId(Long userId);
}
