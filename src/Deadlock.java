public class Deadlock {
    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();

        Thread thread1 = new Thread(() -> extracted(lock1, lock2));
        Thread thread2 = new Thread(() -> extracted(lock2, lock1));

        thread1.start();
        thread2.start();
    }

    private static void extracted(Object lock1, Object lock2) {
        System.out.println(Thread.currentThread().getName() + " start");
        synchronized (lock1) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (lock2) {
            }
        }
        System.out.println(Thread.currentThread().getName() + " finish");
    }
}
