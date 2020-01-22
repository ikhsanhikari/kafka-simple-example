package com.example.demokafkaconsumer.config;

import com.example.demokafkaconsumer.service.ListenerService;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.MessageListener;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    @Autowired
    private ListenerService listenerService;

    @Bean
    public ConsumerFactory<String,String> consumerFactory(){

        Map<String,Object> prop = new HashMap<>();
        prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"10.0.20.12:9092");
        prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
        prop.put(ConsumerConfig.GROUP_ID_CONFIG,"bebas-sih");
        prop.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        return new DefaultKafkaConsumerFactory<>(prop);
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String,String>> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String,String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public ConcurrentMessageListenerContainer concurrentMessageListenerContainer(){

        ContainerProperties containerProperties  =new ContainerProperties("test22222");
        containerProperties.setMessageListener((MessageListener<String,String>) record -> listenerService.listen(record.value()));

        ConcurrentMessageListenerContainer container = new ConcurrentMessageListenerContainer<>(consumerFactory(),containerProperties);

        container.start();

        return container;
    }
}
