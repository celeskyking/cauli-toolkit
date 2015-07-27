package com.github.celeskyking.common.thread;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by sky on 15/7/19
 * 该类是任务类,主要用来处理调度,作为做小的可执行单元的概念
 * 该类是含有一个泛型,该泛型代表了调度中的资源
 */
public abstract class Task<T> {

    public abstract T handle() throws Exception;

    private List<TaskListener<T>> listeners = Lists.newArrayList();


    public void onSuccess(T t){
        for(TaskListener<T> listener : listeners){
            listener.onSuccess(t);
        }
    }

    public void onFailure(Throwable throwable){
        for(TaskListener<T> listener : listeners){
            listener.onFailure(throwable);
        }
    }

    public void register(TaskListener<T> listener){
        this.listeners.add(listener);
    }
}
