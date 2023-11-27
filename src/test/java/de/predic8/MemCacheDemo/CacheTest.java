package de.predic8.MemCacheDemo;

import net.rubyeye.xmemcached.exception.MemcachedException;
import org.junit.jupiter.api.Test;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeoutException;

import static java.net.InetAddress.getLoopbackAddress;
import static org.junit.jupiter.api.Assertions.*;

class CacheTest extends MemcachedTest {
    @Test
    void cacheResourceTest() throws InterruptedException, TimeoutException, MemcachedException {
        String resourceName = cache.submitRandomString();
        assertFalse(cache.isCached(resourceName));
        cache.getResource(resourceName);
        assertTrue(cache.isCached(resourceName));
    }

    @Test
    void cacheExpiryTest() throws InterruptedException, TimeoutException, MemcachedException {
        String resourceName = cache.submitRandomString();
        assertFalse(cache.isCached(resourceName));
        cache.getResource(resourceName, 10);
        assertTrue(cache.isCached(resourceName));
        Thread.sleep(10000);
        assertFalse(cache.isCached(resourceName));
    }

    @Test
    void multiNodeTest() throws InterruptedException, TimeoutException, MemcachedException {
        for (int i = 100; i > 0; i--) {
            cache.getResource(cache.submitRandomString());
        }
        double v = getBytesRead(11211) / getBytesRead(11212);
        System.out.println("v = " + v);
        assertTrue(0.5 < v && v < 1.5);
    }

    private static double getBytesRead(int port) throws MemcachedException, InterruptedException, TimeoutException {
        return Integer.parseInt(cache.getMemcache().stats(new InetSocketAddress(getLoopbackAddress(), port)).get("bytes_read"));
    }
}