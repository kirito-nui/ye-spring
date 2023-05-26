package org.yingheng.propertyresolver;

import org.yingheng.propertyresolver.source.MutablePropertySources;

/**
 * @author: yeyingheng
 * @date: 2023/5/22 17:47
 *
 *
 * 环境的抽象实现
 */
public abstract class AbstractEnvironment implements ConfigurableEnvironment{


    public static final String ACTIVE_PROFILES_PROPERTY_NAME = "spring.profiles.active";


    private final MutablePropertySources propertySources;

    private final ConfigurablePropertyResolver propertyResolver;

    public AbstractEnvironment(MutablePropertySources propertySources) {
        this.propertySources = propertySources;
        this.propertyResolver = new PropertySourcesPropertyResolver(this.propertySources);
    }

    @Override
    public void setActiveProfiles(String... profiles) {

    }

    @Override
    public void addActiveProfile(String profile) {

    }

    @Override
    public void setDefaultProfiles(String... profiles) {

    }

    @Override
    public String[] getActiveProfiles() {
        return new String[0];
    }

    @Override
    public String[] getDefaultProfiles() {
        return new String[0];
    }

    @Override
    public String getProperty(String key) {
        return this.propertyResolver.getProperty(key);
    }

    @Override
    public <T> T getProperty(String key, Class<T> targetType) {
        return null;
    }
}
