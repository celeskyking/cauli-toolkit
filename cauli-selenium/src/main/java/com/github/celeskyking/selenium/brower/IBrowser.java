package com.github.celeskyking.selenium.brower;

import com.github.celeskyking.selenium.Title;
import com.github.celeskyking.selenium.URL;
import com.github.celeskyking.selenium.page.IPage;
import org.openqa.selenium.WebDriver;

import java.util.function.Consumer;

/**
 * Created by sky on 15/8/29
 */
public interface IBrowser {

    /**
     * 启动浏览器
     * */
    IBrowser start();

    /**
     * 最大化浏览器
     * */
    IBrowser maxWindows();

    /**
     * 浏览器前进
     * */
    IBrowser forward();

    /**
     * 浏览器后退
     * */
    IBrowser back();

    /**
     * 浏览器刷新
     * */
    IBrowser refresh();

    /**
     * 打开浏览器
     * */
    IPage open(String url);

    /**
     * 打开浏览器
     * */
    IPage open(String url,Consumer<IPage> processor);

    /**
     * 退出浏览器
     * */
    void quit();

    /**
     * 关闭当前页面
     * */
    IBrowser close();

    /**
     * 打开新页面,如果浏览器未打开,则打开浏览器
     * **/
    IPage openNew(String url);

    /**
     * 打开新页面,如果浏览器未打开,则打开浏览器
     * **/
    IPage openNew(String url,Consumer<IPage> processor);

    /**
     * 当前页面
     * */
    IPage page();

    /**
     * 通过title切换到页面
     * */
    IPage to(Title title,Consumer<IPage> processor);

    /**
     * 通过title切换到页面
     * */
    IPage to(Title title);


    /**
     * 通过url切换到页面
     * */
    IPage to(URL url,Consumer<IPage> processor);


    /**
     * 通过url切换到页面
     * */
    IPage to(URL url);


    WebDriver driver();
}
