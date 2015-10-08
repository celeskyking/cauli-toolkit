package com.github.celeskyking.selenium;

/**
 * Created by sky on 15/8/29
 */
public class Title {

    public String title;

    private Title(String title){
        this.title = title;
    }

    public static Title of(String title){
        return new Title(title);
    }

    public String get() {
        return title;
    }

}
