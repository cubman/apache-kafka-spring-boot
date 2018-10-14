package ru.dstu.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.dstu.entity.Consumer;

import java.util.Collections;
import java.util.Properties;

@Component
public class ConsumerThread implements Runnable {

    private static Logger LOGGER = LoggerFactory.getLogger(ConsumerThread.class);

    private Consumer consumer;

    @Autowired
   // @Qualifier("consumerProp")
    private Properties consumerProperties;
    @Value("message.topic.name")
    private String topics;

    public void setConsumer(Consumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public void run() {
        LOGGER.info("{} is executing", consumer.getId());

        try(KafkaConsumer kafkaConsumer = new KafkaConsumer(consumerProperties)){
            kafkaConsumer.subscribe(Collections.singletonList(topics));
            while (true){
                ConsumerRecords<String, String> records = kafkaConsumer.poll(10);
                for (ConsumerRecord<String, String> record: records){
                    LOGGER.info("Topic - {}, Partition - {}, Value: {}", record.topic(), record.partition(), record.value());
                    consumer.addMessage(record.value());
                }
            }
        } catch (Exception e){
            LOGGER.info(e.getMessage());
        }
    }
}
