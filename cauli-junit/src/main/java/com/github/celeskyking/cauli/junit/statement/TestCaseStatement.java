package com.github.celeskyking.cauli.junit.statement;

import com.github.celeskyking.cauli.junit.TestConfig;
import com.github.celeskyking.cauli.junit.testcase.TestCase;
import org.junit.runners.model.Statement;

/**
 * Created by tianqingwang on 15-10-13
 */
public class TestCaseStatement extends Statement{

    private TestCase testCase;

    private TestConfig testConfig;

    private TestCaseStatement(TestConfig testConfig,TestCase testCase){
        this.testCase = testCase;
    }

    @Override
    public void evaluate() throws Throwable {

    }
}
