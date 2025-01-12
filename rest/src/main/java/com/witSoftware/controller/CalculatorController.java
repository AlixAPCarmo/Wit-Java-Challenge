package com.witSoftware.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ConcurrentHashMap<String, CompletableFuture<String>> responseMap = new ConcurrentHashMap<>();
    private final AtomicInteger requestCounter = new AtomicInteger();

    @Autowired
    public CalculatorController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("/{operation}")
    public CompletableFuture<String> calculate(
            @PathVariable String operation,
            @RequestParam String a,
            @RequestParam String b
    ) {
        // Generate a unique request ID
        String requestId = "req-" + requestCounter.incrementAndGet();
        String message = String.format("%s,%s,%s,%s", requestId, operation, a, b);

        // Send the request to Kafka
        kafkaTemplate.send("calc-requests", message);

        // Create a CompletableFuture to track the response
        CompletableFuture<String> future = new CompletableFuture<>();
        responseMap.put(requestId, future);
        return future;
    }

    @KafkaListener(topics = "calc-results", groupId = "rest-group")
    public void handleResponse(String message) {
        System.out.println("Received response on calc-results: " + message);
        // Parse the response (e.g., "req-1,15.00")
        String[] parts = message.split(",", 2);
        String requestId = parts[0];
        String result = parts[1];

        // Complete the corresponding CompletableFuture
        CompletableFuture<String> future = responseMap.remove(requestId);
        if (future != null) {
            future.complete(result);
        }
    }
}
