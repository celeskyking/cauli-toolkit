package com.github.celeskyking.selenium;

/**
 * Created by sky on 15/8/29
 */
public class URL {

    public String url;

    private URL(String title){
        this.url = title;
    }

    public static URL of(String url){
        return new URL(url);
    }

    public String get() {
        return url;
    }
}
