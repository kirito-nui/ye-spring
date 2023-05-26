package org.yingheng.propertyresolver;

/**
 * @author: yeyingheng
 * @date: 2023/5/22 16:32
 *
 *
 * 可配置的，提供set方法
 */
public interface ConfigurableEnvironment extends Environment{

    void setActiveProfiles(String... profiles);
    void addActiveProfile(String profile);

    void setDefaultProfiles(String... profiles);
}
