package com.example.superhero.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.UUID;

public class RedisConfig {

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration
                redisStandaloneConfiguration = new RedisStandaloneConfiguration();

        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public RedisTemplate<UUID, Object> redisTemplate() {
        RedisTemplate<UUID, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(
                jedisConnectionFactory()
        );
        template.setKeySerializer(
                new StringRedisSerializer()
        );
        template.setHashKeySerializer(
                new StringRedisSerializer()
        );
        template.setHashKeySerializer(
                new JdkSerializationRedisSerializer()
        );
        template.setValueSerializer(
                new JdkSerializationRedisSerializer()
        );
        template.setEnableTransactionSupport(true);
        template.afterPropertiesSet();

        return template;
    }
}
