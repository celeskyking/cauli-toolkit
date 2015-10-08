package com.github.celeskyking.selenium.location;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by sky on 15/9/2
 */
public abstract class ElementLocation {

    private final static String IDENTIFICATION = "->";

    private final static Map<String, Method> methods = Maps.newHashMap();
    private static Logger logger = LoggerFactory.getLogger(ElementLocation.class);

    static {
        try {
            methods.put("id", ElementLocation.class.getDeclaredMethod("id", String.class));
            methods.put("name", ElementLocation.class.getDeclaredMethod("name", String.class));
            methods.put("xpath", ElementLocation.class.getDeclaredMethod("xpath", String.class));
            methods.put("linkText", ElementLocation.class.getDeclaredMethod("linkText", String.class));
            methods.put("tagName", ElementLocation.class.getDeclaredMethod("tagName", String.class));
            methods.put("css", ElementLocation.class.getDeclaredMethod("css", String.class));
            methods.put("partialLinkText", ElementLocation.class.getDeclaredMethod("partialLinkText", String.class));
        } catch (NoSuchMethodException e) {
            logger.error("", e);
        }
    }


    public static ElementLocation find(String location) {
        if (location.contains(IDENTIFICATION)) {
            String tag = StringUtils.substringBefore(location, IDENTIFICATION);
            try {
                return (ElementLocation) methods.get(tag).invoke(ElementLocation.class);
            } catch (Exception e) {
                logger.error("", e);
                throw new RuntimeException(e);
            }
        } else {
            return css(location);
        }
    }

    public abstract By locate();

    public static ElementLocation id(String id) {
        return new IDLocation(id);
    }

    public static ElementLocation name(String name) {
        return new NameLocation(name);
    }

    public static ElementLocation xpath(String xpath) {
        return new XPathLocation(xpath);
    }

    public static ElementLocation linkText(String link) {
        return new LinkTextLocation(link);
    }

    public static ElementLocation tagName(String name) {
        return new TagNameLocation(name);
    }

    public static ElementLocation css(String css) {
        return new CssSelectorLocation(css);
    }

    public static ElementLocation partialLinkText(String name) {
        return new PartialLinkTextLocation(name);
    }

    public static class IDLocation extends ElementLocation {

        private String id;

        public IDLocation(String id) {
            this.id = id;
        }

        @Override
        public By locate() {
            return By.id(id);
        }
    }

    public static class XPathLocation extends ElementLocation {

        private String xpath;

        public XPathLocation(String xpath) {
            this.xpath = xpath;
        }

        @Override
        public By locate() {
            return By.xpath(xpath);
        }
    }

    public static class NameLocation extends ElementLocation {

        private String name;

        public NameLocation(String name) {
            this.name = name;
        }

        @Override
        public By locate() {
            return By.name(name);
        }
    }

    public static class ClassNameLocation extends ElementLocation {

        private String className;

        public ClassNameLocation(String className) {
            this.className = className;
        }

        @Override
        public By locate() {
            return By.className(className);
        }
    }

    public static class LinkTextLocation extends ElementLocation {

        private String linkText;

        public LinkTextLocation(String linkText) {
            this.linkText = linkText;
        }

        @Override
        public By locate() {
            return By.linkText(linkText);
        }
    }

    public static class PartialLinkTextLocation extends ElementLocation {

        private String linkText;

        public PartialLinkTextLocation(String linkText) {
            this.linkText = linkText;
        }

        @Override
        public By locate() {
            return By.partialLinkText(linkText);
        }
    }

    public static class CssSelectorLocation extends ElementLocation {

        private String css;

        public CssSelectorLocation(String css) {
            this.css = css;
        }

        @Override
        public By locate() {
            return By.cssSelector(css);
        }
    }

    public static class TagNameLocation extends ElementLocation {

        private String tagName;

        public TagNameLocation(String tagName) {
            this.tagName = tagName;
        }

        @Override
        public By locate() {
            return By.tagName(tagName);
        }
    }

    public static class JQueryLocation extends ElementLocation {

        private String selector;

        public JQueryLocation(String selector) {
            this.selector = selector;
        }

        @Override
        public By locate() {
            return By.xpath(selector);
        }
    }
}
