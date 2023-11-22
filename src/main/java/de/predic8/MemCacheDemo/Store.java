package de.predic8.MemCacheDemo;

import java.util.HashMap;
import java.util.Map;

public class Store {
    private Map<String, String> data;

    public Store() {
        data = new HashMap<>();
    }

    public void push(String name, String dat) {
        data.put(name, dat);
    }

    public String fetch(String name) {
        return data.get(name);
    }
}
