package org.yingheng.propertyresolver.source;

import java.util.Map;

/**
 * @author: yeyingheng
 * @date: 2023/5/24 15:48
 */
public class MapPropertySource extends EnumerablePropertySource<Map<String, Object>> {
    public MapPropertySource(String name, Map<String, Object> source) {
        super(name, source);
    }

    @Override
    public Object getProperty(String name) {
        return this.source.get(name);
    }


    @Override
    public String[] getPropertyNames() {
        return new String[0];
    }
}
