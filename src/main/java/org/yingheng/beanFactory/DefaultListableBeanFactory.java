package org.yingheng.beanFactory;

import org.yingheng.beanDefinition.BeanDefinition;
import org.yingheng.beanDefinition.BeanDefinitionRegister;
import org.yingheng.beanDefinition.RootBeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: yeyingheng
 * @date: 2023/4/25 15:54
 */
public class DefaultListableBeanFactory implements BeanFactory, BeanDefinitionRegister {


    private static ConcurrentHashMap<String, BeanDefinition> beanDefinitionMaps = new ConcurrentHashMap(256);
    private static ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<String, Object>(256);

    @Override
    public Object getBean(String beanName) {
        Object bean = singletonObjects.get(beanName);
        if (bean == null) {
            bean = createBean(beanName);
        }
        return bean;
    }

    private Object createBean(String beanName) {
        RootBeanDefinition rootBeanDefinition = (RootBeanDefinition) beanDefinitionMaps.get(beanName);
        String className = rootBeanDefinition.getClassName();
        try {
            Constructor<?> constructor = Class.forName(className).getConstructor();
            Object bean = constructor.newInstance(new Object[]{});
            singletonObjects.put(beanName, bean);
            return bean;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void register(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMaps.put(beanName, (RootBeanDefinition) beanDefinition);
    }
}
