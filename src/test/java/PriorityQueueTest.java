import java.util.PriorityQueue;
import org.junit.jupiter.api.Test;

public class PriorityQueueTest {

    @Test
    void poll() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(10);
        pq.add(15);
        pq.add(20);
        pq.add(17);

        while (pq.size() > 0) {
            System.out.println("pq.poll() = " + pq.poll());
        }
    }

}
