package com.github.celeskyking.cauli.junit.builder;

import com.github.celeskyking.cauli.junit.TestConfig;
import com.github.celeskyking.cauli.junit.filter.DefaultTestFilter;
import com.github.celeskyking.cauli.junit.reporter.JSONReporter;

/**
 * Created by tianqingwang on 15-10-14
 */
public class TestConfigBuilder {

    public static TestConfig defaultConfig(){
        TestConfig testConfig = new TestConfig();
        testConfig.setTestCaseBuilder(new DefaultTestCaseBuilder());
        testConfig.setReporter(new JSONReporter());
        testConfig.setTestFilter(new DefaultTestFilter());
        testConfig.setThreadNumbers(1);
        return testConfig;
    }
}
