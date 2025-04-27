package com.RafaelMSantos19.apiTransaction.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RafaelMSantos19.apiTransaction.model.PaymentPostModel;
import com.RafaelMSantos19.apiTransaction.model.PaymentStatus;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentPostModel, Long> {
    
    List<PaymentPostModel> findByStatusNot(PaymentStatus status);
    
    List<PaymentPostModel> findByDebitCodeAndStatusNot(Integer debitCode, PaymentStatus excludedStatus);
    List<PaymentPostModel> findByCpfCnpjAndStatusNot(String cpfCnpj, PaymentStatus excludedStatus);
    List<PaymentPostModel> findByStatusAndStatusNot(PaymentStatus status, PaymentStatus excludedStatus);
    
    List<PaymentPostModel> findByDebitCodeAndCpfCnpjAndStatusNot(Integer debitCode, String cpfCnpj, PaymentStatus excludedStatus);
    List<PaymentPostModel> findByDebitCodeAndStatusAndStatusNot(Integer debitCode, PaymentStatus status, PaymentStatus excludedStatus);
    List<PaymentPostModel> findByCpfCnpjAndStatusAndStatusNot(String cpfCnpj, PaymentStatus status, PaymentStatus excludedStatus);
    List<PaymentPostModel> findByDebitCodeAndCpfCnpjAndStatusAndStatusNot(Integer debitCode, String cpfCnpj, PaymentStatus status, PaymentStatus excludedStatus);
    
    List<PaymentPostModel> findByPaymentDateBetweenAndStatusNot(LocalDateTime startDate, LocalDateTime endDate, PaymentStatus excludedStatus);
    
    List<PaymentPostModel> findByDebitCodeAndPaymentDateBetweenAndStatusNot(
            Integer debitCode, LocalDateTime startDate, LocalDateTime endDate, PaymentStatus excludedStatus);
    
    List<PaymentPostModel> findByCpfCnpjAndPaymentDateBetweenAndStatusNot(
            String cpfCnpj, LocalDateTime startDate, LocalDateTime endDate, PaymentStatus excludedStatus);
    
    List<PaymentPostModel> findByStatusAndPaymentDateBetweenAndStatusNot(
            PaymentStatus status, LocalDateTime startDate, LocalDateTime endDate, PaymentStatus excludedStatus);
    
    List<PaymentPostModel> findByDebitCodeAndCpfCnpjAndPaymentDateBetweenAndStatusNot(
            Integer debitCode, String cpfCnpj, LocalDateTime startDate, LocalDateTime endDate, PaymentStatus excludedStatus);
    
    List<PaymentPostModel> findByDebitCodeAndStatusAndPaymentDateBetweenAndStatusNot(
            Integer debitCode, PaymentStatus status, LocalDateTime startDate, LocalDateTime endDate, PaymentStatus excludedStatus);
    
    List<PaymentPostModel> findByCpfCnpjAndStatusAndPaymentDateBetweenAndStatusNot(
            String cpfCnpj, PaymentStatus status, LocalDateTime startDate, LocalDateTime endDate, PaymentStatus excludedStatus);
    
    List<PaymentPostModel> findByDebitCodeAndCpfCnpjAndStatusAndPaymentDateBetweenAndStatusNot(
            Integer debitCode, String cpfCnpj, PaymentStatus status, LocalDateTime startDate, LocalDateTime endDate, PaymentStatus excludedStatus);
}