package com.flynn.boot.starter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;

/**
 * @author ronghl
 * @date 2023/05/04
 */
@Slf4j
@Data
@ConfigurationProperties(prefix = "my.interceptor")
public class RoutingInterceptor {
    private String name;
    private String alias;
    private Integer order;

    @PostConstruct
    public void init() {
        log.info("RoutingInterceptor - init...");
    }

    @Bean
    public MyService myService() {
        log.info("RoutingInterceptor - register bean: MyService");
        return new MyService();
    }

}
