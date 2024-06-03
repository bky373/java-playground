import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import org.example.ThreadSupport;
import org.junit.jupiter.api.Test;

// 예제 참고 https://codechacha.com/ko/java-executors/
class ExecutorsTest {

    @Test
    void test() {
        Thread.currentThread().interrupt();
        System.out.println("Interrupted while waiting for topic creation results");
    }

    @Test
    void test1() throws InterruptedException {
        int nThreads = 2;
        ExecutorService executor = Executors.newFixedThreadPool(nThreads);

        for (int i = 1; i <= nThreads + 3; i++) {
            final int x = i;
            executor.submit(() -> System.out.println("Job " + x + "-" + ThreadSupport.getThreadName()));
        }
        // shutdown()은 더 이상 쓰레드풀에 작업을 추가하지 못하도록 합니다. 그리고 처리 중인 Task가 모두 완료되면 쓰레드풀을 종료시킵니다.
        assertThatExceptionOfType(RejectedExecutionException.class)
                .isThrownBy(() -> executor.submit(() -> System.out.println("Job 1 - " + ThreadSupport.getThreadName())));

        if (executor.awaitTermination(1, TimeUnit.NANOSECONDS)) {
            System.out.println("## All jobs are terminated");
        } else {
            System.out.println("## Some jobs are not terminated");
            executor.shutdownNow();
        }
        System.out.println("## Finished");
    }
}
