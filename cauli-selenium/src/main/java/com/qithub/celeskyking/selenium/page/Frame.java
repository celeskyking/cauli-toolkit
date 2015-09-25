package com.qithub.celeskyking.selenium.page;

import com.qithub.celeskyking.selenium.element.IElement;

/**
 * Created by sky on 15/9/2
 * Frame继承自页面,因为操作和业务没有区别
 */
public class Frame implements IPage {
    @Override
    public IElement element(String location) {
        return null;
    }
}
