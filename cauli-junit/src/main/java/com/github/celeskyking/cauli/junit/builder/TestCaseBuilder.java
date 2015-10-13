package com.github.celeskyking.cauli.junit.builder;

import com.github.celeskyking.cauli.junit.testcase.TestCase;

/**
 * Created by tianqingwang on 15-10-13
 */
@FunctionalInterface
public interface TestCaseBuilder {

    /**
     * 生成TestCase对象
     * */
    TestCase build();
}
