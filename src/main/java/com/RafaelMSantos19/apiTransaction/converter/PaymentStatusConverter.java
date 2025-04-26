package com.RafaelMSantos19.apiTransaction.converter;

import com.RafaelMSantos19.apiTransaction.model.PaymentStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Arrays;

@Converter(autoApply = true)
public class PaymentStatusConverter implements AttributeConverter<PaymentStatus, String> {

    @Override
    public String convertToDatabaseColumn(PaymentStatus status) {
        if (status == null) {
            return null;
        }
        return status.getDescription(); 
    }

    @Override
    public PaymentStatus convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
       
        return Arrays.stream(PaymentStatus.values())
                   .filter(s -> s.getDescription().equals(dbData))
                   .findFirst()
                   .orElseThrow(() -> new IllegalArgumentException("Status inválido: " + dbData));
    }
}