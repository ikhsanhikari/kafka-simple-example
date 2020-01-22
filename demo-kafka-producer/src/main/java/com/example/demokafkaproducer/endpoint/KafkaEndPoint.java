package com.example.demokafkaproducer.endpoint;

import com.example.demokafkaproducer.model.CustomResponse;
import com.example.demokafkaproducer.service.KafkaProducerService;
import com.example.demokafkaproducer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaEndPoint {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private UserService userService;

    @GetMapping("/produce")
    public String produce(){
        CustomResponse response = new CustomResponse();
        try {

            response.setAction("Update");
            response.setModel("User");
            response.setData(userService.getUser());

            kafkaProducerService.sendMessage("test22222",response);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Berhasil mengirim data !! isi data = ["+response+"]";
    }
}
