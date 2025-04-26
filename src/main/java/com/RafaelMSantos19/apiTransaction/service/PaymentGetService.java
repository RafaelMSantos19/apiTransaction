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
        
       
        PaymentStatus status = null;
        if (statusStr != null) {
            
            Optional<PaymentStatus> foundStatus = Arrays.stream(PaymentStatus.values())
                .filter(s -> s.getDescription().equals(statusStr))
                .findFirst();
                
            if (foundStatus.isPresent()) {
                status = foundStatus.get();
            } else {
                
                return List.of();
            }
        }
        

        Integer debitCode = null;
        if (debitCodeStr != null) {
            try {
                debitCode = Integer.parseInt(debitCodeStr);
            } catch (NumberFormatException e) {
               
                return List.of();
            }
        }
        

        LocalDateTime startDate = null;
        if (startDateStr != null) {
            try {
                startDate = LocalDateTime.parse(startDateStr, DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                
                return List.of();
            }
        }
        
        LocalDateTime endDate = null;
        if (endDateStr != null) {
            try {
                endDate = LocalDateTime.parse(endDateStr, DATE_FORMATTER);
            } catch (DateTimeParseException e) {

                return List.of();
            }
        }


        if (startDate != null && endDate != null) {


            if (debitCode != null && cpfCnpj != null && status != null) {
                return paymentRepository.findByDebitCodeAndCpfCnpjAndStatusAndPaymentDateBetween(
                        debitCode, cpfCnpj, status, startDate, endDate);
            } else if (debitCode != null && cpfCnpj != null) {
                return paymentRepository.findByDebitCodeAndCpfCnpjAndPaymentDateBetween(
                        debitCode, cpfCnpj, startDate, endDate);
            } else if (debitCode != null && status != null) {
                return paymentRepository.findByDebitCodeAndStatusAndPaymentDateBetween(
                        debitCode, status, startDate, endDate);
            } else if (cpfCnpj != null && status != null) {
                return paymentRepository.findByCpfCnpjAndStatusAndPaymentDateBetween(
                        cpfCnpj, status, startDate, endDate);
            } else if (debitCode != null) {
                return paymentRepository.findByDebitCodeAndPaymentDateBetween(
                        debitCode, startDate, endDate);
            } else if (cpfCnpj != null) {
                return paymentRepository.findByCpfCnpjAndPaymentDateBetween(
                        cpfCnpj, startDate, endDate);
            } else if (status != null) {
                return paymentRepository.findByStatusAndPaymentDateBetween(
                        status, startDate, endDate);
            } else {
                return paymentRepository.findByPaymentDateBetween(startDate, endDate);
            }
        } else {


            if (debitCode != null && cpfCnpj != null && status != null) {
                return paymentRepository.findByDebitCodeAndCpfCnpjAndStatus(debitCode, cpfCnpj, status);
            } else if (debitCode != null && cpfCnpj != null) {
                return paymentRepository.findByDebitCodeAndCpfCnpj(debitCode, cpfCnpj);
            } else if (debitCode != null && status != null) {
                return paymentRepository.findByDebitCodeAndStatus(debitCode, status);
            } else if (cpfCnpj != null && status != null) {
                return paymentRepository.findByCpfCnpjAndStatus(cpfCnpj, status);
            } else if (debitCode != null) {
                return paymentRepository.findByDebitCode(debitCode);
            } else if (cpfCnpj != null) {
                return paymentRepository.findByCpfCnpj(cpfCnpj);
            } else if (status != null) {
                return paymentRepository.findByStatus(status);
            } else {
                return paymentRepository.findAll();
            }
        }
    }
}