package com.RafaelMSantos19.apiTransaction.service;

import com.RafaelMSantos19.apiTransaction.model.PaymentPostModel;
import com.RafaelMSantos19.apiTransaction.model.PaymentStatus;
import com.RafaelMSantos19.apiTransaction.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Service
public class PaymentPutService {

    private final PaymentRepository paymentRepository;

    public PaymentPutService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public Map<String, Object> updatePaymentStatus(Long paymentId, String statusDescription) {
        try {
        
            PaymentStatus newStatus = Arrays.stream(PaymentStatus.values())
                .filter(s -> s.getDescription().equalsIgnoreCase(statusDescription))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                    "Status inválido. Use: " + Arrays.toString(PaymentStatus.getValidDescriptions()))
                );

            Optional<PaymentPostModel> paymentOpt = paymentRepository.findById(paymentId);
            
            if (paymentOpt.isEmpty()) {
                return Map.of(
                    "success", false,
                    "message", "Pagamento não encontrado com o ID: " + paymentId
                );
            }

            PaymentPostModel payment = paymentOpt.get();
            PaymentStatus currentStatus = payment.getStatus();

            if (!isValidStatusTransition(currentStatus, newStatus)) {
                return Map.of(
                    "success", false,
                    "message", "Transição de status inválida: " + 
                              currentStatus.getDescription() + " → " + newStatus.getDescription(),
                    "currentStatus", currentStatus.getDescription(),
                    "attemptedStatus", newStatus.getDescription()
                );
            }

            payment.setStatus(newStatus);
            paymentRepository.save(payment);

            return Map.of(
                "success", true,
                "message", "Status do pagamento atualizado com sucesso",
                "paymentId", paymentId,
                "previousStatus", currentStatus.getDescription(),
                "newStatus", newStatus.getDescription()
            );
            
        } catch (IllegalArgumentException e) {
            return Map.of(
                "success", false,
                "message", e.getMessage(),
                "validStatuses", PaymentStatus.getValidDescriptions()
            );
        }
    }

    private boolean isValidStatusTransition(PaymentStatus currentStatus, PaymentStatus newStatus) {
        if (currentStatus == PaymentStatus.PROCESSING_PENDING) {
            return newStatus == PaymentStatus.PROCESSED || newStatus == PaymentStatus.FAILED;
        }
        if (currentStatus == PaymentStatus.PROCESSED) {
            return false;
        }
        if (currentStatus == PaymentStatus.FAILED) {
            return newStatus == PaymentStatus.PROCESSING_PENDING;
        }
        return false;
    }
}