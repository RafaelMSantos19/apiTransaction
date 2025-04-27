package com.RafaelMSantos19.apiTransaction.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.RafaelMSantos19.apiTransaction.model.PaymentPostModel;
import com.RafaelMSantos19.apiTransaction.model.PaymentStatus;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentPostModel, Long> {

    @Query("SELECT p FROM PaymentPostModel p WHERE " +
            "(p.debitCode = :debitCode OR :debitCode IS NULL) AND " +
            "(p.cpfCnpj = :cpfCnpj OR :cpfCnpj IS NULL) AND " +
            "(p.status = :status OR :status IS NULL) AND " +
            "(p.paymentDate BETWEEN :startDate AND :endDate OR (:startDate IS NULL AND :endDate IS NULL) OR (:startDate IS NULL AND p.paymentDate <= :endDate) OR (:endDate IS NULL AND p.paymentDate >= :startDate)) AND " +
            "p.status != :excludedStatus")
    List<PaymentPostModel> findPayments(
            @Param("debitCode") Integer debitCode,
            @Param("cpfCnpj") String cpfCnpj,
            @Param("status") PaymentStatus status,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("excludedStatus") PaymentStatus excludedStatus);
}