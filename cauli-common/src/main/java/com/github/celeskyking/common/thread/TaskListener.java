package com.github.celeskyking.common.thread;

/**
 * Created by sky on 15/7/26
 */
public interface TaskListener<T> {

    void onSuccess(T t);

    void onFailure(Throwable throwable);


}
