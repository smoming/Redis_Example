package per.mike.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import per.mike.example.bean.ProductData;

@Configuration
public class RedisTemplateConfig {

    @Bean
    public RedisTemplate<String, ProductData> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, ProductData> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // template.setValueSerializer(new Jackson2JsonRedisSerializer<>(ProductData.class));

        template.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

        return template;
    }
}
