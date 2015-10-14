package com.github.celeskyking.cauli.junit.reporter;

/**
 * Created by tianqingwang on 15-10-14
 */
public interface Reporter {

    //生成报告
    void report();

    //生成json格式的报告
    String asJson();
}
