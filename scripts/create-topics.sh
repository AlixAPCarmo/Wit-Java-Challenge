#!/bin/bash

# Wait for Kafka to be ready
echo "Waiting for Kafka to start..."
KAFKA_READY=0
RETRIES=20
while [ $KAFKA_READY -eq 0 ]; do
    if /opt/kafka/bin/kafka-broker-api-versions.sh --bootstrap-server kafka:9092 &>/dev/null; then
        KAFKA_READY=1
        echo "Kafka broker is ready!"
    else
        if [ $RETRIES -le 0 ]; then
            echo "Kafka broker did not become ready. Exiting."
            exit 1
        fi
        echo "Kafka not ready. Retrying in 5 seconds..."
        sleep 5
        ((RETRIES--))
    fi
done

# Create the required topics
echo "Creating Kafka topics..."
/opt/kafka/bin/kafka-topics.sh --create --bootstrap-server kafka:9092 \
    --replication-factor 1 --partitions 1 --topic calc-requests


/opt/kafka/bin/kafka-topics.sh --create --bootstrap-server kafka:9092 \
    --replication-factor 1 --partitions 1 --topic calc-results

echo "Kafka topics created successfully."
