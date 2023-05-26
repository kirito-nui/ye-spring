package org.yingheng.propertyresolver.factory;

import org.yingheng.propertyresolver.source.PropertySource;
import org.yingheng.resource.EncodedResource;

import java.io.IOException;

/**
 * @author: yeyingheng
 * @date: 2023/5/25 20:12
 */
public interface PropertySourceFactory{


    PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException;
}
