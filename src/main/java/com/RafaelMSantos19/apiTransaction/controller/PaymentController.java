package com.RafaelMSantos19.apiTransaction.controller;

import com.RafaelMSantos19.apiTransaction.service.PaymentGetService;
import com.RafaelMSantos19.apiTransaction.service.PaymentPostService;
import com.RafaelMSantos19.apiTransaction.service.PaymentPutService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class PaymentController {

    private final PaymentGetService paymentGetService;
    private final PaymentPostService paymentPostService;
    private final PaymentPutService paymentPutService;

    public PaymentController(
                             PaymentGetService paymentGetService, 
                             PaymentPostService paymentPostService,
                             PaymentPutService paymentPutService
    ){
        this.paymentGetService =  paymentGetService;
        this.paymentPostService = paymentPostService;
        this.paymentPutService = paymentPutService;
    }



    @GetMapping("/payment")
    public Map<String, Object> getPayment() {
        return paymentGetService.service();
    }

    @PostMapping("/payment")
    public Map<String, Object> processPayment() {
        return paymentPostService.service();
    }

    @PutMapping("/payment")
    public Map<String, Object> updatePayment() {
        return paymentPutService.service();
    }
}
