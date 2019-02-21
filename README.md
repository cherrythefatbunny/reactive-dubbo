![logo](logo.png)
--
![maven](https://img.shields.io/maven-central/v/com.github.cherrythefatbunny/reactive-dubbo.svg)
![license](https://img.shields.io/github/license/cherrythefatbunny/reactive-dubbo.svg)

Reactive support for [Dubbo](http://dubbo.apache.org) based on [REACTOR](https://projectreactor.io/)

## Getting started
### Install
```bash
# git clone https://github.com/cherrythefatbunny/reactive-dubbo.git
# cd reactive-dubbo
# mvn clean install
```
### Run redis and zookeeper
```bash
# cd demo/redis-and-zookeeper
# nohup mvn spring-boot:run &
```
### Run Demo Provider
```bash
# cd ../provider
# nohup mvn spring-boot:run &
```
### Run Demo Consumer
```bash
# cd ../consumer
# nohup mvn spring-boot:run &
```