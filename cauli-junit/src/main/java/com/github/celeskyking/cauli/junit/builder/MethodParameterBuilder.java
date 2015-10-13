package com.github.celeskyking.cauli.junit.builder;

import java.lang.reflect.Method;

/**
 * Created by tianqingwang on 15-10-13.
 */
public class MethodParameterBuilder implements ParameterBuilder{

    private Method method;

    public MethodParameterBuilder(Method method){
        this.method = method;
    }


    @Override
    public Object[] build() {
        return new Object[0];
    }
}
