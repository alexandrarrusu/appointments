package com.appointment.booking.exception;

import com.appointment.booking.controller.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static java.util.Collections.emptyList;

@ControllerAdvice
public class HandlerController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Response> handleNotFoundException(NotFoundException ex) {
        Response response = new Response(ex.getMessage(), 404, emptyList());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BadRequestException.class)
    public ResponseEntity<Response> handleBadRequestException(BadRequestException ex) {
        Response response = new Response(ex.getMessage(), 400, emptyList());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
