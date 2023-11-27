package de.predic8.MemCacheDemo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import java.io.IOException;

abstract class MemcachedTest {
    private static Process memcachedProcess;
    private static Process memcachedProcess2;
    public static Cache cache;

    @BeforeAll
    public static void startMemcached() throws IOException {
        memcachedProcess = new ProcessBuilder("memcached", "-p", "11211", "-vv").start();
        memcachedProcess2 = new ProcessBuilder("memcached", "-p", "11212", "-vv").start();
        cache = new Cache();
    }

    @AfterAll
    public static void stopMemcached() {
        if (memcachedProcess != null) {
            memcachedProcess.destroy();
        }
        if (memcachedProcess2 != null) {
            memcachedProcess2.destroy();
        }
    }
}
