package com.RafaelMSantos19.apiTransaction.service;

import com.RafaelMSantos19.apiTransaction.model.PaymentPostModel;
import com.RafaelMSantos19.apiTransaction.model.PaymentStatus;
import com.RafaelMSantos19.apiTransaction.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentGetService {

    @Autowired
    private PaymentRepository paymentRepository;
    
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public List<PaymentPostModel> service(
            String debitCodeStr, 
            String cpfCnpj, 
            String statusStr, 
            String startDateStr, 
            String endDateStr) {
        
        PaymentStatus status = getPaymentStatus(statusStr);
        Integer debitCode = getDebitCode(debitCodeStr);
        LocalDateTime startDate = getLocalDateTime(startDateStr);
        LocalDateTime endDate = getLocalDateTime(endDateStr);

        return paymentRepository.findPayments(debitCode, cpfCnpj, status, startDate, endDate, PaymentStatus.INATIVO);
    }

    private PaymentStatus getPaymentStatus(String statusStr) {
        if (statusStr == null) {
            return null;
        }
        return Arrays.stream(PaymentStatus.values())
                .filter(s -> s.getDescription().equals(statusStr))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Status não encontrado"));
    }

    private Integer getDebitCode(String debitCodeStr) {
        if (debitCodeStr == null) {
            return null;
        }
        try {
            return Integer.parseInt(debitCodeStr);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Débito código inválido");
        }
    }

    private LocalDateTime getLocalDateTime(String dateStr) {
        if (dateStr == null) {
            return null;
        }
        try {
            return LocalDateTime.parse(dateStr, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Data inválida");
        }
    }
}