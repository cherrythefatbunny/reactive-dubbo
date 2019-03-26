#!/usr/bin/env bash
nohup mvn clean spring-boot:run -f ../delay-service/pom.xml > delay.log &
