package org.yingheng.resource;

/**
 * @author: yeyingheng
 * @date: 2023/5/16 16:42
 */
public interface ResourceLoader {

    Resource getResource(String location);

    Resource[] getResources(String locationPattern);
}
