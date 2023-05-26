package org.yingheng.propertyresolver;

import org.yingheng.propertyresolver.source.MutablePropertySources;

/**
 * @author: yeyingheng
 * @date: 2023/5/22 18:14
 *
 * 标准实现
 */
public class StandardEnvironment extends AbstractEnvironment{
    public StandardEnvironment(MutablePropertySources propertySources) {
        super(propertySources);
    }
}
