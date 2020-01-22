package com.example.demokafkaproducer.service;

import com.example.demokafkaproducer.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    public List<User> getUser(){
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(i);
            user.setName("User "+i);
            user.setRole("Admin");
            userList.add(user);
        }
        return userList;
    }
}
