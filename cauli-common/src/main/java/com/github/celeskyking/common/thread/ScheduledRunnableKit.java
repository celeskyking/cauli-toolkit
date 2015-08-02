package com.github.celeskyking.common.thread;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.*;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by sky on 15/7/28
 */
public class ScheduledRunnableKit{

    private ListeningScheduledExecutorService scheduledExecutorService;

    private List<ListenableScheduledFuture<?>> futures = Lists.newArrayList();

    public ScheduledRunnableKit(ScheduledExecutorService scheduledExecutorService){
        this.scheduledExecutorService = MoreExecutors.listeningDecorator(scheduledExecutorService);
    }

    private void handleRunnableFuture(ListenableScheduledFuture future){
        futures.add(future);
        ListenableFuture listListenableFuture = Futures.allAsList(futures);
        Futures.addCallback(listListenableFuture, new FutureCallback<List>() {
            public void onSuccess(List ts) {
                ScheduledRunnableKit.this.onSuccess();
            }

            public void onFailure(Throwable throwable) {
                ScheduledRunnableKit.this.onFailure(throwable);
            }
        });
    }




    public void schedule(Runnable runnable,long delay,TimeUnit timeUnit){
        ListenableScheduledFuture<?> future = this.scheduledExecutorService.schedule(runnable, delay, timeUnit);
        handleRunnableFuture(future);
    }

    public void schedule(final ListeningRunnable runnable,long delay,TimeUnit timeUnit){
        ListenableScheduledFuture<?> future = this.scheduledExecutorService.schedule(runnable, delay, timeUnit);
        Futures.addCallback(future, new FutureCallback<Object>() {
            public void onSuccess(Object result) {
                runnable.onSuccess();
            }

            public void onFailure(Throwable t) {
                runnable.onFailure(t);
            }
        });
        handleRunnableFuture(future);
    }

    public void scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit){
        ListenableScheduledFuture<?> future = this.scheduledExecutorService.scheduleAtFixedRate(command, initialDelay, period, unit);
        handleRunnableFuture(future);
    }

    public void scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit){
        ListenableScheduledFuture<?> future = this.scheduledExecutorService.scheduleWithFixedDelay(command, initialDelay, delay, unit);
        handleRunnableFuture(future);
    }

    public void scheduleAtFixedRate(final ListeningRunnable command, long initialDelay, long period, TimeUnit unit){
        ListenableScheduledFuture<?> future = this.scheduledExecutorService.scheduleAtFixedRate(command, initialDelay, period, unit);
        Futures.addCallback(future, new FutureCallback<Object>() {
            public void onSuccess(Object result) {
                command.onSuccess();
            }

            public void onFailure(Throwable t) {
                command.onFailure(t);
            }
        });
        handleRunnableFuture(future);
    }

    public void scheduleWithFixedDelay(final ListeningRunnable command, long initialDelay, long delay, TimeUnit unit){
        ListenableScheduledFuture<?> future = this.scheduledExecutorService.scheduleWithFixedDelay(command, initialDelay, delay, unit);
        Futures.addCallback(future, new FutureCallback<Object>() {
            public void onSuccess(Object result) {
                command.onSuccess();
            }

            public void onFailure(Throwable t) {
                command.onFailure(t);
            }
        });
        handleRunnableFuture(future);
    }


    /**
     * 当所有task执行成功时触发
     * */
    public void onSuccess(){}

    /**
     * 当所有task执行失败的时候触发
     * */
    public void onFailure(Throwable throwable) {}

    public void shutdown(){
        this.scheduledExecutorService.shutdown();
    }
}
