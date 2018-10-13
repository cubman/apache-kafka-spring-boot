package ru.dstu.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import java.io.Serializable;
import java.util.List;
import java.util.Random;


public class Consumer implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    @Override
    public String toString() {
        return "Consumer{" +
                "messageHistory=" + messageHistory +
                ", id=" + id +
                '}';
    }

    class Pair {
        Long id;
        MessageHistory messageHistory;

        public Pair(Long id, MessageHistory messageHistory) {
            this.id = id;
            this.messageHistory = messageHistory;
        }
    }

    @Autowired
    MessageHistory messageHistory;
    Long id;
    Random random = new Random();

    public Consumer() {
        id = random.nextLong();
    }

    Pair getData() {
        return new Pair(id, messageHistory);
    }

    @KafkaListener(topics = "DSTU", groupId = "dstu-group")
    public void listen(String message) {
        LOGGER.info("Recieved message", message);

        messageHistory.addMessage(message);
    }
}
