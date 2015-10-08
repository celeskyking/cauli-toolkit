package com.github.celeskyking.selenium.page;

import com.github.celeskyking.selenium.Title;
import com.github.celeskyking.selenium.brower.IBrowser;
import com.github.celeskyking.selenium.URL;

/**
 * Created by sky on 15/9/30
 */
public class Pages {

    private IBrowser browser;

    public Pages(IBrowser browser){
        this.browser = browser;
    }

    public IPage withTitle(String title){
        return this.browser.to(Title.of(title));
    }

    public IPage withURL(String url){
        return this.browser.to(URL.of(url));
    }
}
