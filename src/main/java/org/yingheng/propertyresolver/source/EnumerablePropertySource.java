package org.yingheng.propertyresolver.source;

import org.yingheng.propertyresolver.source.PropertySource;

/**
 * @author: yeyingheng
 * @date: 2023/5/24 15:44
 *
 * 表示可枚举的，提供一个方法getPropertyNames()
 *
 * 不知道意义何在
 */
public abstract class EnumerablePropertySource<T> extends PropertySource<T> {

    public EnumerablePropertySource(String name, T source) {
        super(name, source);
    }


    public abstract String[] getPropertyNames();
}
