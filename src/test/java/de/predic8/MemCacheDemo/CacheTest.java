package de.predic8.MemCacheDemo;

import net.rubyeye.xmemcached.exception.MemcachedException;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeoutException;

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
}