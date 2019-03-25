package com.github.cherrythefatbunny.reactive.dubbo.boot;

import com.github.cherrythefatbunny.reactive.dubbo.extensions.proxyfactory.ReactiveJavassistProxyFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cherrythefatbunny
 */
public class ReactiveDefaultPropertiesEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {
    /**
     * The name of default {@link PropertySource} defined in SpringApplication#configurePropertySources method.
     */
    private static final String PROPERTY_SOURCE_NAME = "defaultProperties";

    private static final String DUBBO_PROVIDER_PROXY_PROPERTY = "dubbo.provider.proxy";

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        MutablePropertySources propertySources = environment.getPropertySources();
        Map<String, Object> map = new HashMap<>();
        map.put(DUBBO_PROVIDER_PROXY_PROPERTY, ReactiveJavassistProxyFactory.NAME);
        addOrReplace(propertySources,map);
    }
    /**
     * Copy from DubboDefaultPropertiesEnvironmentPostProcessor#addOrReplace(MutablePropertySources, Map)
     *
     * @param propertySources {@link MutablePropertySources}
     * @param map             Default Dubbo Properties
     */
    private void addOrReplace(MutablePropertySources propertySources,
                              Map<String, Object> map) {
        MapPropertySource target = null;
        if (propertySources.contains(PROPERTY_SOURCE_NAME)) {
            PropertySource<?> source = propertySources.get(PROPERTY_SOURCE_NAME);
            if (source instanceof MapPropertySource) {
                target = (MapPropertySource) source;
                for (String key : map.keySet()) {
                    if (!target.containsProperty(key)) {
                        target.getSource().put(key, map.get(key));
                    }
                }
            }
        }
        if (target == null) {
            target = new MapPropertySource(PROPERTY_SOURCE_NAME, map);
        }
        if (!propertySources.contains(PROPERTY_SOURCE_NAME)) {
            propertySources.addLast(target);
        }
    }

    @Override
    public int getOrder() {
        return LOWEST_PRECEDENCE;
    }
}
