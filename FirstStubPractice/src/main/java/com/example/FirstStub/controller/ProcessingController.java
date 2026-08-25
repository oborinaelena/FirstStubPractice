package com.example.FirstStub.controller;

import com.example.FirstStub.model.request.PaymentRequest;
import com.example.FirstStub.model.response.CheckAccountResponse;
import com.example.FirstStub.model.response.PaymentResponse;
import com.example.FirstStub.service.ProcessingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Tag(
        name = "Processing API",
        description = "API системы процессинга"
)
public class ProcessingController {

    private final ProcessingService service;

    public ProcessingController(ProcessingService service) {
        this.service = service;
    }


    @Operation(
            summary = "Проверка задолженностей по счету"
    )
    @GetMapping("/v2/checkAccount")
    public ResponseEntity<CheckAccountResponse> checkAccount(
            @RequestParam String acc,
            @RequestParam int days) {

        log.info(
                "GET /v2/checkAccount acc={}, days={}",
                acc,
                days
        );

        CheckAccountResponse response =
                service.checkAccount(acc, days);

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(response);
    }


    @Operation(
            summary = "Подтверждение платежа"
    )
    @PostMapping("/v2/payment/")
    public ResponseEntity<PaymentResponse> payment(
            @RequestHeader("BankCode") String bankCode,
            @RequestBody PaymentRequest request) {

        log.info(
                "POST /v2/payment/ transactionId={}, BankCode={}",
                request.getTransactionId(),
                bankCode
        );

        PaymentResponse response =
                service.payment(request, bankCode);

        String processingTime =
                service.getProcessingTime();

        HttpHeaders headers = new HttpHeaders();

        headers.set(
                "ProcessingTIme",
                processingTime
        );

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(response);
    }


    @Operation(
            summary = "Удаление транзакции процессинга"
    )
    @DeleteMapping("/v1/transactions/cleare/{id}")
    public ResponseEntity<String> clearTransaction(
            @PathVariable String id)
            throws InterruptedException {

        log.info(
                "DELETE /v1/transactions/cleare/{}",
                id
        );

        String response =
                service.clearTransaction();

        return ResponseEntity
                .status(100)
                .body(response);
    }
}