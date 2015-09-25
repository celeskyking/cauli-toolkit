package com.qithub.celeskyking.selenium.brower;

import com.qithub.celeskyking.selenium.RunJavaScriptHandler;
import com.qithub.celeskyking.selenium.Title;
import com.qithub.celeskyking.selenium.URL;
import com.qithub.celeskyking.selenium.page.IPage;
import com.qithub.celeskyking.selenium.page.PageProcessor;

/**
 * Created by sky on 15/8/29
 */
public class Browser implements IBrowser {

    private IBrowser browser;


    private Browser(IBrowser browser){
        this.browser = browser;
    }

    @Override
    public IBrowser start() {
        return browser.start();
    }

    @Override
    public IBrowser open(String url) {
        return browser.open(url);
    }

    @Override
    public void quit() {
        browser.quit();
    }

    @Override
    public IBrowser openNewWindwow(String url) {
        return browser.openNewWindwow(url);
    }

    @Override
    public IPage page() {
        return browser.page();
    }

    @Override
    public IPage page(Title title) {
        return null;
    }

    @Override
    public IPage page(URL url) {
        return null;
    }

    @Override
    public <T extends IPage> T page(Class<T> clazz) {
        return null;
    }

    @Override
    public <T extends IPage> T waitFor(T page) {
        return null;
    }

    @Override
    public IBrowser runJavaScript(String js) {
        return null;
    }

    @Override
    public IBrowser runJavaScript(String js, RunJavaScriptHandler handler) {
        return null;
    }

    @Override
    public IBrowser runAsynJavaScript(String js, RunJavaScriptHandler handler) {
        return null;
    }

    @Override
    public IPage to(URL url, PageProcessor<IPage> handler) {
        return null;
    }

    @Override
    public IPage to(Title title, PageProcessor<IPage> handler) {
        return null;
    }

    @Override
    public <T extends IPage> T to(Class<T> title, PageProcessor<T> handler) {
        return null;
    }

}
