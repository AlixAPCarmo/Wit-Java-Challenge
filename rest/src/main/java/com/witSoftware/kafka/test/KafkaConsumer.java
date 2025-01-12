package com.witSoftware.kafka.test;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "test-topic", groupId = "rest-group")
    public void listen(String message) {
        System.out.println("Received message: " + message);
    }
}
