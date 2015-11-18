package com.github.celeskyking.cauli.junit.testcase;

import com.google.common.collect.Lists;
import org.junit.runners.model.FrameworkMethod;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by tianqingwang on 15-10-12
 */
public class TestCase extends FrameworkMethod {
    /**
     * 测试case的tag标记,通过路径的形式来区分case的命名空间
     * */
    private String tag;
    /**
     * case的优先级,通过优先级来区分是否执行
     * */
    private int priority;
    /**
     * case的版本,实现case的版本管理
     * */
    private String version;
    /**
     * case的描述
     * */
    private String description;
    /**
     * 是否有依赖
     * */
    private boolean dependent;
    /**依赖的测试任务的case*/
    private List<TestCase> dependencies = Lists.newArrayList();
    /**
     * case的参数列表
     * */
    private List<TestCaseParameter> parameters = Lists.newArrayList();


    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDependent() {
        return dependent;
    }

    public void setDependent(boolean dependent) {
        this.dependent = dependent;
    }

    public List<TestCase> getDependencies() {
        return dependencies;
    }

    public void setDependencies(List<TestCase> dependencies) {
        this.dependencies = dependencies;
    }

    public List<TestCaseParameter> getParamters() {
        return parameters;
    }

    public void setParamters(List<TestCaseParameter> paramters) {
        this.parameters = paramters;
    }

    public TestCase(Method method) {
        super(method);

    }

}
