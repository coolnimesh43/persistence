package com.coolnimesh43.persistence.rest.dto;

import java.io.Serializable;
import java.util.List;

public class GenericResponse implements Serializable {

    private String returnVal;
    private String returnMessage;
    private List<ResponseError> errors;

    public GenericResponse() {
        super();
    }

    public GenericResponse(String returnVal, String returnMessage) {
        super();
        this.returnVal = returnVal;
        this.returnMessage = returnMessage;
    }

    public String getReturnVal() {
        return returnVal;
    }

    public void setReturnVal(String returnVal) {
        this.returnVal = returnVal;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public List<ResponseError> getErrors() {
        return errors;
    }

    public void setErrors(List<ResponseError> errors) {
        this.errors = errors;
    }
}
