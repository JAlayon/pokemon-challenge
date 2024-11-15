package mx.bankaya.challenge.config.cache;

import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig implements CachingConfigurer {

    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> new SimpleKey(target.getClass().getSimpleName(), method.getName(), params);
    }
}
