#!/usr/bin/env bash
nohup mvn clean spring-boot:run -f ../consumer-mvc/pom.xml > mvc.log &
