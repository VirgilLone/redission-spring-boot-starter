package com.lw.dream;


import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(RedissonProperties.class)
public class RedissonAutoConfiguration {

    @Bean
    public RedissonClient redissonClient(RedissonProperties redissonProperties){// DI依赖注入 使用@EnableConfigurationProperties redissonProperties已经存在ioc容器中
        // 初始化RedissonClient，并装载到ioc容器
        Config config=new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer()
                .setAddress("redis://"+redissonProperties.getHost()+":"+redissonProperties.getPort())
                .setConnectTimeout(redissonProperties.getTimeout());
        return Redisson.create(config);
    }

}
