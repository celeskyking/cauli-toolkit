package com.github.celeskyking.common.thread;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.*;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by sky on 15/7/28
 */
public class ScheduleKit {

    private ListeningScheduledExecutorService scheduledExecutorService;

    private List<ListenableScheduledFuture<?>> futures = Lists.newArrayList();

    public ScheduleKit(ScheduledExecutorService scheduledExecutorService){
        this.scheduledExecutorService = MoreExecutors.listeningDecorator(scheduledExecutorService);
    }

    public void schedule(Runnable runnable,long delay,TimeUnit timeUnit){
        ListenableScheduledFuture<?> future = this.scheduledExecutorService.schedule(runnable,delay,timeUnit);
        futures.add(future);
    }

    public void schedule(final ListeningRunnable runnable,long delay,TimeUnit timeUnit){
        ListenableScheduledFuture<?> future = this.scheduledExecutorService.schedule(runnable,delay,timeUnit);
        Futures.addCallback(future, new FutureCallback<Object>() {
            public void onSuccess(Object result) {
                runnable.onSuccess();
            }

            public void onFailure(Throwable t) {
                runnable.onFailure(t);
            }
        });
        futures.add(future);
    }


}
