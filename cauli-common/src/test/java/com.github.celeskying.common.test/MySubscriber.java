package com.github.celeskying.common.test;

import com.github.celeskyking.common.annotation.Subscribe;
import com.github.celeskyking.common.annotation.SubscribeRegistry;

/**
 * Created by sky on 15/8/1
 */
public class MySubscriber {


    @Subscribe("test")
    public void testSubscriber(String name){
        System.out.println(name);
    }

    @Subscribe("test")
    public void testSubscriberTwo(int i){
        System.out.println(i);
    }

    @Subscribe("test_param")
    public void testSubscriberWithNoParam(){
        System.out.println("hello");
    }

    @SubscribeRegistry
    public static class MyAnnoSubscriber{
        @Subscribe("test")
        public void testSubscriber(int name){
            System.out.println(name);
        }
    }
}
