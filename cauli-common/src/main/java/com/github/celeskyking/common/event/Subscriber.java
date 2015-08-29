package com.github.celeskyking.common.event;

import com.github.celeskyking.common.annotation.ThreadSafeEvent;
import com.google.common.base.Preconditions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.Executor;

/**
 * Created by sky on 15/7/30
 */
public class Subscriber {

    private String eventName;

    private EventBus eventBus;

    private Object target;

    private final Method method;

    private Executor executor;

    private Class<?>[] parameterTypes;



    public static Subscriber create(String eventName,EventBus eventBus,Object listener,Method method,
                                    Class<?>[] parameterTypes){
        return isDeclaredThreadSafe(method)? new Subscriber(eventName,eventBus,listener,method,parameterTypes)
                :new SynchronizedSubscriber(eventName,eventBus,listener,method,parameterTypes);
    }


    private Subscriber(String eventName,EventBus eventBus,Object target,Method method,Class<?>[] parameterTypes){
        this.eventName = eventName;
        this.eventBus = eventBus;
        this.target = Preconditions.checkNotNull(target);
        this.method = method;
        this.executor=eventBus.getExecutor();
        this.method.setAccessible(true);
        this.parameterTypes=parameterTypes;
    }


    public void dispatchEvent(final Object... object){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    invokeSubscriberMethod(object);
                } catch (IllegalAccessException e) {
                    eventBus.handleSubcriberException(e, exceptionContext(object));
                }
            }
        });
    }


    private SubscriberExceptionContext exceptionContext(Object... objects){
        return new SubscriberExceptionContext(eventName,eventBus,target,method,objects);
    }


    void invokeSubscriberMethod(Object... objects) throws IllegalAccessException {
        try{
            method.invoke(target,objects);
        }catch (IllegalArgumentException e){
            throw new Error("Method rejected target/argument: " + Arrays.toString(objects),e);
        } catch (InvocationTargetException e) {
            throw new Error("Method became inaccessible: "+ Arrays.toString(objects),e);
        } catch (IllegalAccessException e) {
            if (e.getCause() instanceof Error){
                throw (Error)e.getCause();
            }
            throw e;
        }
    }

    public boolean match(Object... objects){
        if(objects!=null&&parameterTypes!=null){
            if(objects.length!=0&&parameterTypes.length!=0){
                if(objects.length==parameterTypes.length){
                    for(int i=0;i<objects.length;i++){
                        if(!checkType(parameterTypes[i],objects[i].getClass())){
                            return false;
                        }
                    }
                    return true;
                }
            }else if(objects.length==0&&parameterTypes.length==0) {
                return true;
            }
        }else if(objects==null&&parameterTypes==null){
            return true;
        }
        return false;
    }


    private static boolean isDeclaredThreadSafe(Method method){
        return method.getAnnotation(ThreadSafeEvent.class)!=null;
    }


    final static class SynchronizedSubscriber extends Subscriber{

        private SynchronizedSubscriber(String eventName, EventBus eventBus, Object target, Method method, Class<?>[] parameterTypes) {
            super(eventName, eventBus, target, method, parameterTypes);
        }

        @Override
        public void dispatchEvent(Object... object) {
            synchronized (this){
                super.dispatchEvent(object);
            }
        }
    }

    private boolean checkType(Class<?> left,Class<?> right){
        if(left==int.class){
            return Integer.class==right;
        }else if(left==boolean.class){
            return Boolean.class == right;
        }else if(left==float.class){
            return Float.class==right;
        }else if(left==double.class){
            return Double.class==right;
        }else if(left==short.class){
            return Short.class==right;
        }else if(left==char.class){
            return Character.class == right;
        }else if(left==byte.class){
            return Byte.class==right;
        }else{
            return left==right;
        }
    }
}
