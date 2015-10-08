package com.github.celeskyking.selenium.brower;

import com.github.celeskyking.selenium.Title;
import com.github.celeskyking.selenium.page.IPage;
import org.openqa.selenium.WebDriver;

import java.util.function.Consumer;

/**
 * Created by sky on 15/8/29
 */
public class Browser {

    private IBrowser browser;

    private Browser(IBrowser browser){
        this.browser = browser;
    }


    public static IBrowser firefox(){
        return new Firefox();
    }

    public void close() {
        this.browser.close();
    }

    public IPage openNew(String url) {
        return null;
    }

    public IPage openNew(String url, Consumer<IPage> processor) {
        return null;
    }

    public IPage page() {
        return null;
    }

    public WebDriver getWebDriver() {
        return this.browser.getWebDriver();
    }


    public static void main(String[] args) {
        Browser.firefox().open("http://www.baidu.com", page -> {
            page.find("").click();
            page.find("").click();
        }).to(Title.of("百度一下,你就知道"), page -> {

        });
    }
}
