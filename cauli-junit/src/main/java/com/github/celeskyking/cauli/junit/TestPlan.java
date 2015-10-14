package com.github.celeskyking.cauli.junit;

import com.github.celeskyking.cauli.junit.builder.TestConfigBuilder;

/**
 * Created by tianqingwang on 15-10-14
 * 测试计划
 */
@FunctionalInterface
public interface TestPlan {

    TestConfig testConfig = TestConfigBuilder.defaultConfig();

    void config(TestConfig testConfig);

    default TestConfig testConfig(){
        return testConfig;
    }

}
