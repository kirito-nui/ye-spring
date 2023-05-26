package org.yingheng.resource;

/**
 * @author: yeyingheng
 * @date: 2023/5/16 16:42
 */
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    String CLASSPATH_ALL_URL_PREFIX = "classpath*:";

    Resource getResource(String location);

    Resource[] getResources(String locationPattern);
}
