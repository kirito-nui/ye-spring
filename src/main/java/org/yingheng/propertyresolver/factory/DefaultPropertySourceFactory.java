package org.yingheng.propertyresolver.factory;

import org.yingheng.propertyresolver.source.PropertySource;
import org.yingheng.propertyresolver.source.ResourcePropertySource;
import org.yingheng.resource.EncodedResource;

import java.io.IOException;

/**
 * @author: yeyingheng
 * @date: 2023/5/25 20:14
 */
public class DefaultPropertySourceFactory implements PropertySourceFactory{
    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        return new ResourcePropertySource(name, resource);
    }
}
