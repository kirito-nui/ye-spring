package org.yingheng.propertyresolver;

/**
 * @author: yeyingheng
 * @date: 2023/5/22 14:42
 *
 * 可配置的属性解析器
 * 定义一些校验方法，一些占位符方法(前缀后缀)
 *
 * ConfigurableXXX成了Spring的一种命名规范，或者说是一种设计模式。它表示可配置的，所以都会提供大量的set方法
 * Spring很多接口都是读写分离的，最顶层接口一般都只会提供只读方法，这是Spring框架设计的一般规律之一
 */
public interface ConfigurablePropertyResolver extends PropertyResolver{
}
