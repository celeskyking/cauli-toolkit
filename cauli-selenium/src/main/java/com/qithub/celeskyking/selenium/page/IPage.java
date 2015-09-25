package com.qithub.celeskyking.selenium.page;

import com.qithub.celeskyking.selenium.element.IElement;
import com.qithub.celeskyking.selenium.location.Location;

/**
 * Created by sky on 15/8/29
 */
public interface IPage {


    IElement element(String id);

    IElement find(String location);

    IElement find(Location location);

    IPage to(Frame frame);


}
