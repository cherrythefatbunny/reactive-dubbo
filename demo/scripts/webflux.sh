#!/usr/bin/env bash
nohup mvn clean spring-boot:run -f ../consumer-webflux/pom.xml > webflux.log &