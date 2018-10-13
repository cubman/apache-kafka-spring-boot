package ru.dstu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.dstu.entity.MessageHistory;

import java.util.List;

@Controller
@RequestMapping("/producer")
public class ProducerController {

    @Autowired
    private MessageHistory producerMessageHistory;

    @PostMapping("/")
    public ModelAndView producer(@RequestParam("message") String message) {
        ModelAndView modelAndView = new ModelAndView("kafka/producer");
        modelAndView.addObject("message", message);
        producerMessageHistory.addMessage(message);
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
