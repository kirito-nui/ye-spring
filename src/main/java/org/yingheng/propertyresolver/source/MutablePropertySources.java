package org.yingheng.propertyresolver.source;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author: yeyingheng
 * @date: 2023/5/23 20:42
 */
public class MutablePropertySources implements PropertySources {


    private final List<PropertySource<?>> propertySourceList = new CopyOnWriteArrayList<>();


    public MutablePropertySources() {
    }

    public MutablePropertySources(PropertySources propertySources) {
        this();
        for (PropertySource<?> propertySource : propertySources) {
            propertySourceList.add(propertySource);
        }
    }


    @Override
    public Iterator<PropertySource<?>> iterator() {
        return null;
    }

    public void addLast(PropertySource<?> propertySource) {
        propertySourceList.add(propertySource);
    }
}
