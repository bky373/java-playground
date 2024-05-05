import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Test;

public class CounterMultiThreadsTest {

    @Test
    void test1() throws InterruptedException {
        Counter counter = new Counter();

        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 1000; i++) {
            executor.submit(counter::increment);
        }
        executor.shutdown();
        Thread.sleep(500);
        System.out.println("counter = " + counter.count);
    }

    class Counter {

        int count;

        public void increment() {
            this.count = count + 1;
        }
    }
}
