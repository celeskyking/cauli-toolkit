package com.github.celeskyking.common.event;

/**
 * Created by sky on 15/7/30
 */
public interface SubscriberExceptionHandler {

    void handleException(Throwable exception, SubscriberExceptionContext context);
}
