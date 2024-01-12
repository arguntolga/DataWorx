package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Producer {
    public static void main(String[] args) throws InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");

        KafkaProducer<String, Integer> producer = new KafkaProducer<>(props);
        String topic = "my-topic";

        while (true) {
            String key = "my-key-" + System.currentTimeMillis();
            Integer value = new Random().nextInt();
            ProducerRecord<String, Integer> record = new ProducerRecord<>(topic, key, value);
            producer.send(record);
            System.out.println("Sent message: key=" + key + ", value=" + value);
            TimeUnit.SECONDS.sleep(1);
        }
    }
}

