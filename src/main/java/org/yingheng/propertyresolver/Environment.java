package org.yingheng.propertyresolver;

/**
 * @author: yeyingheng
 * @date: 2023/5/22 15:18
 *
 * 建立了两个重要模型【profiles】和【properties】
 *
 * profiles:配置。它代表应用在一启动时注册到context中bean definitions的命名的逻辑分组。
 * properties:属性。几乎在所有应用中都扮演着重要角色，他可能源自多种源头。例如属性文件，JVM系统属性，系统环境变量，JNDI，servlet上下文参数，Map等等，Environment对象和其相关的对象一起提供给用户一个方便用来配置和解析属性的服务。
 */
public interface Environment extends PropertyResolver{

    String[] getActiveProfiles();

    String[] getDefaultProfiles();
}
