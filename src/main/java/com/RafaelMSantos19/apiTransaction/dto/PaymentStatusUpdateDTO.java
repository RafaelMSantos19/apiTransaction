package com.RafaelMSantos19.apiTransaction.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PaymentStatusUpdateDTO {
    
    @NotNull(message = "ID do pagamento é obrigatório")
    private Long paymentId;
    
    @NotBlank(message = "Status é obrigatório")
    private String status;

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}