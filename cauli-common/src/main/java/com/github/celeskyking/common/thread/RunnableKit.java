package com.github.celeskyking.common.thread;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.*;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by sky on 15/7/26
 */
public class RunnableKit {

    private ListeningExecutorService executorService;

    private List<ListenableFuture<?>> futures = Lists.newArrayList();

    public RunnableKit(ExecutorService executorService){
        this.executorService = MoreExecutors.listeningDecorator(executorService);
    }

    public void submit(Runnable runnable){
        ListenableFuture future = this.executorService.submit(runnable);
        futures.add(future);
    }


    public void submit(final ListeningRunnable runnable){
        ListenableFuture future = this.executorService.submit(runnable);
        Futures.addCallback(future, new FutureCallback() {
            public void onSuccess(Object o) {
                runnable.onSuccess();
            }

            public void onFailure(Throwable throwable) {
                runnable.onFailure(throwable);
            }
        });
        futures.add(future);
    }

    public void finish(){
        ListenableFuture listListenableFuture = Futures.allAsList(futures);
        Futures.addCallback(listListenableFuture, new FutureCallback<List>() {
            public void onSuccess(List ts) {
                RunnableKit.this.onSuccess();
            }
            public void onFailure(Throwable throwable) {
                RunnableKit.this.onFailure(throwable);
            }
        });
        this.executorService.shutdown();
    }


    /**
     * 当所有task执行成功时触发
     * */
    public void onSuccess(){}

    /**
     * 当所有task执行失败的时候触发
     * */
    public void onFailure(Throwable throwable) {}



    /**
     * 例子
     * */
    public static void main(String[] args) {
        RunnableKit kit = new RunnableKit(Executors.newFixedThreadPool(5)){
            @Override
            public void onSuccess() {
                System.out.println("all finished and success");
            }

            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("失败啦~~");
            }
        };
        for(int i=0;i<10;i++){
            kit.submit(new ListeningRunnable() {
                @Override
                void onSuccess() {
                    System.out.println("success-task");
                }

                @Override
                void onFailure(Throwable throwable) {
                    System.out.println("task -failure");
                }

                public void run() {
                    //System.out.println("run");
                    throw new RuntimeException("error");
                }
            });
        }
        kit.finish();
    }


}
