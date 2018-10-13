package ru.dstu.entity;

import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class Message  implements Serializer {
    @Override
    public void configure(Map map, boolean b) {

    }

    @Override
    public byte[] serialize(String s, Object o) {
        return new byte[0];
    }

    @Override
    public void close() {

    }
}
