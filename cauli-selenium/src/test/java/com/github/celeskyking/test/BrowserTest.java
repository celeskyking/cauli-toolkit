package com.github.celeskyking.test;

import com.github.celeskyking.selenium.Title;
import com.github.celeskyking.selenium.brower.Browser;
import org.junit.Test;

/**
 * Created by sky on 15/10/22
 */
public class BrowserTest {

    @Test
    public void testBrowser(){
        Browser.firefox().open("www.baidu.com").as(BaiduPage.class).process(baiduPage -> {
            baiduPage.search("北京");
        }).to(Title.of("百度一下,搜索北京"), page -> {
            System.out.println(page.url());
        }).browser().quit();


        Browser.firefox().open("www.baidu.com", page -> page.as(BaiduPage.class)
                .process(baiduPage -> baiduPage.search("北京"))).browser().quit();
        Browser.firefox().open("www.baidu.com", page -> {
            page.find("#kw").input("北京");
            page.find("#su").click();
        }).to(Title.of("百度一下,搜索北京"), page -> {
            System.out.println(page.url());
        }).browser().quit();
    }
}
