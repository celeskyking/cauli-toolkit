package com.github.celeskyking.selenium.page;

import com.github.celeskyking.selenium.Title;
import com.github.celeskyking.selenium.URL;
import com.github.celeskyking.selenium.brower.IBrowser;
import com.github.celeskyking.selenium.description.FrameDescription;
import com.github.celeskyking.selenium.element.IElement;
import com.github.celeskyking.selenium.location.ElementLocation;
import com.github.celeskyking.selenium.page.frame.Frame;
import com.github.celeskyking.selenium.page.frame.FrameImpl;

import java.util.function.Consumer;

/**
 * Created by sky on 15/8/29
 */
public interface IPage {



    IElement find(String location);

    IElement find(ElementLocation location);

    FrameImpl frame(int index);

    FrameImpl frame(ElementLocation elementLocation);

    FrameImpl frame(String location);

    IPage to(Frame frame,Consumer<IPage> processor);

    IPage to(Title title,Consumer<IPage> processor);

    IPage to(URL url,Consumer<IPage> processor);

    <T extends IPage> T as(Class<T> pageClass);

    /**
     * 处理页面的逻辑
     * */
    IPage process(Consumer<IPage> processor);

    /**
     * 事物执行类,通过handler来封装要执行的页面操作,易于维护
     * */
    <T extends IPage> T to(Class<T> page,Consumer<T> processor);

    IPage to(FrameDescription description,Consumer<IPage> processor);

    IPage runJs(String js);
    /**
     * 执行js
     * */
    IPage runJs(String js,Consumer<Object> objectConsumer);
    /**
     * 异步执行js
     * */
    IPage runAsynJs(String js,Consumer<Object> objectConsumer);

    IBrowser browser();

    IPage parent();

    IPage screenShot();

}
