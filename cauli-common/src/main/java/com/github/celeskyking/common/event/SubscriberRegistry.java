package com.github.celeskyking.common.event;

import com.github.celeskyking.common.annotation.Subscribe;
import com.google.common.base.MoreObjects;
import com.google.common.collect.*;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by sky on 15/7/30r
 * 事件注册表
 */
public class SubscriberRegistry {

    private EventBus eventBus;

    private final ConcurrentMap<String,CopyOnWriteArraySet<Subscriber>> subscribers = Maps.newConcurrentMap();

    public SubscriberRegistry(EventBus eventBus){
        this.eventBus = eventBus;
    }

    public void register(Object listener){
        Multimap<String,Subscriber> listenerMethods = findAllSubScribers(listener);
        for(Map.Entry<String,Collection<Subscriber>> entry :listenerMethods.asMap().entrySet()){
            String eventName = entry.getKey();
            Collection<Subscriber> eventMethodsListener = entry.getValue();
            CopyOnWriteArraySet<Subscriber> eventSubScribers = subscribers.get(eventName);
            if(eventSubScribers==null){
                CopyOnWriteArraySet<Subscriber> newSet = new CopyOnWriteArraySet<>();
                eventSubScribers = MoreObjects.firstNonNull(subscribers.putIfAbsent(eventName,newSet),newSet);
            }
            eventSubScribers.addAll(eventMethodsListener);
        }
    }

    public void unregister(Object listener){
        Multimap<String,Subscriber> listenerMethods = findAllSubScribers(listener);
        for(Map.Entry<String,Collection<Subscriber>> entry : listenerMethods.asMap().entrySet()){
            String eventName = entry.getKey();
            Collection<Subscriber> listenerSubScribers = entry.getValue();
            CopyOnWriteArraySet<Subscriber> currentSubscribers = subscribers.get(eventName);
            if(currentSubscribers == null || !currentSubscribers.removeAll(listenerSubScribers)){
                throw new IllegalArgumentException("missing event subscriber of an annotated method. Is "+ listener+ "registered ?");
            }
        }
    }


    Multimap<String,Subscriber> findAllSubScribers(Object listener){
        Multimap<String,Subscriber> methodsListener = HashMultimap.create();
        for(Method method : findSubScribeMethods(listener)){
            Class<?>[] parameterTypes = method.getParameterTypes();
            String eventName = getEventName(method);
            methodsListener.put(eventName,Subscriber.create(eventName,eventBus,listener,method,parameterTypes));
        }
        return methodsListener;
    }


    List<Method> findSubScribeMethods(Object listener){
        List<Method> methods = Lists.newArrayList();
        Class<?> clazz = listener.getClass();
        for(Method method:clazz.getDeclaredMethods()){
            if(method.isAnnotationPresent(Subscribe.class)&&!method.isSynthetic()&& !Modifier.isStatic(method.getModifiers())){
                methods.add(method);
            }
        }
        return methods;
    }

    String getEventName(Method method){
        return method.getAnnotation(Subscribe.class).value();
    }

    public Iterator<Subscriber> getSubscribers(String event){
        List<Iterator<Subscriber>> subscriberIterator = Lists.newArrayListWithCapacity(1);
        CopyOnWriteArraySet<Subscriber> eventSubscribers = subscribers.get(event);
        if(eventSubscribers!=null){
            subscriberIterator.add(eventSubscribers.iterator());
        }
        return Iterators.concat(subscriberIterator.iterator());
    }
}
