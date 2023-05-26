package org.yingheng.propertyresolver.source;

/**
 * @author: yeyingheng
 * @date: 2023/5/22 20:55
 */
public abstract class PropertySource<T> {

    protected final String name;

    protected final T source;

    public PropertySource(String name, T source) {
        this.name = name;
        this.source = source;
    }

    public abstract Object getProperty(String name);
}
