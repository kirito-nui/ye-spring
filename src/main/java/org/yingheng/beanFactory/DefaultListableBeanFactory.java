package org.yingheng.beanFactory;

import org.yingheng.beanDefinition.BeanDefinition;
import org.yingheng.beanDefinition.BeanDefinitionRegister;
import org.yingheng.beanDefinition.RootBeanDefinition;
import org.yingheng.beanPostProcessor.MyBeanPostProcessor;
import org.yingheng.beanPostProcessor.MyInstantiationAwareBeanPostProcessor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: yeyingheng
 * @date: 2023/4/25 15:54
 */
public class DefaultListableBeanFactory implements BeanFactory, BeanDefinitionRegister {


    private static ConcurrentHashMap<String, BeanDefinition> beanDefinitionMaps = new ConcurrentHashMap(256);
    private static ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<String, Object>(256);

    private static List<MyBeanPostProcessor> beanPostProcessors = new ArrayList<>();
    private BeanPostProcessorCache beanPostProcessorCache;
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
            Object bean = constructor.newInstance();

            // 填充属性
            populateBean(beanName, rootBeanDefinition, bean);
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

    private void populateBean(String beanName, RootBeanDefinition rootBeanDefinition, Object bean) {
        if (hasInstantiationAwareBeanPostProcessor()) {
            for (MyInstantiationAwareBeanPostProcessor bp : getBeanProcessorCache().instantiationAware) {
                bp.postProcessAfterInstantiation(bean, beanName);
            }
        }
    }

    private boolean hasInstantiationAwareBeanPostProcessor() {
        return !getBeanProcessorCache().instantiationAware.isEmpty();
    }

    private BeanPostProcessorCache getBeanProcessorCache() {
        BeanPostProcessorCache bppCache = this.beanPostProcessorCache;
        if (bppCache == null) {
            bppCache = new BeanPostProcessorCache();
            for (MyBeanPostProcessor bpp : beanPostProcessors) {
                if (bpp instanceof MyInstantiationAwareBeanPostProcessor instantiationAwareBpp) {
                    bppCache.instantiationAware.add(instantiationAwareBpp);
                }
            }
            this.beanPostProcessorCache = bppCache;
        }
        return bppCache;
    }

    @Override
    public void register(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMaps.put(beanName, beanDefinition);
    }

    @Override
    public void register(Class<?> clazz) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        String className = clazz.getName();
        String beanName = clazz.getSimpleName();
        beanDefinition.setBeanName(beanName);
        beanDefinition.setClassName(className);
        beanDefinitionMaps.put(beanName, beanDefinition);
    }

    static class BeanPostProcessorCache {
        final List<MyInstantiationAwareBeanPostProcessor> instantiationAware = new ArrayList<>();
    }
}
