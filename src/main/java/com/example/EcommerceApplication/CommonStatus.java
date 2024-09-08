package com.example.EcommerceApplication;

public enum CommonStatus {
    SUCCESS("SUCCESS"),
    FAIL("FAIL");

    private final String status;

    CommonStatus(String status) {
        this.status = status;
    }

    public String getDisplayName() {
        return status;
    }
}
