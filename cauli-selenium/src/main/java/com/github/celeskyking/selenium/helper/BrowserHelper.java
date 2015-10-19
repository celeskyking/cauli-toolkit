package com.github.celeskyking.selenium.helper;

import com.github.celeskyking.selenium.brower.IBrowser;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;

/**
 * Created by sky on 15/9/29
 */
public class BrowserHelper {

    public static void selectWindowWithTitle(IBrowser browser,String title){
        WebDriver driver = browser.driver();
        for(String handle : driver.getWindowHandles()){
            driver.switchTo().window(handle);
            if(StringUtils.contains(driver.getTitle(), title)){
                break;
            }
        }
    }

    public static void selectWindowWithCurrentUrl(IBrowser browser,String url){
        WebDriver driver = browser.driver();
        for(String handle : driver.getWindowHandles()){
            driver.switchTo().window(handle);
            if(StringUtils.contains(driver.getCurrentUrl(), url)){
                break;
            }
        }
    }
}
