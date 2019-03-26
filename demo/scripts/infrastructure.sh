#!/usr/bin/env bash
nohup mvn clean spring-boot:run -f ../redis-and-zookeeper/pom.xml > infrastructure.log &
