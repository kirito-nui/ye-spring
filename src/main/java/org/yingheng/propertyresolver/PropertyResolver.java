package org.yingheng.propertyresolver;

/**
 * @author: yeyingheng
 * @date: 2023/5/22 14:33
 *
 *
 * 属性解析器顶层接口
 */
public interface PropertyResolver {


    // 返回指定key对应的value
    String getProperty(String key);

    // 返回指定key对应的value，会解析成指定类型。如果没有对应值则返回null
    <T> T getProperty(String key, Class<T> targetType);
}
