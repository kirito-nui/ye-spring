package org.yingheng.beanFactory;

import org.yingheng.aware.BeanFactoryAware;
import org.yingheng.beanDefinition.BeanDefinition;
import org.yingheng.beanDefinition.BeanDefinitionRegister;
import org.yingheng.beanDefinition.RootBeanDefinition;
import org.yingheng.beanPostProcessor.BeanPostProcessor;
import org.yingheng.beanPostProcessor.InstantiationAwareBeanPostProcessor;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: yeyingheng
 * @date: 2023/4/25 15:54
 */
public class DefaultListableBeanFactory implements ListableBeanFactory, BeanDefinitionRegister {


    private static ConcurrentHashMap<String, BeanDefinition> beanDefinitionMaps = new ConcurrentHashMap(256);
    private static List<String> beanDefinitionNames = new ArrayList<>(256);
    private static ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<String, Object>(256);

    private static List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();
    private BeanPostProcessorCache beanPostProcessorCache;
    @Override
    public Object getBean(String beanName) {
        Object bean = singletonObjects.get(beanName);
        if (bean == null) {
            bean = createBean(beanName);
        }
        return bean;
    }

    @Override
    public Object getBean(Class<?> clazz) {
        Object bean = null;
        String[] beanNamesForType = getBeanNamesForType(clazz);
        if (beanNamesForType.length > 1) {

        }
        if (beanNamesForType.length == 1) {
            bean = getBean(beanNamesForType[0]);
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
        if (!BeanPostProcessor.class.isAssignableFrom(rootBeanDefinition.getClazz()) &&
                hasInstantiationAwareBeanPostProcessor()) {
            for (InstantiationAwareBeanPostProcessor bp : getBeanProcessorCache().instantiationAware) {
                bp.postProcessAfterInstantiation(bean, beanName);
            }
        }
        if (bean instanceof BeanFactoryAware) {
            ((BeanFactoryAware)bean).setBeanFactory(this);
        }
    }

    private boolean hasInstantiationAwareBeanPostProcessor() {
        return !getBeanProcessorCache().instantiationAware.isEmpty();
    }

    private BeanPostProcessorCache getBeanProcessorCache() {
        BeanPostProcessorCache bppCache = this.beanPostProcessorCache;
        if (bppCache == null) {
            bppCache = new BeanPostProcessorCache();
            for (BeanPostProcessor bpp : beanPostProcessors) {
                if (bpp instanceof InstantiationAwareBeanPostProcessor instantiationAwareBpp) {
                    bppCache.instantiationAware.add(instantiationAwareBpp);
                }
            }
            this.beanPostProcessorCache = bppCache;
        }
        return bppCache;
    }

    private void invokeBeanFactoryPostProcessors(){

    }

    public void registerBeanPostProcessors(){
        String[] beanNamesForType = getBeanNamesForType(BeanPostProcessor.class);
        for (String bppName : beanNamesForType) {
            Object bppBean = getBean(bppName);
            beanPostProcessors.add((BeanPostProcessor) bppBean);
            singletonObjects.put(bppName, bppBean);
        }
    }

    @Override
    public void register(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionNames.add(beanName);
        beanDefinitionMaps.put(beanName, beanDefinition);
    }

    @Override
    public void register(Class<?> clazz) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition();
        String className = clazz.getName();
        String beanName = clazz.getSimpleName();
        beanDefinition.setBeanName(beanName);
        beanDefinition.setClassName(className);
        beanDefinition.setClazz(clazz);
        register(beanName, beanDefinition);
    }

    @Override
    public String[] getBeanNamesForType(Class<?> type) {
        List<String> result = new ArrayList<>();
        for (String beanDefinitionName : beanDefinitionNames) {
            RootBeanDefinition beanDefinition = (RootBeanDefinition) beanDefinitionMaps.get(beanDefinitionName);
            if (type.isAssignableFrom(beanDefinition.getClazz())) {
                result.add(beanDefinitionName);
            }
        }
        return result.toArray(new String[]{});
    }

    static class BeanPostProcessorCache {
        final List<InstantiationAwareBeanPostProcessor> instantiationAware = new ArrayList<>();
    }
}
