package com.sqills.api.model;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

/**
 * Model object expected to be populated in REST API
 *
 * @author mert.tutuncu
 */
public class SqillsModel {

    @NotEmpty(message = "Input can not be empty!")
    String inputStr;

    public String getInputStr() {
        return inputStr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SqillsModel that = (SqillsModel) o;
        return getInputStr().equals(that.getInputStr());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInputStr());
    }
}
