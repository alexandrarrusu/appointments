package com.appointment.booking.controller.response;

import java.util.List;

public class Response<T> {

    private String message;
    private int code;
    private List<T> response;

    public Response(String message, int code, List<T> patientsList) {
        this.message = message;
        this.code = code;
        this.response = patientsList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<T> getPatientsList() {
        return response;
    }

    public void setPatientsList(List<T> patientsList) {
        this.response = patientsList;
    }
}
