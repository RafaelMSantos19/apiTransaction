package com.RafaelMSantos19.apiTransaction.controller;

import com.RafaelMSantos19.apiTransaction.model.PaymentStatus;
import com.RafaelMSantos19.apiTransaction.dto.PaymentRequestDTO;
import com.RafaelMSantos19.apiTransaction.model.PaymentPostModel;
import com.RafaelMSantos19.apiTransaction.service.PaymentGetService;
import com.RafaelMSantos19.apiTransaction.service.PaymentPostService;
import com.RafaelMSantos19.apiTransaction.service.PaymentPutService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentGetService paymentGetService;
    private final PaymentPostService paymentPostService;
    private final PaymentPutService paymentPutService;
    private final ObjectMapper objectMapper;

    public PaymentController(
            PaymentGetService paymentGetService,
            PaymentPostService paymentPostService,
            PaymentPutService paymentPutService,
            ObjectMapper objectMapper
    ) {
        this.paymentGetService = paymentGetService;
        this.paymentPostService = paymentPostService;
        this.paymentPutService = paymentPutService;
        this.objectMapper = objectMapper;
        
        this.objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @GetMapping
    public ResponseEntity<List<PaymentPostModel>> getPayments(
            @RequestParam(required = false) String debitCode,
            @RequestParam(required = false) String cpfCnpj,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {
        
        List<PaymentPostModel> payments = paymentGetService.service(debitCode, cpfCnpj, status, startDate, endDate);
        return ResponseEntity.ok(payments);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createPayment(
            @Valid @RequestBody PaymentRequestDTO paymentRequest) {
        
        try {
            System.out.println("JSON recebido: " + objectMapper.writeValueAsString(paymentRequest));
    
            PaymentPostModel payment = new PaymentPostModel();
            payment.setDebitCode(paymentRequest.getDebitCode());
            payment.setCpfCnpj(paymentRequest.getCpfCnpj());
            payment.setPaymentMethod(paymentRequest.getPaymentMethod());
            payment.setCard(paymentRequest.getCard());
            payment.setValue(paymentRequest.getValue());
    
            Map<String, Object> response = paymentPostService.service(payment);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
    
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(Map.of(
                        "error", "Erro ao processar pagamento",
                        "message", e.getMessage()
                    ));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updatePaymentStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        
        Map<String, Object> response = paymentPutService.updatePaymentStatus(id, status);
        boolean success = (boolean) response.get("success");
        
        if (success) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().body(response);
        }
    }
}