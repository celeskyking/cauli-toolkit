package com.github.celeskyking.common.event;

import com.github.celeskyking.common.annotation.SubscribeRegistry;
import com.github.celeskyking.common.reflect.ClassFilter;
import com.github.celeskyking.common.reflect.ClassFinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.util.Set;
import java.util.concurrent.Executor;
import static com.github.celeskyking.common.reflect.ClassFilter.*;

/**
 * Created by sky on 15-7-31
 */
public class AnnotationEventBus extends EventBus{

    private Logger logger = LoggerFactory.getLogger(AnnotationEventBus.class);

    public AnnotationEventBus(String identifier, Executor executor, Dispatcher dispatcher, SubscriberExceptionHandler exceptionHandler) {
        super(identifier, executor, dispatcher, exceptionHandler);
        loadSubScribers();
    }

    public AnnotationEventBus(Executor executor, Dispatcher dispatcher, SubscriberExceptionHandler exceptionHandler) {
        super(executor, dispatcher, exceptionHandler);
        loadSubScribers();
    }

    public AnnotationEventBus() {
        super();
        loadSubScribers();
    }

    public AnnotationEventBus(String identifier, Executor executor) {
        super(identifier, executor);
        loadSubScribers();
    }

    public AnnotationEventBus(Executor executor) {
        super(executor);
        loadSubScribers();
    }

    public AnnotationEventBus(String identifier, Executor executor, SubscriberExceptionHandler subscriberExceptionHandler) {
        super(identifier, executor, subscriberExceptionHandler);
        loadSubScribers();
    }

    public AnnotationEventBus(Executor executor, SubscriberExceptionHandler subscriberExceptionHandler) {
        super(executor, subscriberExceptionHandler);
        loadSubScribers();
    }

    public AnnotationEventBus(SubscriberExceptionHandler subscriberExceptionHandler) {
        super(subscriberExceptionHandler);
        loadSubScribers();
    }

    public AnnotationEventBus(String identifier) {
        super(identifier);
        loadSubScribers();
    }


    protected void loadSubScribers(){
        Set<Class<?>> classes = ClassFinder.finder().filter(ClassFilter.filter(
                withAnnotation(SubscribeRegistry.class).and(isNotInterface())
        )).find();
        for(Class<?> clazz : classes){
            checkConstructor(clazz);
            try {
                register(clazz.newInstance());
            } catch (Exception e) {
                logger.error("",e);
            }
        }
    }

    private static void checkConstructor(Class<?> clazz){
        Constructor[] constructors = clazz.getConstructors();
        if(constructors.length!=1||constructors[0].getParameterCount()!=0){
            throw new RuntimeException("SubscribeRegistry的构造方法必须有且只有一个默认构造方法");
        }
    }
}
