package com.example.demokafkaconsumer.service;

import com.example.demokafkaconsumer.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ListenerService {

    public void listen(String pesan){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.setDateFormat("dd-MM-yyyy'T'HH:mm:ss")
                .setPrettyPrinting()
                .create();
        Map<String, Object> data = gson.fromJson(pesan, new TypeToken<Map<String, Object>>() {}.getType());
        if(data.containsKey("data")){
            if(data.get("data")!=null){
                String userString = gson.toJson(data.get("data"));
                User[] user = gson.fromJson(userString,User[].class);
                List<User> users = convertArrayToList(user);
                System.out.println("Data User : "+users);
            }
        }

    }

    public static <T> List<T> convertArrayToList(T array[])
    {

        // Create an empty List
        List<T> list = new ArrayList<>();

        // Iterate through the array
        for (T t : array) {
            // Add each element into the list
            list.add(t);
        }

        // Return the converted List
        return list;
    }
}
