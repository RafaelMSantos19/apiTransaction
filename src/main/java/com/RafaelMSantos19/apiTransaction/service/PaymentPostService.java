package com.RafaelMSantos19.apiTransaction.service;

import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class PaymentPostService {

    public Map<String, Object> service() {
        return Map.of("message", "POST processado com sucesso");
    }
}
