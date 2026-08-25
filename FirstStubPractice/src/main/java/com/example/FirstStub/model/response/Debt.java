package com.example.FirstStub.model.response;

public class Debt {

    private int sum;
    private String description;

    public Debt(int sum, String description) {
        this.sum = sum;
        this.description = description;
    }

    public int getSum() {
        return sum;
    }

    public String getDescription() {
        return description;
    }
}