package com.qithub.celeskyking.selenium;

/**
 * Created by sky on 15/8/29
 */
public abstract class URL {

    public abstract boolean match(String url);

    private URL(){

    }

    public static URL containsURL(String url){
        return new ContainsURL(url);
    }



    private static class ContainsURL extends URL{

        private String url;

        public ContainsURL(String url){
            this.url = url;
        }

        @Override
        public boolean match(String matchURLs) {
            if(url.contains(matchURLs)){
                return true;
            }else{
                return false;
            }
        }
    }
}
