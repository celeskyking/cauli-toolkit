package com.github.celeskying.common.test;

import com.github.celeskyking.common.annotation.SubscribeRegistry;
import com.github.celeskyking.common.reflect.ClassFilter;
import com.github.celeskyking.common.reflect.ClassFinder;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Set;

/**
 * Created by sky on 15/8/1
 */
public class ClassFinderTest {

    @Test
    public void testClassFinderWithBasePackage(){
        Set<Class<?>> classSet = ClassFinder.finder("com.github.celeskyking").find();
        for(Class<?> clazz : classSet){
            Assert.assertThat(clazz.getName(), CoreMatchers.startsWith("com.github.celeskyking"));
        }
    }

    @Test
    public void testClassFinder(){
        Set<Class<?>> classes = ClassFinder.finder().
                filter(ClassFilter.filter(ClassFilter.withAnnotation(SubscribeRegistry.class))).find();
        for(Class<?> clazz : classes){
            Assert.assertThat("com.github.celeskying.common.test.MySubscriber$MyAnnoSubscriber",CoreMatchers.equalTo(clazz.getName()));
        }
    }

}
