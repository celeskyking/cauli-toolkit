package com.github.celeskyking.cauli.junit.parallel;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by sky on 15/9/20
 */
public class ParallelExecutor {

    private int threads;

    private ExecutorService executorService;

    private CompletionService<Void> completionService;

    private ParallelExecutor(int threads){
        this.threads = threads;
        this.executorService = Executors.newFixedThreadPool(threads,new ParallelThreadFactory("testcase-pool"));
        this.completionService = new ExecutorCompletionService<>(executorService);
    }


    private static class ParallelThreadFactory implements ThreadFactory{

        private final AtomicInteger poolNumber = new AtomicInteger(1);
        final AtomicInteger threadNumber = new AtomicInteger(1);
        final ThreadGroup threadGroup;

        public ParallelThreadFactory(String poolName){
            this.threadGroup = new ThreadGroup(poolName+"-"+poolNumber.getAndIncrement());
        }


        @Override
        public Thread newThread(Runnable r) {
            return new Thread(threadGroup,r,threadGroup.getName()+"thread-"+threadNumber.getAndIncrement(),0);
        }
    }

    public int getThreads() {
        return threads;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }
}
