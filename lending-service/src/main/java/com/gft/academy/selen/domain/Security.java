package com.gft.academy.selen.domain;

public class Security {

    private String id;
    private Integer availableQuantity;

    public Security() {
    }

    public Security(String id, Integer availableQuantity) {
        this.id = id;
        this.availableQuantity = availableQuantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }
}
