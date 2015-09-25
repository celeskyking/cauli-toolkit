package com.qithub.celeskyking.selenium.brower;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

/**
 * Created by sky on 15/8/30
 */
public class Firefox extends AbstractBrowser {


    private FirefoxDriver firefoxDriver;

    private FirefoxProfile profile;


    public static Firefox newFirefox(String firefoxPath){
        Firefox firefox =  new Firefox();
        System.setProperty("webdriver.firefox.bin", firefoxPath);
        return firefox;
    }

    public static Firefox newFirefox(){
        Firefox firefox =  new Firefox();
        return firefox;
    }

    public Firefox withProfile(FirefoxProfile profile){
        this.profile = profile;
        return this;
    }

    @Override
    public Firefox start() {
        if(this.profile == null){
            firefoxDriver = new FirefoxDriver();
        }else{
            firefoxDriver = new FirefoxDriver(profile);
        }
        return this;
    }

}
