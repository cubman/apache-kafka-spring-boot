package ru.dstu.controller;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dstu.entity.Consumer;
import ru.dstu.entity.MessageHistory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    @Autowired
    private ApplicationContext applicationContext;

    Map<Long, Consumer> consumers = new HashMap<>();
    ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(10);

    @GetMapping("/add")
    public String addConsumer() {


        ConsumerThread consumerThread = applicationContext.getBean(ConsumerThread.class);
        Consumer consumer = consumerThread.getConsumer();

        threadPoolExecutor.submit(consumerThread);
        consumers.put(consumer.getId(), consumer);

        return "consumer added " + consumer;
    }

    @GetMapping("")
    public List<Pair<Long, MessageHistory>> getConsumers() {
        return consumers.entrySet().stream()
                .map(longConsumerEntry -> longConsumerEntry.getValue().getData())
                .collect(Collectors.toList());
    }
}
