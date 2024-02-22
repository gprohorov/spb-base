package edu.pro.spbbase.config;
/*
  @author   george
  @project   spb-base
  @class  CacheConfig
  @version  1.0.0 
  @since 15.02.24 - 12.32
*/

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean("MyCacheManager")
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager() {
            @Override
            protected Cache createConcurrentMapCache(String name) {
                return new ConcurrentMapCache(
                        name,
                        CacheBuilder.newBuilder()
                                .expireAfterWrite(20, TimeUnit.SECONDS)
                                .expireAfterAccess(25, TimeUnit.SECONDS)
                                .build().asMap(),
                        false);
            }
        };
    }
}
