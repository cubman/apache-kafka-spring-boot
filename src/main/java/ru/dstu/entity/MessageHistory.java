package ru.dstu.entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class MessageHistory implements Serializable {
    private List<String> history = new LinkedList<>();

    public void addMessage(String message) {
        history.add(message);
    }

    public List<String> getHistory() {
        return history;
    }
}
