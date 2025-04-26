package com.RafaelMSantos19.apiTransaction.service;

import com.RafaelMSantos19.apiTransaction.model.PaymentPostModel;
import com.RafaelMSantos19.apiTransaction.model.PaymentStatus;
import com.RafaelMSantos19.apiTransaction.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

@Service
public class PaymentPutService {

    private final PaymentRepository paymentRepository;

    public PaymentPutService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public Map<String, Object> updatePaymentStatus(Long paymentId, String statusStr) {
        try {
            // Converter String para PaymentStatus
            PaymentStatus newStatus = PaymentStatus.valueOf(statusStr.toUpperCase());
            
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
                    "message", "Transição de status inválida: " + currentStatus.getName() + " → " + newStatus.getName(),
                    "currentStatus", currentStatus.getName(),
                    "attemptedStatus", newStatus.getName()
                );
            }

            payment.setStatus(newStatus);
            paymentRepository.save(payment);

            return Map.of(
                "success", true,
                "message", "Status do pagamento atualizado com sucesso",
                "paymentId", paymentId,
                "previousStatus", currentStatus.getName(),
                "newStatus", newStatus.getName()
            );
            
        } catch (IllegalArgumentException e) {
            return Map.of(
                "success", false,
                "message", "Status inválido: " + statusStr,
                "validStatuses", new String[] {
                    PaymentStatus.PROCESSING_PENDING.name(),
                    PaymentStatus.PROCESSED.name(),
                    PaymentStatus.FAILED.name()
                }
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