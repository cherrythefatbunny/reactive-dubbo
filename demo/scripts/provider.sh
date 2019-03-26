#!/usr/bin/env bash
nohup mvn clean spring-boot:run -f ../provider/pom.xml > provider.log &
