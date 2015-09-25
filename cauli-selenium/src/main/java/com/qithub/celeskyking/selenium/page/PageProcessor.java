package com.qithub.celeskyking.selenium.page;

/**
 * Created by sky on 15/8/30
 * 该类处理页面的逻辑,处理具体的页面的操作逻辑
 */
public interface PageProcessor<T> {

    void process(T t);
}
