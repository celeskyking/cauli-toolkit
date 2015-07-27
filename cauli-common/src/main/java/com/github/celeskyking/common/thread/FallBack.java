package com.github.celeskyking.common.thread;

/**
 * Created by sky on 15/7/26
 */
public interface FallBack<T> {

    T fallback(Throwable throwable) throws Throwable;
}
