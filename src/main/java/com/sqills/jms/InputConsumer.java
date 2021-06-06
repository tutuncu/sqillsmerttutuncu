package com.sqills.jms;


import com.sqills.service.InputConsumptionService;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ApplicationScoped
public class InputConsumer implements Runnable{

    @Inject
    ConnectionFactory connectionFactory;
    @Inject
    InputConsumptionService inputConsumptionService;

    private JMSContext jmsContext;
    private JMSConsumer jmsConsumer;
    private final ExecutorService scheduler = Executors.newSingleThreadExecutor();


    void onStart(@Observes StartupEvent ev) {
        scheduler.submit(this);
    }

    void onStop(@Observes ShutdownEvent ev) {
        scheduler.shutdown();
    }

    @Override
    public void run() {
        try (JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)) {
            JMSConsumer consumer = context.createConsumer(context.createQueue("prices"));
            while (true) {
                Message message = consumer.receive();
                if (message == null) return;
                inputConsumptionService.logConsumedData(message.getBody(String.class));
            }
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
