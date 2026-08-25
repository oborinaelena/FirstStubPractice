package com.example.FirstStub.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CheckAccountResponse {

    private String account;

    @JsonProperty("vip-client")
    private boolean vipClient;

    private boolean blocked;

    private String inn;

    private List<Debt> debt;

    public CheckAccountResponse(
            String account,
            boolean vipClient,
            boolean blocked,
            String inn,
            List<Debt> debt) {

        this.account = account;
        this.vipClient = vipClient;
        this.blocked = blocked;
        this.inn = inn;
        this.debt = debt;
    }

    public String getAccount() {
        return account;
    }

    public boolean isVipClient() {
        return vipClient;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public String getInn() {
        return inn;
    }

    public List<Debt> getDebt() {
        return debt;
    }
}