package com.sqills.service;

import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InputConsumptionService {

    private static final String BLANK_REGEX = "\\s+";
    private static final String SPACE = " ";
    private static final String NON_ALPHANUM_REGEX = "[^A-Za-z0-9]";
    private static final String UNDERSCORE = "_";
    Logger logger = Logger.getLogger(InputConsumptionService.class);

    public void processConsumedString(String data) {
        StringBuilder tokenized = new StringBuilder();
        StringBuilder replaced = new StringBuilder();
        StringBuilder capitalized = new StringBuilder();

        String[] split = data.split(BLANK_REGEX);

        for (String str : split) {
            tokenized.append(str).append(SPACE);
            replaced.append(str.replaceAll(NON_ALPHANUM_REGEX,UNDERSCORE)).append(SPACE);
            capitalized.append(str.replaceAll(NON_ALPHANUM_REGEX, UNDERSCORE).toUpperCase()).append(SPACE);
        }

        logger.info(tokenized.append(replaced).append(capitalized).toString().trim());
    }
}
