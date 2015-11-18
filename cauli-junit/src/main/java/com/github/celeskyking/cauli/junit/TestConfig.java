package com.github.celeskyking.cauli.junit;

import com.github.celeskyking.cauli.junit.builder.TestCaseBuilder;
import com.github.celeskyking.cauli.junit.filter.TestFilter;
import com.github.celeskyking.cauli.junit.reporter.Reporter;

/**
 * Created by tianqingwang on 15-10-13
 */
public class TestConfig {

    private TestCaseBuilder testCaseBuilder;

    private TestFilter testFilter;

    private Reporter reporter;

    private int threadNumbers;

    public TestCaseBuilder getTestCaseBuilder() {
        return testCaseBuilder;
    }

    public void setTestCaseBuilder(TestCaseBuilder testCaseBuilder) {
        this.testCaseBuilder = testCaseBuilder;
    }

    public TestFilter getTestFilter() {
        return testFilter;
    }

    public void setTestFilter(TestFilter testFilter) {
        this.testFilter = testFilter;
    }

    public Reporter getReporter() {
        return reporter;
    }

    public void setReporter(Reporter reporter) {
        this.reporter = reporter;
    }

    public int getThreadNumbers() {
        return threadNumbers;
    }

    public void setThreadNumbers(int threadNumbers) {
        this.threadNumbers = threadNumbers;
    }


}
