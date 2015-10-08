package com.github.celeskyking.selenium.page;

/**
 * Created by sky on 15/10/7
 */
public interface PageListener {

    void beforeLocateElement(String id);

    void afterLocateElement(String id);
}
