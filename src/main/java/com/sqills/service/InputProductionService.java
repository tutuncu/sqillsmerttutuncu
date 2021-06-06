package com.sqills.service;

import com.sqills.api.model.SqillsModel;
import com.sqills.jms.InputProducer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class InputProductionService {

    @Inject
    InputProducer inputProducer;

    public void processInput(SqillsModel model) {
        inputProducer.send(model.getInputStr());
    }
}
