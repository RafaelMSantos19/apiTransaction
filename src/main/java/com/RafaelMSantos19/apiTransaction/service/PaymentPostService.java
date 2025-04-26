package com.RafaelMSantos19.apiTransaction.service;

import com.RafaelMSantos19.apiTransaction.model.PaymentPostModel;
import com.RafaelMSantos19.apiTransaction.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentPostService {

    private final PaymentRepository paymentRepository;

    public PaymentPostService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Map<String, Object> service(PaymentPostModel paymentPostModel) {
        
        PaymentPostModel savedPayment = paymentRepository.save(paymentPostModel);
        
        Map<String, Object> response = new HashMap<>();
        response.put("id", savedPayment.getId());
        response.put("debitCode", savedPayment.getDebitCode());
        response.put("cpfCnpj", savedPayment.getCpfCnpj());
        response.put("paymentMethod", savedPayment.getPaymentMethod());
        response.put("card", savedPayment.getCard());
        response.put("payment_value", savedPayment.getValue());
        response.put("paymentDate", savedPayment.getPaymentDate());
        response.put("status", savedPayment.getStatus());
        
        return response;
    }
}