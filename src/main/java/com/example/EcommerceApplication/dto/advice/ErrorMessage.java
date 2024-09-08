package com.example.EcommerceApplication.dto.advice;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorMessage {
    private final Integer code;
    private final String message;
    private final Date timestamp;
}
