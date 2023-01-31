package com.gavin.MyRibbonConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 由于自定义Ribbon轮询规则不能放在@ScanComponent扫描的包及其子包下，故在这个位置打包
 */
@Configuration
public class MyRibbonRule {
    @Bean
    public IRule myRibbonRule(){
        return new RandomRule(); //从默认轮询规则切换为随机规则
    }
}
