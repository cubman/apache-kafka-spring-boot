package ru.dstu.entity;

import java.util.LinkedList;
import java.util.List;

public class MessageHistory {
    private List<String> history = new LinkedList<>();

    public void addMessage(String message) {
        history.add(message);
    }

    public List<String> getHistory() {
        return history;
    }
}
