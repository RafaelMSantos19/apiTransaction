package com.RafaelMSantos19.apiTransaction.service;

import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class PaymentGetService {

    public Map<String, Object> service() {
        return Map.of("message", "GET payment recebido");
    }
}
