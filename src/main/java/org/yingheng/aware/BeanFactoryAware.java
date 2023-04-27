package org.yingheng.aware;

import org.yingheng.beanFactory.BeanFactory;

/**
 * @author: yeyingheng
 * @date: 2023/4/27 16:44
 */
public interface BeanFactoryAware extends Aware{


    void setBeanFactory(BeanFactory beanFactory);
}
