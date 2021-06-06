package com.sqills.service;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InputConsumptionService {

    public void logConsumedData(String data) {
    System.out.println(data);
    }
}
