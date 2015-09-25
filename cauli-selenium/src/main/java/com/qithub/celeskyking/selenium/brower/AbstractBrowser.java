package com.qithub.celeskyking.selenium.brower;

import com.qithub.celeskyking.selenium.RunJavaScriptHandler;
import com.qithub.celeskyking.selenium.Title;
import com.qithub.celeskyking.selenium.URL;
import com.qithub.celeskyking.selenium.page.IPage;
import com.qithub.celeskyking.selenium.page.PageWorker;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * Created by sky on 15/9/2
 */
public abstract class AbstractBrowser implements IBrowser{


    private WebDriver driver;

    @Override
    public abstract IBrowser start();


    @Override
    public IBrowser open(String url) {
        this.driver.get(url);
        return this;
    }

    @Override
    public void quit() {
        this.driver.quit();
    }

    @Override
    public IBrowser openNewWindwow(String url) {
        runJavaScript("window.open(\""+url+"\")");
        return this;
    }

    @Override
    public IPage page() {
        return null;
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
    public <V extends IPage> V page(Class<V> clazz) {
        return null;
    }

    @Override
    public <V extends IPage> V waitFor(V page) {
        return null;
    }

    @Override
    public IBrowser runJavaScript(String js) {
        return null;
    }

    @Override
    public IBrowser runJavaScript(String js, RunJavaScriptHandler handler) {
        Object object = ((JavascriptExecutor) (this.driver)).executeScript(js);
        handler.handle(object);
        return this;
    }

    @Override
    public IBrowser runAsynJavaScript(String js, RunJavaScriptHandler handler) {
        Object object = ((JavascriptExecutor) (this.driver)).executeAsyncScript(js);
        handler.handle(object);
        return this;
    }

    @Override
    public IPage to(URL url, PageWorker<IPage> handler) {
        return null;
    }

    @Override
    public IPage to(Title title, PageWorker<IPage> handler) {
        return null;
    }

    @Override
    public <V extends IPage> V to(Class<V> title, PageWorker<V> handler) {
        return null;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}
