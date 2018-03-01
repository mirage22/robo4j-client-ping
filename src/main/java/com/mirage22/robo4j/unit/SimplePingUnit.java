package com.mirage22.robo4j.unit;

import com.robo4j.ConfigurationException;
import com.robo4j.RoboContext;
import com.robo4j.RoboUnit;
import com.robo4j.configuration.Configuration;
import com.robo4j.socket.http.HttpMethod;
import com.robo4j.socket.http.HttpVersion;
import com.robo4j.socket.http.message.HttpDecoratedRequest;
import com.robo4j.socket.http.message.HttpRequestDenominator;

/**
 * @author Miroslav Wengner (@miragemiko)
 */
public class SimplePingUnit extends RoboUnit<Integer> {

    private String target;
    private String httpTarget;

    public SimplePingUnit(RoboContext context, String id) {
        super(Integer.class, context, id);
    }

    @Override
    protected void onInitialization(Configuration configuration) throws ConfigurationException {
        target = configuration.getString("target", null);
        httpTarget = configuration.getString("httpTarget", null);
    }

    @Override
    public void onMessage(Integer message) {
        System.out.println(getClass().getSimpleName() + ":number of messages: " + message);
        for (int i = 0; i < message; i++) {

            HttpRequestDenominator denominator = new HttpRequestDenominator(HttpMethod.GET, "/" + httpTarget, HttpVersion.HTTP_1_1);
            HttpDecoratedRequest result = new HttpDecoratedRequest(denominator);
//            result.addCallback("simpleConsumer");

            getContext().getReference(target).sendMessage(result);


        }
    }
}
