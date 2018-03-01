package com.mirage22.robo4j.unit;

import com.robo4j.AttributeDescriptor;
import com.robo4j.DefaultAttributeDescriptor;
import com.robo4j.RoboContext;
import com.robo4j.RoboUnit;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Miroslav Wengner (@miragemiko)
 */
public class SimpleConsumer extends RoboUnit<String> {
    public static final String PROP_GET_RECEIVED_MESSAGES = "getReceivedMessages";
    public static final AttributeDescriptor<Integer> NUMBER_OF_RECEIVED_MESSAGES = new DefaultAttributeDescriptor<>(Integer.class,
            PROP_GET_RECEIVED_MESSAGES);

    private final AtomicInteger counter = new AtomicInteger(0);

    public SimpleConsumer(RoboContext context, String id) {
        super(String.class, context, id);
    }

    @Override
    public void onMessage(String message) {
        System.out.println(getClass().getSimpleName() + " onMessage: " + message);
        counter.incrementAndGet();
    }

    @SuppressWarnings("unchecked")
    @Override
    public synchronized <R> R onGetAttribute(AttributeDescriptor<R> attribute) {
        if (attribute.getAttributeName().equals(PROP_GET_RECEIVED_MESSAGES)
                && attribute.getAttributeType() == Integer.class) {
            return (R) (Integer) counter.get();
        }
        return null;
    }
}
