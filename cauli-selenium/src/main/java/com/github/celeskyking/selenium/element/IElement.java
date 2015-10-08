package com.github.celeskyking.selenium.element;

import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

/**
 * Created by sky on 15/8/29
 */
public interface IElement {

    /**
     * @param location 通过定位方式来发现该元素
     * */
    IElement find(String location);

    /**
     * @param location 通过定位方式发现指定类型的元素
     * */
    <T extends IElement> T find(String location, Class<T> tClass);

    /**
     * 点击元素
     * */
    void click();

    /**
     * 双击元素
     * */
    void doubleClick();

    /**
     * 键按下
     * */
    void keyDown(Keys key);

    /**
     * 键松开
     * */
    void keyUp(Keys key);

    /**
     * 断言元素属性
     * */
    void assertAttribute(String attr, String value);

    /**
     * 断言元素是否可编辑
     * */
    void assertEditable();

    /**
     * 断言元素不可编辑
     * */
    void assertNotEditable();

    /**
     * 断言元素是否被选中
     * */
    void assertSelected();

    /**
     * 断言元素是否存在
     * */
    void assertIsExist();

    /**
     * 断言元素text
     * */
    void assertText(String text);

    /**
     * 断言元素的value值
     **/
    void assertValue(String value);

    /**
     * 清空元素
     * */
    IElement clear();

    /**
     * 输入信息
     * */
    IElement input(String text);

    /**
     * 定位焦点
     * */
    IElement focus();

    /**
     * @param attr 属性名称
     * 获取元素的属性
     * */
    String getAttribute(String attr);

    /**
     * 获取元素文本内容
     * */
    String getText();

    /**
     * 获取css样式
     **/
    String getCssValue(String name);

    /**
     * 获取该元素被定义的ID名称
     * */
    String getId();

    /**
     *
     * */
    void setId(String id);

    /**
     * 获取元素的位置
     * */
    Point getLocation();

    /**
     * 获取元素的大小
     * */
    int[] getSize();

    /**
     * 获取元素的tagname
     * */
    String getTagName();

    /**
     * 滚动到元素位置
     * */
    IElement scroll();

    /**
     * 鼠标悬浮
     * */
    IElement mouseOver();

    /**
     * 提交表单
     * */
    void submit();

    /**
     * 判断元素是否存在
     * */
    boolean isExist();

    /**
     * 判断元素是否可见
     * @return
     * */
    boolean isDisplay();

    /**
     * 判断元素是否可编辑
     * */
    boolean isEnable();

    /**
     * 判断元素是否被选择
     * */
    boolean isSelected();

    /**
     * 拖拽
     * */
    void dragAndDrop(IElement element);

    /**
     * 拖拽
     * */
    void dragAndDrop(Point point);

    /**
     * 按下左键
     * */
    void leftDown();


    /**
     * 松开左键
     * */
    void leftUp();

    /**
     * 按下键盘按键
     * */
    void keyPress(Keys key);

    /**
     * 按下键盘按键
     * */
    void keyPress(String keys);

    /**
     * 松开键盘按键
     * */
    void release();

    /**
     *
     * */
    WebElement getElement();

    /**
     * 基于该元素获取之下的定位位置的元素
     * */
    IElement child(String location);

    /**
     * 基于该元素获取之下的定位位置的元素
     * */
    IElement child(String location, int index);

    /**
     * 获取该元素位置下的所有子元素
     * */
    IElement children(String location);

    /**
     * 右键点击
     * */
    void contextClick();

    /**转化元素*/
    <T extends IElement> T as (T element);
}
