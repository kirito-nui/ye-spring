package org.yingheng.propertyresolver;

import org.yingheng.propertyresolver.source.PropertySources;

/**
 * @author: yeyingheng
 * @date: 2023/5/22 15:05
 *
 *
 * 提供数据源  解析
 */
public class PropertySourcesPropertyResolver extends AbstractPropertyResolver{

    private PropertySources propertySources;


    public PropertySourcesPropertyResolver(PropertySources propertySources) {
        this.propertySources = propertySources;
    }

    @Override
    public String getProperty(String key) {
        return null;
    }

    @Override
    public <T> T getProperty(String key, Class<T> targetType) {
        return null;
    }
}
