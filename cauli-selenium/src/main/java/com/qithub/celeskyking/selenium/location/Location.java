package com.qithub.celeskyking.selenium.location;

import org.openqa.selenium.By;

/**
 * Created by sky on 15/9/2
 */
public abstract class Location {

    public abstract By locate();

    public Location id(String id){
        return new IDLocation(id);
    }


    public static class IDLocation extends Location{

        private String id;

        public IDLocation(String id){
            this.id = id;
        }

        @Override
        public By locate() {
            return By.id(id);
        }
    }

    public static class XPathLocation extends Location{

        private String xpath;

        public XPathLocation(String xpath){
            this.xpath = xpath;
        }

        @Override
        public By locate() {
            return By.xpath(xpath);
        }
    }

    public static class NameLocation extends Location{

        private String name;

        public NameLocation(String name){
            this.name = name;
        }

        @Override
        public By locate() {
            return By.name(name);
        }
    }

    public static class ClassNameLocation extends Location{

        private String className;

        public ClassNameLocation(String className){
            this.className=className;
        }

        @Override
        public By locate() {
            return By.className(className);
        }
    }

    public static class LinkTextLocation extends Location{

        private String linkText;

        public LinkTextLocation(String linkText){
            this.linkText=linkText;
        }

        @Override
        public By locate() {
            return By.linkText(linkText);
        }
    }

    public static class PartialLinkTextLocation extends Location{

        private String linkText;

        public PartialLinkTextLocation(String linkText){
            this.linkText=linkText;
        }

        @Override
        public By locate() {
            return By.partialLinkText(linkText);
        }
    }

    public static class CssSelectorLocation extends Location{

        private String css;

        public CssSelectorLocation(String css){
            this.css=css;
        }

        @Override
        public By locate() {
            return By.cssSelector(css);
        }
    }

    public static class TagNameLocation extends Location{

        private String tagName;

        public TagNameLocation(String tagName){
            this.tagName = tagName;
        }

        @Override
        public By locate() {
            return By.tagName(tagName);
        }
    }

    public static class JQueryLocation extends Location{

        private String selector;

        public JQueryLocation(String selector){
            this.selector = selector;
        }

        @Override
        public By locate() {
            return By.xpath(selector);
        }
    }
}
