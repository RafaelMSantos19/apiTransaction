package com.RafaelMSantos19.apiTransaction.service;

import com.RafaelMSantos19.apiTransaction.model.PaymentPostModel;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PaymentPostService {

    private PaymentPostModel paymentPostModel;

    public Map<String, Object> service(PaymentPostModel paymentPostModel){

        return Map.of(
            "debitCode", paymentPostModel.getDebitCode(),
            "CpfCnpj", paymentPostModel.getCpfCnpj(),
            "paymentMethod", paymentPostModel.getPaymentMethod(),
            "card", paymentPostModel.getCard(),
            "value", paymentPostModel.getValue()
        );

    }

}
