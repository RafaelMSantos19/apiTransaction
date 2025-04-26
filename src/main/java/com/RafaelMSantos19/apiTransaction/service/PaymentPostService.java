package com.RafaelMSantos19.apiTransaction.service;

import com.RafaelMSantos19.apiTransaction.model.PaymentPostModel;
import com.RafaelMSantos19.apiTransaction.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentPostService {

    private final PaymentRepository paymentRepository;

    public PaymentPostService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public Map<String, Object> service(PaymentPostModel paymentPostModel) {
        
        validatePaymentMethodAndCard(paymentPostModel);
        
        
        paymentPostModel.setPaymentDate(java.time.LocalDateTime.now());
        
        
        PaymentPostModel savedPayment = paymentRepository.save(paymentPostModel);
        
        
        return buildResponse(savedPayment);
    }

    private void validatePaymentMethodAndCard(PaymentPostModel payment) {
        String paymentMethod = payment.getPaymentMethod();
        String cardNumber = payment.getCard();
        
        
        if (isCardPayment(paymentMethod)) {
            if (cardNumber == null || cardNumber.trim().isEmpty()) {
                throw new IllegalArgumentException("Número do cartão é obrigatório para pagamento com cartão");
            }
            
            
            if (!cardNumber.matches("\\d{13,19}")) {
                throw new IllegalArgumentException("Número do cartão inválido. Deve conter entre 13 e 19 dígitos");
            }
            
           
            payment.setCard(maskCardNumber(cardNumber));
        } else if (cardNumber != null && !cardNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Número do cartão não deve ser enviado para este método de pagamento");
        }
    }

    private boolean isCardPayment(String paymentMethod) {
        return "cartao_credito".equalsIgnoreCase(paymentMethod) || 
               "cartao_debito".equalsIgnoreCase(paymentMethod);
    }

    private String maskCardNumber(String cardNumber) {
        
        return cardNumber;
    }

    private Map<String, Object> buildResponse(PaymentPostModel payment) {
        Map<String, Object> response = new HashMap<>();
        response.put("id", payment.getId());
        response.put("debitCode", payment.getDebitCode());
        response.put("cpfCnpj", payment.getCpfCnpj());
        response.put("paymentMethod", payment.getPaymentMethod());
        
        
        if (isCardPayment(payment.getPaymentMethod())) {
            response.put("card", payment.getCard());
        }
        
        response.put("payment_value", payment.getValue());
        response.put("paymentDate", payment.getPaymentDate());
        response.put("status", payment.getStatus());
        
        return response;
    }
}