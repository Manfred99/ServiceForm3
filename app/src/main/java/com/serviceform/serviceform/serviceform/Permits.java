package com.serviceform.serviceform.serviceform;

public class Permits {

    private String explanation;
    private String valueNumeric;

    public Permits(String explanation, String valueNumeric) {
        this.explanation = explanation;
        this.valueNumeric = valueNumeric;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getValueNumeric() {
        return valueNumeric;
    }

    public void setValueNumeric(String valueNumeric) {
        this.valueNumeric = valueNumeric;
    }

    @Override
    public String toString() {
        return explanation ;

    }
}
