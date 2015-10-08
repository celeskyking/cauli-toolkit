package com.github.celeskyking.selenium.page;

import com.github.celeskyking.selenium.brower.IBrowser;
import com.github.celeskyking.selenium.description.PageDescription;

/**
 * Created by sky on 15/9/30
 */
public class Page {


    public PageDescription withTitle(String title){
        return new PageWithTitleDescription(title);
    }

    public PageDescription withURL(String url){
        return new PageWithURLDescription(url);
    }


    private static class PageWithTitleDescription extends PageDescription {

        private String title;

        public PageWithTitleDescription(String title){
            this.title = title;
        }


        @Override
        public IPage describe(IBrowser browser) {
            return PageImpl.withTitle(browser,title);
        }
    }

    private static class PageWithURLDescription extends PageDescription {

        private String url;

        public PageWithURLDescription(String url){
            this.url = url;
        }

        @Override
        public IPage describe(IBrowser browser) {
            return PageImpl.withURL(browser, url);
        }
    }

}
