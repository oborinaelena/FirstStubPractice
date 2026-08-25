package com.example.FirstStub.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PaymentResponse {

    @JsonProperty("transaction_id")
    private String transactionId;

    @JsonProperty("bank_bik")
    private String bankBik;

    private String status;

    private List<Contact> contact;

    public PaymentResponse(
            String transactionId,
            String bankBik,
            String status,
            List<Contact> contact) {

        this.transactionId = transactionId;
        this.bankBik = bankBik;
        this.status = status;
        this.contact = contact;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getBankBik() {
        return bankBik;
    }

    public String getStatus() {
        return status;
    }

    public List<Contact> getContact() {
        return contact;
    }
}