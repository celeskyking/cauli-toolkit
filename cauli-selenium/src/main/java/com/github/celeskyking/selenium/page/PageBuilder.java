package com.github.celeskyking.selenium.page;

import com.github.celeskyking.selenium.Title;
import com.github.celeskyking.selenium.URL;
import com.github.celeskyking.selenium.brower.IBrowser;
import com.github.celeskyking.selenium.description.FrameDescription;
import com.github.celeskyking.selenium.element.IElement;
import com.github.celeskyking.selenium.location.ElementLocation;
import com.github.celeskyking.selenium.page.frame.Frame;
import com.github.celeskyking.selenium.page.frame.FrameImpl;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.function.Consumer;

/**
 * Created by sky on 15/10/7
 */
public class PageBuilder implements IPage{

    private IPage page;

    private List<PageListener> pageListeners= Lists.newArrayList();

    public IPage register(PageListener pageListener){
        this.pageListeners.add(pageListener);
        return this;
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
    public IPage runJs(String js, Consumer<Object> objectConsumer) {
        return null;
    }

    @Override
    public IPage runAsynJs(String js, Consumer<Object> objectConsumer) {
        return null;
    }

    @Override
    public IBrowser browser() {
        return null;
    }

    @Override
    public IPage parent() {
        return null;
    }

    @Override
    public IPage screenShot() {
        return null;
    }
}
