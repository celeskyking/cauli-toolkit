package com.github.celeskyking.common.thread;

/**
 * Created by sky on 15/7/26
 */
public abstract class ListeningRunnable implements Runnable{


    abstract void onSuccess();

    abstract void onFailure(Throwable throwable);

}
