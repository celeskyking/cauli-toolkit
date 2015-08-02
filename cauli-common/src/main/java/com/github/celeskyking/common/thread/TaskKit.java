package com.github.celeskyking.common.thread;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.*;

import java.util.List;
import java.util.concurrent.*;

/**
 * Created by sky on 15/7/25
 * 一个线程池的帮助类,基于guava的异步线程
 */
public class TaskKit<T> {

    private ListeningExecutorService executorService;

    private List<ListenableFuture<T>> futures = Lists.newArrayList();

    private List<T> results = Lists.newArrayList();

    public TaskKit(ExecutorService executor){
        this.executorService = MoreExecutors.listeningDecorator(executor);
        this.fallBack = new FallBack<T>() {
            public T fallback(Throwable throwable) throws Throwable {
                throw throwable;
            }
        };
    }




    public void submit(Callable<T> callback,final TaskListener<T>... listeners){
        final ListenableFuture<T> listenableFuture = this.executorService.submit(callback);
        FutureFallback<T> futureFallback = new FutureFallback<T>() {
            public ListenableFuture<T> create(Throwable throwable) throws Exception {
                SettableFuture<T> settableFuture = SettableFuture.create();
                try{
                    T t = fallBack.fallback(throwable);
                    settableFuture.set(t);
                } catch (Throwable throwable1) {
                    settableFuture.setException(throwable1);
                }
                return settableFuture;
            }
        };
        ListenableFuture<T> future = Futures.withFallback(listenableFuture, futureFallback);
        Futures.addCallback(future, new FutureCallback<T>() {
            public void onSuccess(T t) {
                results.add(t);
                if(listeners!=null&&listeners.length>0){
                    for(TaskListener<T> listener : listeners){
                        listener.onSuccess(t);
                    }
                }

            }

            public void onFailure(Throwable throwable) {
                if(listeners!=null&&listeners.length>0){
                    for(TaskListener<T> listener : listeners){
                        listener.onFailure(throwable);
                    }
                }
            }
        });
        futures.add(listenableFuture);
    }


    private FallBack<T> fallBack;

    /***如果出现了异常,可以进行异常恢复操作,阻止异常抛出,或者特定异常返回指定结果*/
    public void withFallback(FallBack<T> fallback){
        this.fallBack=fallback;
    }

    /**
     * 提交异步执行的方法
     * */
    public void submit(final Task<T> task){
        ListenableFuture<T> listenableFuture=this.executorService.submit(new Callable<T>() {
            public T call() throws Exception {
                return task.handle();
            }
        });
        FutureFallback<T> futureFallback = new FutureFallback<T>() {
            public ListenableFuture<T> create(Throwable throwable) throws Exception {
                SettableFuture<T> settableFuture = SettableFuture.create();
                try{
                    T t = fallBack.fallback(throwable);
                    settableFuture.set(t);
                } catch (Throwable throwable1) {
                    settableFuture.setException(throwable1);
                }
                return settableFuture;
            }
        };
        ListenableFuture<T> future = Futures.withFallback(listenableFuture, futureFallback);
        Futures.addCallback(future, new FutureCallback<T>() {
            public void onSuccess(T t) {
                results.add(t);
                task.onSuccess(t);
            }

            public void onFailure(Throwable throwable) {
                task.onFailure(throwable);
            }
        });
        futures.add(listenableFuture);
    }
    /**
     * 执行一组任务
     * */
    public void invokeAll(List<Task<T>> tasks){
        for(final Task<T> task : tasks){
            submit(task);
        }
    }


    /**
     * 执行一组任务
     * */
    public void invokeAll(List<Callable<T>> callables,TaskListener<T>... listeners){
        for(final Callable<T> task : callables){
            submit(task,listeners);
        }
    }

    public boolean isTerminated(){
        return this.executorService.isTerminated();
    }



    /**该方法会阻塞所有的任务完成*/
    public void finish(){
        ListenableFuture<List<T>> listListenableFuture = Futures.allAsList(futures);
        Futures.addCallback(listListenableFuture, new FutureCallback<List<T>>() {
            public void onSuccess(List<T> ts) {
                TaskKit.this.onSuccess(ts);
            }
            public void onFailure(Throwable throwable) {
                TaskKit.this.onFailure(throwable);
            }
        });
        this.executorService.shutdown();
    }
    /**
     * 退出线程池,如果不退出的话，主线中会一直被阻塞
     * */
    public void shutdown(){
        this.executorService.shutdown();
    }


    public void isShutdown(){
        this. executorService.isShutdown();
    }

    /**
     * 当task执行成功时触发
     * */
    public void onSuccess(List<T> results){}

    /**
     * 当task执行成功时触发
     * */
    public void onFailure(Throwable throwable){}

}
