package com.github.celeskyking.common.event;

import java.lang.reflect.Method;

/**
 * Created by sky on 15/7/30
 */
public class SubscriberExceptionContext {

    private final EventBus eventBus;

    private final Object[] parameters;

    private final Object subscriber;

    private final Method subscriberMethod;

    private final String eventName;


    SubscriberExceptionContext(String eventName,EventBus eventBus,Object subscriber,Method subscriberMethod,Object... parameters){
        this.eventName = eventName;
        this.eventBus=eventBus;
        this.subscriber = subscriber;
        this.subscriberMethod = subscriberMethod;
        this.parameters = parameters;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public Object getSubscriber() {
        return subscriber;
    }

    public Method getSubscriberMethod() {
        return subscriberMethod;
    }

    public String getEventName() {
        return eventName;
    }
}
