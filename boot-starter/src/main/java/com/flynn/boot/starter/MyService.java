package com.flynn.boot.starter;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;

/**
 * @author ronghl
 * @date 2023/05/04
 */
@Slf4j
public class MyService {

    @PostConstruct
    public void init() {
        log.info("MyService - 初始化...");
    }

    public void testPrint() {
        log.info("MyService - testPrint. Register success.");
    }

}
