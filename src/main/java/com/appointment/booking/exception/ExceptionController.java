package com.appointment.booking.exception;

import com.appointment.booking.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.mail.MessagingException;

import static java.util.Collections.emptyList;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Response<Object>> handleNotFoundException(NotFoundException ex) {
        Response<Object> response = new Response<>(ex.getMessage(), "404", emptyList());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = MessagingException.class)
    public ResponseEntity<Response<Object>> handleMessagingException(MessagingException ex) {
        Response<Object> response = new Response<>(ex.getMessage(), "500", emptyList());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
