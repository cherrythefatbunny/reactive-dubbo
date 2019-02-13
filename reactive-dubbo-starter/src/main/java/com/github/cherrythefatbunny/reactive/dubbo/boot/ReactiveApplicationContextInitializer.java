package com.github.cherrythefatbunny.reactive.dubbo.boot;

import com.github.cherrythefatbunny.reactive.dubbo.extensions.rpc.support.RpcUtilsHacker;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author cherry
 */

public class ReactiveApplicationContextInitializer implements ApplicationContextInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        RpcUtilsHacker.hack();
    }
}