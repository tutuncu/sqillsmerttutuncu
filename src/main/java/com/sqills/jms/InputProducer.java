package com.sqills.jms;


import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.*;

@ApplicationScoped
public class InputProducer {

    @Inject
    ConnectionFactory connectionFactory;


    public void send(String data) {
        try (JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)) {
            context.createProducer().send(context.createQueue("data"), data);
        }
    };
}
