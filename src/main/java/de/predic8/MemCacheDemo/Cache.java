package de.predic8.MemCacheDemo;

import net.rubyeye.xmemcached.XMemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import java.io.IOException;
import java.util.Base64;
import java.util.Random;
import java.util.concurrent.TimeoutException;

public class Cache {
    private XMemcachedClient memcache;
    private Store store;

    public Cache() throws IOException {
        memcache = new XMemcachedClient();
        store = new Store();

        memcache.addServer("127.0.0.1", 11211);
        memcache.addServer("127.0.0.1", 11212);
    }

    public String submitRandomString() {
        String name = randStrOfLen(8);
        store.push(name, randStrOfLen(512));
        return name;
    }

    public String getResource(String name) throws InterruptedException, TimeoutException, MemcachedException {
        return getResource(name, 3600);
    }

    public String getResource(String name, int expiry) throws InterruptedException, TimeoutException, MemcachedException {
        String resource = memcache.get(name);
        if (resource == null) {
            String storeResource = store.fetch(name);
            memcache.add(name, expiry, storeResource);
            return storeResource;
        } else {
            return resource;
        }
    }

    public boolean isCached(String name) throws InterruptedException, TimeoutException, MemcachedException {
        return memcache.get(name) != null;
    }

    private String randStrOfLen(int length) {
        byte[] randomBytes = new byte[length];
        new Random().nextBytes(randomBytes);

        String base64String = Base64.getEncoder().encodeToString(randomBytes);
        return base64String.substring(0, length);
    }

    public XMemcachedClient getMemcache() {
        return memcache;
    }
}
