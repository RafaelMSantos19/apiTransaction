package com.RafaelMSantos19.apiTransaction.model;

public class PaymentPostModel {
    
    private int debitCode;
    private String CpfCnpj;
    private String paymentMethod;
    private String card;
    private double value;

    // Getters e Setters
    public int getDebitCode() {
        return debitCode;
    }

    public void setDebitCode(int debitCode) {
        this.debitCode = debitCode;
    }

    public String getCpfCnpj() {
        return CpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.CpfCnpj = cpfCnpj;
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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
