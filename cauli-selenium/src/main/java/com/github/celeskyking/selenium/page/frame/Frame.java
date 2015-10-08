package com.github.celeskyking.selenium.page.frame;

import com.github.celeskyking.selenium.brower.IBrowser;
import com.github.celeskyking.selenium.page.PageImpl;

/**
 * Created by sky on 15/9/2
 * Frame继承自页面,因为操作和业务没有区别
 */
public class Frame extends PageImpl {

    public Frame(IBrowser browser) {
        super(browser);
    }

    @Override
    public void locate() {

    }
}
