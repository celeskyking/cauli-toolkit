package com.github.celeskyking.cauli.junit;

import com.github.celeskyking.cauli.junit.testcase.TestCase;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;

import java.util.List;

/**
 * Created by tianqingwang on 15-10-14
 */
public class CoreRunner extends ParentRunner<TestCase> {

    private TestConfig testConfig;

    protected CoreRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
        if(TestPlan.class.isAssignableFrom(testClass)&&testClass.getDeclaredConstructors().length==1
                &&testClass.getDeclaredConstructors()[0].getParameterCount()==0){
            try {
                TestPlan testPlan = (TestPlan) testClass.newInstance();
                this.testConfig = testPlan.testConfig();
                testPlan.config(this.testConfig);
            } catch (Exception e) {
                throw new InitializationError(e);
            }
        }else{
            throw new InitializationError("RunWith运行的类必须继承TestPlan,并且只包含默认构造方法");
        }
    }

    @Override
    protected List<TestCase> getChildren() {
        return testConfig.getTestCaseBuilder().build();
    }

    @Override
    protected Description describeChild(TestCase child) {
        return Description.createTestDescription(getTestClass().getJavaClass(),testName(child));
    }

    private String testName(TestCase testCase){
        return testCase.getTag();
    }

    @Override
    protected void runChild(TestCase child, RunNotifier notifier) {

    }

}
