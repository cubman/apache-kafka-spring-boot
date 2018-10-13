package ru.dstu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dstu.entity.Consumer;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/consumer")
public class ConsumerController {

    List<Consumer> consumers = new ArrayList<>();

    @GetMapping("/add")
    public String addConsumer() {
        Consumer consumer = new Consumer();
        consumers.add(consumer);
        return "consumer add " + consumer;
    }

    @GetMapping("")
    public List<Consumer> getConsumers() {
        return consumers;
    }
}
