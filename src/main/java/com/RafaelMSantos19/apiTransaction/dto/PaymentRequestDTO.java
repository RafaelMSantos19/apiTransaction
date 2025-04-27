package com.RafaelMSantos19.apiTransaction.dto;

import com.RafaelMSantos19.apiTransaction.model.PaymentPostModel;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

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

    public PaymentPostModel toPaymentPostModel() {
        PaymentPostModel payment = new PaymentPostModel();
        payment.setDebitCode(this.debitCode);
        payment.setCpfCnpj(this.cpfCnpj);
        payment.setPaymentMethod(this.paymentMethod);
        payment.setCard(this.card);
        payment.setValue(this.value);
        return payment;
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