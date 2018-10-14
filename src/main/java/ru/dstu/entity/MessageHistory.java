package ru.dstu.entity;

import java.util.LinkedList;
import java.util.List;

public class MessageHistory {
    private List<String> history = new LinkedList<>();

    public synchronized void addMessage(String message) {
        history.add(message);
    }

    public synchronized List<String> getHistory() {
        return history;
    }
}
