package com.RafaelMSantos19.apiTransaction.handler;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.RafaelMSantos19.apiTransaction.service.PaymentDeleteService.PaymentNotFoundException;
import com.RafaelMSantos19.apiTransaction.service.PaymentPutService.PaymentInvalidStatusException;

@RestControllerAdvice
public class PaymentExceptionHandler {

    @ExceptionHandler(PaymentInvalidStatusException.class)
    public ResponseEntity<Map<String, String>> handlePaymentInvalidStatusException(PaymentInvalidStatusException e) {
        Map<String, String> response = Map.of("error", e.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<Map<String, String>> handlePaymentNotFoundException(PaymentNotFoundException e) {
        Map<String, String> response = Map.of("error", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}