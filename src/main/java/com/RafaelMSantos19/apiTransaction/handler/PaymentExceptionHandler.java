package com.RafaelMSantos19.apiTransaction.handler;

import java.util.HashMap;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.RafaelMSantos19.apiTransaction.service.PaymentDeleteService.PaymentInvalidStatusException;
import com.RafaelMSantos19.apiTransaction.service.PaymentDeleteService.PaymentNotFoundException;

import java.util.Map;

@RestControllerAdvice
@ComponentScan(basePackages = {"com.RafaelMSantos19.apiTransaction"})
public class PaymentExceptionHandler {

     @ExceptionHandler(PaymentInvalidStatusException.class)
    public ResponseEntity<Map<String, String>> handlePaymentInvalidStatusException(PaymentInvalidStatusException e) {
        Map<String, String> response = new HashMap<>();
        response.put("error", e.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<Map<String, String>> handlePaymentNotFoundException(PaymentNotFoundException e) {
        Map<String, String> response = new HashMap<>();
        response.put("error", e.getMessage());
        return ResponseEntity.notFound().body(response);
    }
    
}
