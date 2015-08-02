package com.github.celeskying.common.test;

import com.github.celeskyking.common.event.AnnotationEventBus;
import com.github.celeskyking.common.event.EventBus;
import org.junit.Test;

/**
 * Created by sky on 15/8/1
 */
public class SubscriberTest {

    @Test
    public void testSubscriber() throws InterruptedException {
        EventBus eventBus = new EventBus();
        eventBus.register(new MySubscriber());
        eventBus.post("test", "tianqing.wang");
        eventBus.post("test",1);
    }

    @Test
    public void testSubscriberWithNoParam(){
        EventBus eventBus = new EventBus();
        eventBus.register(new MySubscriber());
        eventBus.post("test_param");
    }

    @Test
    public void testAnnoSubscriber(){
        EventBus eventBus = new AnnotationEventBus();
        eventBus.post("test",1);
    }
}
