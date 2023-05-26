package org.yingheng.resource;

/**
 * @author: yeyingheng
 * @date: 2023/5/24 18:52
 */
public class DefaultResourceLoader implements ResourceLoader{


    @Override
    public Resource getResource(String location) {
        return null;
    }

    @Override
    public Resource[] getResources(String locationPattern) {
        return new Resource[0];
    }
}
