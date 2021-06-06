package com.sqills.service;

import com.sqills.api.model.SqillsModel;
import com.sqills.jms.InputProducer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * A service to push data into MQ.
 *
 * @author mert.tutuncu
 */
@ApplicationScoped
public class InputProductionService {

    @Inject
    InputProducer inputProducer;

    public void processInput(SqillsModel model) {
        inputProducer.send(model.getInputStr());
    }
}
