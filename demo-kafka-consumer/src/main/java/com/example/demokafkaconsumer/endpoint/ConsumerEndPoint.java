package com.example.demokafkaconsumer.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerEndPoint {
    @GetMapping("consume")
    public String consume(){
        return "Hikari";
    }
}
