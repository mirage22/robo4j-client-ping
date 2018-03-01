package com.mirage22.robo4j.codec;

import com.robo4j.socket.http.codec.AbstractHttpMessageCodec;
import com.robo4j.socket.http.units.HttpProducer;

/**
 * @author Miroslav Wengner (@miragemiko)
 */
@HttpProducer
public class SimpleMessageCodec extends AbstractHttpMessageCodec<SimpleMessage> {
    public SimpleMessageCodec() {
        super(SimpleMessage.class);
    }
}
