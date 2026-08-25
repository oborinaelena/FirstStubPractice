package com.example.FirstStub.model.response;

import java.util.List;

public class Contact {

    private String name;
    private List<String> telecom;

    public Contact(String name, List<String> telecom) {
        this.name = name;
        this.telecom = telecom;
    }

    public String getName() {
        return name;
    }

    public List<String> getTelecom() {
        return telecom;
    }
}