package com.github.celeskyking.selenium.page;

import com.github.celeskyking.selenium.Location;
import com.github.celeskyking.selenium.Title;
import com.github.celeskyking.selenium.URL;
import com.github.celeskyking.selenium.brower.IBrowser;
import com.github.celeskyking.selenium.description.FrameDescription;
import com.github.celeskyking.selenium.element.IElement;
import com.github.celeskyking.selenium.helper.BrowserHelper;
import com.github.celeskyking.selenium.location.ElementLocation;
import com.github.celeskyking.selenium.page.frame.Frame;
import com.github.celeskyking.selenium.page.frame.FrameImpl;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;

import java.util.function.Consumer;

/**
 * Created by sky on 15/9/29
 */
public abstract class PageImpl implements IPage,Location {

    private IBrowser browser;


    public static IPage withTitle(IBrowser browser,String title){
        return new PageWithTitle(browser,title);
    }

    public static IPage withURL(IBrowser browser,String url){
        return new PageWithUrl(browser,url);
    }


    public PageImpl(IBrowser browser){
        this.browser = browser;
    }


    @Override
    public IElement find(String location) {
        return null;
    }

    @Override
    public IElement find(ElementLocation location) {
        return null;
    }

    @Override
    public FrameImpl frame(int index) {
        return null;
    }

    @Override
    public FrameImpl frame(ElementLocation elementLocation) {
        return null;
    }

    @Override
    public FrameImpl frame(String location) {
        return null;
    }

    @Override
    public IPage to(Frame frame, Consumer<IPage> processor) {
        return null;
    }

    @Override
    public IPage to(Title title, Consumer<IPage> processor) {
        return null;
    }

    @Override
    public IPage to(URL url, Consumer<IPage> processor) {
        return null;
    }

    @Override
    public <T extends IPage> T as(Class<T> pageClass) {
        return null;
    }

    @Override
    public IPage process(Consumer<IPage> processor) {
        return null;
    }

    @Override
    public <T extends IPage> T to(Class<T> page, Consumer<T> processor) {
        return null;
    }

    @Override
    public IPage to(FrameDescription description, Consumer<IPage> processor) {
        return null;
    }

    @Override
    public IPage runJs(String js) {
        return null;
    }

    @Override
    public IPage runAsynJs(String js, Consumer<Object> objectConsumer) {
        return null;
    }

    @Override
    public IPage runJs(String js, Consumer<Object> objectConsumer) {
        return null;
    }

    @Override
    public IBrowser browser() {
        return browser;
    }

    @Override
    public IPage parent() {
        return null;
    }

    @Override
    public IPage screenShot() {
        return null;
    }

    public static class PageWithTitle extends PageImpl{


        private String title;

        private PageWithTitle(IBrowser browser,String title) {
            super(browser);
            this.title = title;
        }

        @Override
        public void locate() {
            BrowserHelper.selectWindowWithTitle(browser(),title);
        }
    }

    public static class PageWithUrl  extends PageImpl{


        private String url;

        private PageWithUrl(IBrowser browser,String url) {
            super(browser);
            this.url = url;
        }

        @Override
        public void locate() {
            BrowserHelper.selectWindowWithCurrentUrl(browser(),this.url);
        }

    }

}
