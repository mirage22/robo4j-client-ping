package com.mirage22.robo4j;

import com.robo4j.RoboBuilder;
import com.robo4j.RoboContext;
import com.robo4j.RoboReference;
import com.robo4j.util.SystemUtil;

import java.io.InputStream;

/**
 * @author Miroslav Wengner (@miragemiko)
 */
public class ClientPingMain {

    public static void main(String[] args) throws Exception {
        InputStream configInputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("robo4j.xml");
        RoboBuilder builder = new RoboBuilder(
                Thread.currentThread().getContextClassLoader().getResourceAsStream("robo4jSystem.xml"));

        builder.add(configInputStream);
        RoboContext pingSystem = builder.build();

        pingSystem.start();
        System.out.println("pintSystem: State after start:");
        System.out.println(SystemUtil.printStateReport(pingSystem));


        RoboReference<Integer> decoratedProducer = pingSystem.getReference("simplePingUnit");
        decoratedProducer.sendMessage(40);

        Thread.sleep(2000);

        pingSystem.shutdown();
        System.out.println("pintSystem: State after shutdown:");
        System.out.println(SystemUtil.printStateReport(pingSystem));


    }
}
