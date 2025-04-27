package com.RafaelMSantos19.apiTransaction.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.RafaelMSantos19.apiTransaction.dto.PaymentRequestDTO;
import com.RafaelMSantos19.apiTransaction.model.PaymentPostModel;
import com.RafaelMSantos19.apiTransaction.service.PaymentDeleteService;
import com.RafaelMSantos19.apiTransaction.service.PaymentGetService;
import com.RafaelMSantos19.apiTransaction.service.PaymentPostService;
import com.RafaelMSantos19.apiTransaction.service.PaymentPutService;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentGetService paymentGetService;
    private final PaymentPostService paymentPostService;
    private final PaymentPutService paymentPutService;
    private final PaymentDeleteService paymentDeleteService;
    private final ObjectMapper objectMapper;

    public PaymentController(
            PaymentGetService paymentGetService,
            PaymentPostService paymentPostService,
            PaymentPutService paymentPutService,
            PaymentDeleteService paymentDeleteService,
            ObjectMapper objectMapper
    ) {
        this.paymentGetService = paymentGetService;
        this.paymentPostService = paymentPostService;
        this.paymentPutService = paymentPutService;
        this.paymentDeleteService = paymentDeleteService;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deletePayment(@PathVariable Long id) {
        Map<String, String> response = paymentDeleteService.deletePayment(id);
        return ResponseEntity.ok(response);
    }
}