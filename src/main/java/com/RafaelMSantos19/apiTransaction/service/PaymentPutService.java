package com.RafaelMSantos19.apiTransaction.service;

import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class PaymentPutService {

    public Map<String, Object> service() {
        return Map.of("message", "PUT pagamento atualizado com sucesso");
    }
}
