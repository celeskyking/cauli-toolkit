package com.github.celeskyking.common.event;

import com.google.common.util.concurrent.MoreExecutors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.Executor;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by sky on 15-7-29
 * 事件总线,用于监听事件,注册到事件总线中,
 * 事件总线会接收来自于Watcher的通知事件,
 * 该事件触发的时候,会做出相应的Callback。
 */
public class EventBus {

    private static Logger logger = LoggerFactory.getLogger(EventBus.class);

    private final Executor executor;

    private final SubscriberExceptionHandler exceptionHandler;

    private final Dispatcher dispatcher;

    private final String identifier;


    public EventBus(String identifier,Executor executor,Dispatcher dispatcher, SubscriberExceptionHandler exceptionHandler){
        this.identifier=identifier;
        this.executor = executor;
        this.dispatcher=dispatcher;
        this.exceptionHandler = exceptionHandler;
    }

    public EventBus(Executor executor,Dispatcher dispatcher, SubscriberExceptionHandler exceptionHandler){
        this("default",executor,dispatcher,exceptionHandler);
    }

    public EventBus(){
        this("default", MoreExecutors.directExecutor(),
                Dispatcher.perThreadDispatchQueue(), defaultExceptionHandler());
    }

    public EventBus(String identifier,Executor executor){
        this(identifier, executor, Dispatcher.legacyAsync(),defaultExceptionHandler());
    }

    public EventBus(Executor executor){
        this("default", executor, Dispatcher.legacyAsync(), defaultExceptionHandler());
    }

    public EventBus(String identifier,Executor executor,SubscriberExceptionHandler subscriberExceptionHandler){
        this(identifier, executor, Dispatcher.legacyAsync(), subscriberExceptionHandler);
    }

    public EventBus(Executor executor,SubscriberExceptionHandler subscriberExceptionHandler){
        this("default", executor, Dispatcher.legacyAsync(), subscriberExceptionHandler);
    }

    public EventBus(SubscriberExceptionHandler subscriberExceptionHandler){
        this("default", MoreExecutors.directExecutor(), Dispatcher.perThreadDispatchQueue(), subscriberExceptionHandler);
    }

    public EventBus(String identifier){
        this(identifier, MoreExecutors.directExecutor(),
                Dispatcher.perThreadDispatchQueue(),defaultExceptionHandler());

    }

    private static SubscriberExceptionHandler defaultExceptionHandler(){
        return new SubscriberExceptionHandler() {
            @Override
            public void handleException(Throwable exception, SubscriberExceptionContext context) {
                logger.error("",exception);
                //do nothing
            }
        };
    }


    /**
     * 事件注册的时候会保证线程安全,该事件总线保证事件的callback为同步执行..
     * */
    private SubscriberRegistry registry = new SubscriberRegistry(this);

    /**
     * @param listener 事件类.
     * */
    public void register(Object listener){
        registry.register(listener);
    }

    public void unregister(Object listener){
        registry.unregister(listener);
    }

    /**
     * 触发事件,通过eventName来触发,
     * 触发事件支持带有参数
     * */
    public void post(String event,Object ...objects){
        Iterator<Subscriber> subscriberIterator = registry.getSubscribers(event);
        if(subscriberIterator.hasNext()){
            dispatcher.dispatch(event,subscriberIterator,objects);
        }else{
            logger.warn("not found subscriber of the event:{}", event);
        }
    }


    public Executor getExecutor(){
        return this.executor;
    }


    public void handleSubcriberException(Throwable exception,SubscriberExceptionContext context){
        checkNotNull(exception);
        checkNotNull(context);
        try{
            exceptionHandler.handleException(exception,context);
        }catch (Exception e){
            logger.info(String.format(Locale.ROOT, "Exception %s thrown while handling exception: %s", e, exception));
        }
    }


    public String identifier(){
        return identifier;
    }

}
