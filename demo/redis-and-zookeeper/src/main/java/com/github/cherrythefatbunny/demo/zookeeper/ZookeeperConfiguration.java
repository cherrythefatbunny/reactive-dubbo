package com.github.cherrythefatbunny.demo.zookeeper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

@Configuration
@ConditionalOnProperty(value = "infrastructure.enable",havingValue = "true")
public class ZookeeperConfiguration {
    @Value("${embedded.zookeeper.port}")
    private int zkPort;
    private EmbeddedZooKeeper zk;

    @PostConstruct
    public void startRedis() throws IOException {
        zk = new EmbeddedZooKeeper(zkPort, false);
        zk.start();
    }

    @PreDestroy
    public void stopRedis() {
        zk.stop();
    }
}
