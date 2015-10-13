package com.github.celeskyking.cauli.junit.filter;

import com.github.celeskyking.cauli.junit.testcase.TestCase;

/**
 * Created by tianqingwang on 15-10-13
 */
@FunctionalInterface
public interface TestFilter {

    boolean filter(TestCase testCase);
}
