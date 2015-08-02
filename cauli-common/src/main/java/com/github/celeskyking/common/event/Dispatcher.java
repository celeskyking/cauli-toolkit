package com.github.celeskyking.common.event;


import com.google.common.collect.Queues;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static com.google.common.base.Preconditions.*;

/**
 * Created by sky on 15/7/30
 * 参考guava的事件总线的思路来实现
 */
public abstract class Dispatcher {

    private static Logger logger = LoggerFactory.getLogger(Dispatcher.class);


    static Dispatcher perThreadDispatchQueue(){
        return new PerThreadDispatchQueue();
    }

    static Dispatcher legacyAsync(){
        return new LegacyAsyncDispatcher();
    }

    static Dispatcher immediate(){
        return new ImmediateDispatcher();
    }

    abstract void dispatch(String event,Iterator<Subscriber> subscriberIterator,Object... objects);

    private static final class PerThreadDispatchQueue extends Dispatcher{

        private final ThreadLocal<Queue<Event>>  queue= new ThreadLocal<Queue<Event>>(){
            @Override
            protected Queue<Event> initialValue() {
                return Queues.newArrayDeque();
            }
        };

        private final ThreadLocal<Boolean> dispatching = new ThreadLocal<Boolean>(){
            @Override
            protected Boolean initialValue() {
                return false;
            }
        };

        @Override
        void dispatch(String event, Iterator<Subscriber> subscriberIterator,Object... objects) {
            checkNotNull(event);
            checkNotNull(subscriberIterator);
            logger.debug("Dispatcher.dispatch() - event:{}",event);
            Queue<Event> queueForThread = queue.get();
            queueForThread.offer(new Event(subscriberIterator));
            if(!dispatching.get()){
                dispatching.set(true);
                try{
                    Event nextEvent;
                    while((nextEvent = queueForThread.poll())!=null){
                        while(nextEvent.subscribers.hasNext()){
                            Subscriber subscriber = nextEvent.subscribers.next();
                            if(subscriber.match(objects)){
                                subscriber.dispatchEvent(objects);
                            }
                        }
                    }
                }finally {
                    dispatching.remove();
                    queue.remove();
                }
            }
        }
    }


    private static final class LegacyAsyncDispatcher extends Dispatcher{

        private final ConcurrentLinkedQueue<EventWithSubscriber> queue = Queues.newConcurrentLinkedQueue();

        @Override
        void dispatch(String event, Iterator<Subscriber> subscribers,Object... objects) {
            checkNotNull(event);
            while(subscribers.hasNext()){
                queue.add(new EventWithSubscriber(subscribers.next()));
            }
            EventWithSubscriber subscriber;
            while((subscriber = queue.poll())!=null){
                subscriber.subscriber.dispatchEvent(objects);
            }
        }
    }


    private static final class ImmediateDispatcher extends Dispatcher{

        @Override
        void dispatch(String event, Iterator<Subscriber> subscribers,Object... objects) {
            checkNotNull(event);
            while (subscribers.hasNext()){
                subscribers.next().dispatchEvent(objects);
            }
        }
    }



    private static final class Event{
        private final Iterator<Subscriber> subscribers;

        private Event(Iterator<Subscriber> subscribers){
            this.subscribers = subscribers;
        }
    }

    private static final class EventWithSubscriber{
        private final Subscriber subscriber;

        private EventWithSubscriber(Subscriber subscriber){
            this.subscriber = subscriber;
        }
    }
}
