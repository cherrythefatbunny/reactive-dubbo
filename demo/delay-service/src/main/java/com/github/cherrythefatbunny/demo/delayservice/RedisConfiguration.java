package com.github.cherrythefatbunny.demo.delayservice;

import com.github.cherrythefatbunny.demo.api.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfiguration {

    @Bean
    public ReactiveRedisTemplate<String, Person> jsonReactiveTemplate(
            ReactiveRedisConnectionFactory factory) {
        Jackson2JsonRedisSerializer<Person> valueSerializer =
                new Jackson2JsonRedisSerializer<>(Person.class);
        RedisSerializationContext.RedisSerializationContextBuilder<String, Person> builder =
                RedisSerializationContext.newSerializationContext();
        RedisSerializationContext<String, Person> context = builder
                .key(RedisSerializer.string())
                .value(valueSerializer)
                .hashKey(RedisSerializer.string())
                .hashValue(valueSerializer)
                .build();
        return new ReactiveRedisTemplate<>(factory, context);
    }
    @Bean
    public ReactiveRedisTemplate<String, String> stringReactiveTemplate(
            ReactiveRedisConnectionFactory factory) {
        Jackson2JsonRedisSerializer<String> valueSerializer =
                new Jackson2JsonRedisSerializer<>(String.class);
        RedisSerializationContext.RedisSerializationContextBuilder<String, String> builder =
                RedisSerializationContext.newSerializationContext();
        RedisSerializationContext<String, String> context = builder
                .key(RedisSerializer.string())
                .value(RedisSerializer.string())
                .hashKey(RedisSerializer.string())
                .hashValue(RedisSerializer.string())
                .build();
        return new ReactiveRedisTemplate<>(factory, context);
    }
}
