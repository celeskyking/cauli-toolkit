package com.qithub.celeskyking.selenium.brower;

import com.qithub.celeskyking.selenium.RunJavaScriptHandler;
import com.qithub.celeskyking.selenium.Title;
import com.qithub.celeskyking.selenium.URL;
import com.qithub.celeskyking.selenium.page.IPage;
import com.qithub.celeskyking.selenium.page.PageProcessor;

/**
 * Created by sky on 15/8/29
 */
public interface IBrowser {

    /**
     * 启动浏览器
     * */
     IBrowser start();


    /**
     * 打开浏览器
     * */
    IBrowser open(String url);


    void quit();

    /**
     * 打开新页面,如果浏览器未打开,则打开浏览器
     * **/
    IBrowser openNewWindwow(String url);

    /**
     * 当前页面
     * */
    IPage page();

    /**
     * 支持包含title信息的定位
     * */
    IPage page(Title title);

    /**
     * 通过url切换到指定页面,支持包含url的一部分
     * */
    IPage page(URL url);

    /**
     * 通过指定的Page类来切换页面,并进行操作
     * */
    <V extends IPage> V page(Class<V> clazz);


    /**等待页面加载完毕*/
    <V extends IPage> V waitFor(V page);


    IBrowser runJavaScript(String js);

    /**
     * 执行js
     * */
    IBrowser runJavaScript(String js,RunJavaScriptHandler handler);


    /**
     * 异步执行js
     * */
    IBrowser runAsynJavaScript(String js,RunJavaScriptHandler handler);

    /**
     * 事物执行类,通过handler来封装要执行的页面操作,易于维护
     * */
    IPage to(URL url,PageProcessor<IPage> handler);

    /**
     * 事物执行类
     * */
    IPage to(Title title,PageProcessor<IPage> handler);

    /**
     * 事物执行类,通过handler来封装要执行的页面操作,易于维护
     * */
    <V extends IPage> V to(Class<V> title,PageProcessor<V> handler);
}
