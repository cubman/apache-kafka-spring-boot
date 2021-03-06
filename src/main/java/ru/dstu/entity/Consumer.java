package ru.dstu.entity;

import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Consumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    @Override
    public String toString() {
        return "Consumer{" +
                "messageHistory=" + messageHistory +
                ", id=" + id +
                '}';
    }

    public Long getId() {
        return id;
    }

    @Autowired
    MessageHistory messageHistory;
    Long id;
    Random random = new Random();

    public Consumer() {
        id = random.nextLong();
    }

    public Pair<Long, MessageHistory> getData() {
        return new Pair(id, messageHistory);
    }

    public void addMessage(String message) {
        LOGGER.info("Recieved message {}", message);

        messageHistory.addMessage(message);
    }
}
