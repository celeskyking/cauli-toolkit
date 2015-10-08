package com.github.celeskyking.selenium.brower;

import com.github.celeskyking.selenium.Title;
import com.github.celeskyking.selenium.URL;
import com.github.celeskyking.selenium.page.IPage;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;

import java.util.function.Consumer;

/**
 * Created by sky on 15/9/2
 */
public abstract class AbstractBrowser implements IBrowser{

    @Override
    public abstract IBrowser start();

    @Override
    public IBrowser maxWindows() {
        driver().manage().window().maximize();
        return this;
    }

    @Override
    public IBrowser forward() {
        driver().navigate().forward();
        return this;
    }

    @Override
    public IBrowser back() {
        driver().navigate().back();
        return this;
    }

    @Override
    public IBrowser refresh() {
        driver().navigate().refresh();
        return this;
    }

    @Override
    public IPage open(String url) {
        if(!StringUtils.startsWith(url,"http://")){
            url = "http://"+url;
        }
        driver().get(url);
        return page();
    }

    @Override
    public IPage open(String url, Consumer<IPage> processor) {
        if(!StringUtils.startsWith(url,"http://")){
            url = "http://"+url;
        }
        driver().get(url);
        IPage page = page();
        processor.accept(page);
        return page;
    }

    @Override
    public void quit() {
        driver().quit();
    }

    @Override
    public IBrowser close() {
        driver().close();
        return this;
    }

    @Override
    public IPage openNew(String url) {
        page().runJs("window.open('"+url+"')");
        return null;
    }

    @Override
    public IPage openNew(String url, Consumer<IPage> processor) {
        return null;
    }


    @Override
    public IPage page() {
        return null;
    }

    @Override
    public IPage to(Title title, Consumer<IPage> processor) {
        return null;
    }

    @Override
    public IPage to(Title title) {
        return null;
    }

    @Override
    public IPage to(URL url, Consumer<IPage> processor) {
        return null;
    }

    @Override
    public IPage to(URL url) {
        return null;
    }


    @Override
    public WebDriver driver() {
        return null;
    }
}
