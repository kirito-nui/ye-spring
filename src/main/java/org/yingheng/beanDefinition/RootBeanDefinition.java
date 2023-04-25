package org.yingheng.beanDefinition;

import lombok.Data;

/**
 * @author: yeyingheng
 * @date: 2023/4/25 16:00
 */

@Data
public class RootBeanDefinition implements BeanDefinition{

    private String beanName;
    private String className;
}
