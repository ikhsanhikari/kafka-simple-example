package com.example.demokafkaproducer.service;

import com.example.demokafkaproducer.model.CustomResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String , String > kafkaTemplate;

    @Autowired
    private UserService userService;

    public void sendMessage(String topic, CustomResponse response)
            throws InterruptedException {

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson= gsonBuilder.setDateFormat("dd-MM-yyyy'T'HH:mm:ss")
                .serializeNulls()
                .setPrettyPrinting()
                .create();
        String data  = gson.toJson(response);

        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic,data);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("Data gagal terkirim");
            }

            @Override
            public void onSuccess(SendResult<String, String> stringStringSendResult) {
                System.out.println("Data terkirim !! isi pesan ["+data+"]");
            }
        });
    }

}
