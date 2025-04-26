package com.RafaelMSantos19.apiTransaction.model;

public enum PaymentStatus {
    PROCESSING_PENDING("Pendente de Processamento"),
    PROCESSED("Processado com Sucesso"), 
    FAILED("Processado com Falha");

    private final String description;

    PaymentStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return this.name();
    }
}