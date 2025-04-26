package com.RafaelMSantos19.apiTransaction.repository;

import com.RafaelMSantos19.apiTransaction.model.PaymentPostModel;
import com.RafaelMSantos19.apiTransaction.model.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentPostModel, Long> {
    
    List<PaymentPostModel> findByDebitCode(Integer debitCode);
    List<PaymentPostModel> findByCpfCnpj(String cpfCnpj);
    List<PaymentPostModel> findByStatus(PaymentStatus status);
    List<PaymentPostModel> findByDebitCodeAndCpfCnpj(Integer debitCode, String cpfCnpj);
    List<PaymentPostModel> findByDebitCodeAndStatus(Integer debitCode, PaymentStatus status);
    List<PaymentPostModel> findByCpfCnpjAndStatus(String cpfCnpj, PaymentStatus status);
    List<PaymentPostModel> findByDebitCodeAndCpfCnpjAndStatus(Integer debitCode, String cpfCnpj, PaymentStatus status);
    
    List<PaymentPostModel> findByPaymentDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    List<PaymentPostModel> findByDebitCodeAndPaymentDateBetween(
            Integer debitCode, LocalDateTime startDate, LocalDateTime endDate);
    
    List<PaymentPostModel> findByCpfCnpjAndPaymentDateBetween(
            String cpfCnpj, LocalDateTime startDate, LocalDateTime endDate);
    
    List<PaymentPostModel> findByStatusAndPaymentDateBetween(
            PaymentStatus status, LocalDateTime startDate, LocalDateTime endDate);
    
    List<PaymentPostModel> findByDebitCodeAndCpfCnpjAndPaymentDateBetween(
            Integer debitCode, String cpfCnpj, LocalDateTime startDate, LocalDateTime endDate);
    
    List<PaymentPostModel> findByDebitCodeAndStatusAndPaymentDateBetween(
            Integer debitCode, PaymentStatus status, LocalDateTime startDate, LocalDateTime endDate);
    
    List<PaymentPostModel> findByCpfCnpjAndStatusAndPaymentDateBetween(
            String cpfCnpj, PaymentStatus status, LocalDateTime startDate, LocalDateTime endDate);
    
    List<PaymentPostModel> findByDebitCodeAndCpfCnpjAndStatusAndPaymentDateBetween(
            Integer debitCode, String cpfCnpj, PaymentStatus status, LocalDateTime startDate, LocalDateTime endDate);
}