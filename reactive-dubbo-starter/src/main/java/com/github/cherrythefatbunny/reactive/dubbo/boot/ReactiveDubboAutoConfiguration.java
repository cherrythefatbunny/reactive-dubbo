package com.github.cherrythefatbunny.reactive.dubbo.boot;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author cherrythefatbunny
 */
@ConditionalOnProperty(prefix = "dubbo",value = "reactive",havingValue = "enable")
@Configuration
public class ReactiveDubboAutoConfiguration {
    @Bean
    public BeanFactoryPostProcessor modifyProxyFactoryProperty() {
        return new BeanFactoryPostProcessor() {
            @Override
            public void postProcessBeanFactory(ConfigurableListableBeanFactory factory) throws BeansException {
                //TODO autoconfiguration to convert annotation proxy to reactive version
//                for(String name:factory.getBeanNamesForAnnotation(Service.class)) {
//                    Annotation annotation = factory.findAnnotationOnBean(name, Service.class);
//                    try {
//                        Object handler = Proxy.getInvocationHandler(annotation);
//                        Field f = handler.getClass().getDeclaredField("memberValues");
//                        f.setAccessible(true);
//                        Map<String, Object> memberValues = (Map<String, Object>) f.get(handler);
//                        String proxyVal = (String) memberValues.getOrDefault(PROXY_KEY,"javassist");
//                        proxyVal = "reactive" + proxyVal;
//                        memberValues.put(PROXY_KEY, proxyVal);
//                    } catch (NoSuchFieldException|IllegalAccessException e) {
//                        log.warn("modifyProxyFactoryProperty", e);
//                    }
//                }
            }
        };
    }
}
