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

        jmsContext = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE);
        jmsConsumer = jmsContext.createConsumer(jmsContext.createQueue("data"));
        scheduler.submit(this);
    }

    void onStop(@Observes ShutdownEvent ev) {
        jmsContext.close();
        scheduler.shutdown();
    }

    @Override
    public void run() {
            while (true) {
                Message message = jmsConsumer.receive();
                if (message == null) {
                    return;
                }
                try {
                    inputConsumptionService.processConsumedString(message.getBody(String.class));
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }

    }
}
