package com.witSoftware;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculatorKafkaListener {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final CalculatorOperations calculatorOperations;

    @Autowired
    public CalculatorKafkaListener(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.calculatorOperations = new CalculatorOperations();
    }

    @KafkaListener(topics = "calc-requests", groupId = "calculator-group")
    public void handleRequest(ConsumerRecord<String, String> record) {
        System.out.println("Received message on calc-requests: " + record.value());
        String message = record.value();
        try {
            // Parse the incoming message (e.g., "req-1,sum,5,10")
            String[] parts = message.split(",", 4);
            if (parts.length != 4) {
                throw new IllegalArgumentException("Invalid message format. Expected: requestId,operation,a,b");
            }

            String requestId = parts[0];
            String operation = parts[1];
            BigDecimal a = new BigDecimal(parts[2]);
            BigDecimal b = new BigDecimal(parts[3]);

            // Perform the operation
            BigDecimal result = performOperation(operation, a, b);

            // Publish the result back to Kafka
            String responseMessage = requestId + "," + result.toString();
            kafkaTemplate.send("calc-results", responseMessage);
        } catch (Exception e) {
            // Publish an error message to Kafka
            String errorMessage = "Error: " + e.getMessage();
            kafkaTemplate.send("calc-results", errorMessage);
        }
    }

    private BigDecimal performOperation(String operation, BigDecimal a, BigDecimal b) {
        return switch (operation) {
            case "sum" -> calculatorOperations.sum(a, b);
            case "subtract" -> calculatorOperations.subtract(a, b);
            case "multiply" -> calculatorOperations.multiply(a, b);
            case "divide" -> calculatorOperations.divide(a, b);
            default -> throw new IllegalArgumentException("Unsupported operation: " + operation);
        };
    }
}
