package com.RafaelMSantos19.apiTransaction.controller;

import com.RafaelMSantos19.apiTransaction.model.PaymentStatus;
import com.RafaelMSantos19.apiTransaction.dto.PaymentRequestDTO;
import com.RafaelMSantos19.apiTransaction.model.PaymentPostModel;
import com.RafaelMSantos19.apiTransaction.service.PaymentGetService;
import com.RafaelMSantos19.apiTransaction.service.PaymentPostService;
import com.RafaelMSantos19.apiTransaction.service.PaymentPutService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/payment")
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
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getPayment() {
        try {
            Map<String, Object> response = paymentGetService.service();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> processPayment(
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
            return ResponseEntity.ok(response);

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