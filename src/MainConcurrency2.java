import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainConcurrency2 {
    public static final int THREADS_NUMBER = 10000;
    private final AtomicInteger atomicInteger = new AtomicInteger();
    private final Lock lock = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {

        final MainConcurrency2 mainConcurrency = new MainConcurrency2();

        CountDownLatch countDownLatch = new CountDownLatch(THREADS_NUMBER);
        ExecutorService service = Executors.newCachedThreadPool();

        for (int i = 0; i < THREADS_NUMBER; i++) {
            service.submit(() -> {
                        for (int j = 0; j < 100; j++) {
                            mainConcurrency.inc();
                        }
                    }
            );
            countDownLatch.countDown();
        }
        service.shutdown();
        countDownLatch.await(10, TimeUnit.SECONDS);
        System.out.println(mainConcurrency.atomicInteger.get());
    }

    private void inc() {
        atomicInteger.incrementAndGet();
    }
}
