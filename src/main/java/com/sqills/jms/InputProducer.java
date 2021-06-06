package com.sqills.jms;


import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ApplicationScoped
public class InputProducer {

    @Inject
    ConnectionFactory connectionFactory;

    private JMSContext jmsContext;
    private JMSProducer jmsProducer;
    private Queue queue;


    void onStart(@Observes StartupEvent ev) {
        jmsContext = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE);
        jmsProducer = jmsContext.createProducer();
        queue = jmsContext.createQueue("data");
    }

    void onStop(@Observes ShutdownEvent ev) {
        jmsContext.close();
    }


    public void send(String data) {
        jmsProducer.send(queue,data);
    };
}
