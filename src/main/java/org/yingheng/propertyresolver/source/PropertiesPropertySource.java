package org.yingheng.propertyresolver.source;

import java.util.Map;
import java.util.Properties;

/**
 * @author: yeyingheng
 * @date: 2023/5/24 15:53
 */
public class PropertiesPropertySource extends MapPropertySource {


    public PropertiesPropertySource(String name, Properties properties) {
        super(name, (Map) properties);
    }

    public PropertiesPropertySource(String name, Map<String, Object> source) {
        super(name, source);
    }
}
