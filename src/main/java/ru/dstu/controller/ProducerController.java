package ru.dstu.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.dstu.entity.MessageHistory;

import java.util.List;

@Controller
@RequestMapping("/producer")
public class ProducerController {

    private static final Logger LOGGER = LoggerFactory.getLogger("KafkaApp");

    @Value("${message.topic.name}")
    private String topicName;

    @Autowired
    private MessageHistory producerMessageHistory;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/")
    public ModelAndView producer(@RequestParam("message") String message) {
        ModelAndView modelAndView = new ModelAndView("kafka/producer");
        modelAndView.addObject("message", message);

        producerMessageHistory.addMessage(message);
        kafkaTemplate.send(topicName, message);

        LOGGER.info("Sended", message);

        return modelAndView;
    }
    @RequestMapping("history")
    @ResponseBody
    List<String> getHistory() {
        return producerMessageHistory.getHistory();
    }

    @GetMapping("/")
    public String producer() {
        return "kafka/producer";
    }

}
