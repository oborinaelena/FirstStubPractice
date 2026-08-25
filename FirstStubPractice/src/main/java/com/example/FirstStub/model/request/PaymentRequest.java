package com.example.FirstStub.model.request;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentRequest {

    @JsonProperty("transaction_id")
    private String transactionId;

    private double sum;

    @JsonProperty("need_processing")
    private boolean needProcessing;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public boolean isNeedProcessing() {
        return needProcessing;
    }

    public void setNeedProcessing(boolean needProcessing) {
        this.needProcessing = needProcessing;
    }
}