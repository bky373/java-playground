import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

// 예제 참고 https://codechacha.com/ko/java-countdownlatch/
class CountDownLatchTest {

    @Test
    void startThreadsInConsecutiveOrder() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(6);
        latch.countDown();
        assertThat(latch.getCount()).isEqualTo(5);
        List<Thread> workers = Stream.generate(() -> new Thread(new Worker(latch)))
                                     .limit(5)
                                     .toList();
        System.out.println("## Start multi-threads [tid: " + getThreadId() + "]");
        workers.forEach(Thread::start);
        System.out.println("## Waiting for multi-threads to be finished [tid: " + getThreadId() + "]");
        latch.await();
        assertThat(latch.getCount()).isZero();
        System.out.println("## Finished multi-threads [tid: " + getThreadId() + "]");
    }

    @Test
    void startThreadsSimultaneously() throws InterruptedException {
        CountDownLatch readyLatch = new CountDownLatch(5);
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch finishLatch = new CountDownLatch(5);
        List<Thread> workers = Stream.generate(() -> new Thread(new Worker(readyLatch, startLatch, finishLatch)))
                                     .limit(5)
                                     .toList();
        System.out.println("## Start multi-threads [tid: " + getThreadId() + "]");
        workers.forEach(Thread::start);
        readyLatch.await();
        System.out.println("## Ready to run multi-threads [tid: " + getThreadId() + "]");
        assertThat(finishLatch.getCount()).isEqualTo(5);
        startLatch.countDown();
        System.out.println("## Waiting for multi-threads to be finished [tid: " + getThreadId() + "]");
        finishLatch.await();
        assertThat(finishLatch.getCount()).isZero();
        System.out.println("## Finished multi-threads [tid: " + getThreadId() + "]");
    }

    @Test
    void waitForSeconds() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
        List<Thread> workers = Stream.generate(() -> new Thread(new Worker(latch, 2000)))
                                  .limit(5)
                                  .toList();
        System.out.println("## Start multi-threads [tid: " + getThreadId() + "]");
        workers.forEach(Thread::start);
        System.out.println("## Waiting for multi-threads to be finished [tid: " + getThreadId() + "]");
        latch.await(1, TimeUnit.SECONDS);
        System.out.println("## Finished multi-threads [tid: " + getThreadId() + "]");
        assertThat(latch.getCount()).isEqualTo(5);
        Thread.sleep(2000);
    }

    private class Worker implements Runnable {

        private CountDownLatch readyLatch;
        private CountDownLatch startLatch;
        private CountDownLatch finishLatch;
        private long sleepMillis;

        public Worker(CountDownLatch finishLatch) {
            this.finishLatch = finishLatch;
        }

        public Worker(CountDownLatch finishLatch, long sleepMillis) {
            this.sleepMillis = sleepMillis;
            this.finishLatch = finishLatch;
        }

        public Worker(CountDownLatch readyLatch,
                      CountDownLatch startLatch,
                      CountDownLatch finishLatch) {
            this.readyLatch = readyLatch;
            this.startLatch = startLatch;
            this.finishLatch = finishLatch;
        }

        @Override
        public void run() {
            if (sleepMillis > 0) {
                System.out.println("start to run on [tid: " + getThreadId() + "]");
                try {
                    Thread.sleep(sleepMillis);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                finishLatch.countDown();
                System.out.println("end running on [tid: " + getThreadId() + "]");
            } else if (readyLatch == null) {
                System.out.println("run on [tid: " + getThreadId() + "]");
                finishLatch.countDown();
            } else {
                readyLatch.countDown();
                try {
                    startLatch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("run on [tid: " + getThreadId() + "]");
                finishLatch.countDown();
            }
        }
    }

    private static long getThreadId() {
        return Thread.currentThread()
                     .getId();
    }
}
