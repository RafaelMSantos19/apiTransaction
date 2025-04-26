package com.RafaelMSantos19.apiTransaction.model;

import java.util.Arrays;
import java.util.stream.Collectors;

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

    public static String[] getValidDescriptions() {
        return Arrays.stream(values())
                   .map(PaymentStatus::getDescription)
                   .toArray(String[]::new);
    }
}