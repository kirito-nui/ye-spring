package org.yingheng.factory;

/**
 * @author: yeyingheng
 * @date: 2023/4/26 17:57
 *
 * 列表bean工厂，用来迭代集合的
 */
public interface ListableBeanFactory extends BeanFactory{


    String[] getBeanNamesForType(Class<?> type);
}
