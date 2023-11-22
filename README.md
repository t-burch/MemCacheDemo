# Why Xmemcached as Memcached Library?

| Feature                       | "whalin" Memcached for Java        | Spymemcached                                 | Xmemcached                                                 |
|-------------------------------|------------------------------------|----------------------------------------------|------------------------------------------------------------|
| **Based On**                  | Blocking IO                        | Java NIO                                     | Java NIO                                                   |
| **License**                   | GPL 3.0                            | Apache 2.0                                   | Apache 2.0                                                 |
| **Latest Version**            | 3.0.2 (l/u 2013)                   | 2.12.3 (l/u 2017)                            | 2.4.7 (l/u 2021)                                           |
| **Stability**                 | Widely used and stable             | Less stable with reports of timeouts         | High performance, carefully tuned for top performance      |
| **Support for CAS Operations** | No                                 | Yes                                          | Yes                                                        |
| **Performance**               | Good performance with blocking IO  | Higher access rate but with stability issues | Extremely high performance, especially in high concurrency |
| **Resource Consumption**      | Higher due to traditional IO model | Lower due to NIO model                       | Lower, optimized for less resource consumption             |
| **Concurrency Handling**      | Not specified                      | Single-threaded approach                     | Multithreaded, optimized for high concurrency              |
| **Comment**                   | Deprecated/Legacy                  | Has connection issues + legacy               | Semi-actively developed + Spring integration               |