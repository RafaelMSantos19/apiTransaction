package com.RafaelMSantos19.apiTransaction.dto;

import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public class PaymentRequestDTO {
    
    @NotNull(message = "Debit code é obrigatório")
    @Min(value = 1, message = "Debit code deve ser maior que 0")
    private Integer debitCode;
    
    @NotBlank(message = "CPF/CNPJ é obrigatório")
    @Size(min = 11, max = 14, message = "CPF/CNPJ deve ter entre 11 e 14 caracteres")
    private String cpfCnpj;
    
    @NotBlank(message = "Método de pagamento é obrigatório")
    private String paymentMethod;
    
    @Size(min = 13, max = 19, message = "Cartão deve ter entre 13 e 19 dígitos")
    private String card;
    
    @NotNull(message = "Valor é obrigatório")
    @Positive(message = "Valor deve ser positivo")
    private Double value;


    public Integer getDebitCode() {
        return debitCode;
    }

    public void setDebitCode(Integer debitCode) {
        this.debitCode = debitCode;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "PaymentRequestDTO{" +
                "debitCode=" + debitCode +
                ", cpfCnpj='" + cpfCnpj + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", card='" + card + '\'' +
                ", value=" + value +
                '}';
    }
}