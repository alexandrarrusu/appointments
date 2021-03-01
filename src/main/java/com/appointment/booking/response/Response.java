package com.appointment.booking.response;

import java.util.List;

public class Response<T> {

    private String message;
    private String code;
    private List<T> results;

    public Response(String message, String code, List<T> results) {
        this.message = message;
        this.code = code;
        this.results = results;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public List<T> getResults() {
        return results;
    }
}
