package org.yingheng.propertyresolver;

import org.yingheng.propertyresolver.source.PropertySource;
import org.yingheng.propertyresolver.source.PropertySources;

/**
 * @author: yeyingheng
 * @date: 2023/5/22 15:05
 * <p>
 * <p>
 * 提供数据源  解析
 */
public class PropertySourcesPropertyResolver extends AbstractPropertyResolver {

    private PropertySources propertySources;


    public PropertySourcesPropertyResolver(PropertySources propertySources) {
        this.propertySources = propertySources;
    }

    @Override
    public String getProperty(String key) {
        return getProperty(key, String.class);
    }

    @Override
    public <T> T getProperty(String key, Class<T> targetType) {
        if (this.propertySources != null) {
            for (PropertySource<?> propertySource : propertySources) {
                Object value = propertySource.getProperty(key);
                if (value != null) {
                    // todo 这里会做类型转换
                    return (T) value;
                }
            }
        }
        return null;
    }
}
