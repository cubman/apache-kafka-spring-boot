package ru.dstu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dstu.entity.Consumer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    Map<Long, Consumer> consumers = new HashMap<>();
    ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(10);

    @GetMapping("/add")
    public String addConsumer() {
        Consumer consumer = new Consumer();
        consumers.put(consumer.getId(), consumer);

        ConsumerThread consumerThread = new ConsumerThread();
        consumerThread.setConsumer(consumer);

        threadPoolExecutor.execute(consumerThread);

        return "consumer added " + consumer;
    }

    @GetMapping("")
    public Set<Map.Entry<Long, Consumer>> getConsumers() {
        return consumers.entrySet();
    }
}
