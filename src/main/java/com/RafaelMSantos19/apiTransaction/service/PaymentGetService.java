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
                return paymentRepository.findByDebitCodeAndCpfCnpjAndStatusAndPaymentDateBetweenAndStatusNot(
                        debitCode, cpfCnpj, status, startDate, endDate, PaymentStatus.INATIVO);
            } else if (debitCode != null && cpfCnpj != null) {
                return paymentRepository.findByDebitCodeAndCpfCnpjAndPaymentDateBetweenAndStatusNot(
                        debitCode, cpfCnpj, startDate, endDate, PaymentStatus.INATIVO);
            } else if (debitCode != null && status != null) {
                return paymentRepository.findByDebitCodeAndStatusAndPaymentDateBetweenAndStatusNot(
                        debitCode, status, startDate, endDate, PaymentStatus.INATIVO);
            } else if (cpfCnpj != null && status != null) {
                return paymentRepository.findByCpfCnpjAndStatusAndPaymentDateBetweenAndStatusNot(
                        cpfCnpj, status, startDate, endDate, PaymentStatus.INATIVO);
            } else if (debitCode != null) {
                return paymentRepository.findByDebitCodeAndPaymentDateBetweenAndStatusNot(
                        debitCode, startDate, endDate, PaymentStatus.INATIVO);
            } else if (cpfCnpj != null) {
                return paymentRepository.findByCpfCnpjAndPaymentDateBetweenAndStatusNot(
                        cpfCnpj, startDate, endDate, PaymentStatus.INATIVO);
            } else if (status != null) {
                return paymentRepository.findByStatusAndPaymentDateBetweenAndStatusNot(
                        status, startDate, endDate, PaymentStatus.INATIVO);
            } else {
                return paymentRepository.findByPaymentDateBetweenAndStatusNot(startDate, endDate, PaymentStatus.INATIVO);
            }
        } else {


            if (debitCode != null && cpfCnpj != null && status != null) {
                return paymentRepository.findByDebitCodeAndCpfCnpjAndStatusAndStatusNot(debitCode, cpfCnpj, status, PaymentStatus.INATIVO);
            } else if (debitCode != null && cpfCnpj != null) {
                return paymentRepository.findByDebitCodeAndCpfCnpjAndStatusNot(debitCode, cpfCnpj, PaymentStatus.INATIVO);
            } else if (debitCode != null && status != null) {
                return paymentRepository.findByDebitCodeAndStatusAndStatusNot(debitCode, status, PaymentStatus.INATIVO);
            } else if (cpfCnpj != null && status != null) {
                return paymentRepository.findByCpfCnpjAndStatusAndStatusNot(cpfCnpj, status, PaymentStatus.INATIVO);
            } else if (debitCode != null) {
                return paymentRepository.findByDebitCodeAndStatusNot(debitCode, PaymentStatus.INATIVO);
            } else if (cpfCnpj != null) {
                return paymentRepository.findByCpfCnpjAndStatusNot(cpfCnpj, PaymentStatus.INATIVO);
            } else if (status != null) {
                return paymentRepository.findByStatusAndStatusNot(status, PaymentStatus.INATIVO);
            } else {
                return paymentRepository.findByStatusNot(PaymentStatus.INATIVO);
            }
        }
    }
}