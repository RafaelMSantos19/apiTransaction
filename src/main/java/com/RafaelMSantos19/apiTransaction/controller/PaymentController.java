package com.RafaelMSantos19.apiTransaction.controller;

import com.RafaelMSantos19.apiTransaction.service.PaymentGetService;
import com.RafaelMSantos19.apiTransaction.model.PaymentPostModel;
import com.RafaelMSantos19.apiTransaction.service.PaymentPostService;
import com.RafaelMSantos19.apiTransaction.service.PaymentPutService;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.*;


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
    public Map<String, Object> processPayment(@RequestBody PaymentPostModel paymentPostModel) {

        
        try {

            System.out.println("Json Enviado na Request Post:");

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(paymentPostModel);
            System.out.println(json);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return paymentPostService.service(paymentPostModel);   
    }

    @PutMapping("/payment")
    public Map<String, Object> updatePayment() {
        return paymentPutService.service();
    }
}
