package com.RafaelMSantos19.apiTransaction.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RafaelMSantos19.apiTransaction.model.PaymentPostModel;
import com.RafaelMSantos19.apiTransaction.model.PaymentStatus;
import com.RafaelMSantos19.apiTransaction.repository.PaymentRepository;

@Service
public class PaymentDeleteService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentDeleteService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Map<String, String> deletePayment(Long id) {
        Optional<PaymentPostModel> paymentOptional = paymentRepository.findById(id);
        if (paymentOptional.isPresent()) {
            PaymentPostModel payment = paymentOptional.get();
            if (payment.getStatus() == PaymentStatus.PROCESSING_PENDING) {
                payment.setStatus(PaymentStatus.INATIVO);
                paymentRepository.save(payment);
                Map<String, String> response = new HashMap<>();
                response.put("message", "Pagamento com ID " + id + " excluído com sucesso");
                return response;
            } else {
                throw new PaymentInvalidStatusException(id);
            }
        } else {
            throw new PaymentNotFoundException(id);
        }
    }

    public static class PaymentInvalidStatusException extends RuntimeException {
        public PaymentInvalidStatusException(Long id) {
            super("ID " + id + " é inválido para essa operação, pois o pagamento não está com status Pendente de Processamento");
        }
    }

    public static class PaymentNotFoundException extends RuntimeException {
        public PaymentNotFoundException(Long id) {
            super("Pagamento com ID " + id + " não encontrado");
        }
    }
}