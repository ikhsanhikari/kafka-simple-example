package com.example.demokafkaproducer.model;

import lombok.Data;

@Data
public class CustomResponse {
    private String action;
    private String model;
    private Object data;
}
