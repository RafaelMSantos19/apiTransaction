package com.RafaelMSantos19.apiTransaction.repository;

import com.RafaelMSantos19.apiTransaction.model.PaymentPostModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentPostModel, Long> {
}