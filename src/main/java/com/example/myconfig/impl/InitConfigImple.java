package com.example.myconfig.impl;

/**
 * Created by wuhaochao on 2017/7/21.
 */

import com.example.myconfig.InitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * 自定义bean实现
 */
public class InitConfigImple implements InitConfig {
    private static final Logger logger = LoggerFactory.getLogger(InitConfigImple.class);

    @Override
    public void display() {
        logger.info("这是自定义bean配置");
    }
}
