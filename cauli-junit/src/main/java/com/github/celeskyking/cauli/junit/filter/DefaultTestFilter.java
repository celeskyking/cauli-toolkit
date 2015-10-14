package com.github.celeskyking.cauli.junit.filter;

import com.github.celeskyking.cauli.junit.testcase.TestCase;

/**
 * Created by tianqingwang on 15-10-14
 */
public class DefaultTestFilter implements TestFilter{
    @Override
    public boolean filter(TestCase testCase) {
        return false;
    }
}
