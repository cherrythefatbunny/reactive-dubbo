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

## Getting involved

### Maven dependency
Both provider and consumer should add reactive-dubbo-starter dependency
```xml
<dependency>
    <groupId>com.github.cherrythefatbunny</groupId>
    <artifactId>reactive-dubbo-starter</artifactId>
    <version>1.0.2-SNAPSHOT</version>
</dependency>
```
### Service definition
For provider side,you should define reactive services by specifying a reactive proxy factory(e.g.,reactivejavassist,reactivejdk. )
```java
@Service(proxy = "reactivejavassist")
public class ReactiveServiceImpl implements ReactiveService {
    
}
```