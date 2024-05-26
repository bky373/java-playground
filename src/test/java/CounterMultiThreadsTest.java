import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Test;

public class CounterMultiThreadsTest {

    @Test
    void testWithoutSync() throws InterruptedException {
        Counter counter = new Counter();

        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10000; i++) {
            executor.submit(counter::increment);
        }
        executor.shutdown();
        Thread.sleep(500);
        System.out.println("counter = " + counter.count);
    }

    @Test
    void testWithSync() throws InterruptedException {
        Counter counter = new Counter();

        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10000; i++) {
            executor.submit(counter::syncIncrement);
        }
        executor.shutdown();
        Thread.sleep(500);
        System.out.println("counter = " + counter.count);
    }

    class Counter {

        private int count;

        public void increment() {
            this.count++;
        }

        public synchronized void syncIncrement() {
//            System.out.println("Counter.syncIncrement() with count " + count + " on [tid: " + org.example.ThreadSupport.getThreadName() + "]");
            this.count = count + 1;
        }
    }
}
