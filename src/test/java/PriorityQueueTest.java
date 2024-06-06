import static org.assertj.core.api.Assertions.assertThat;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

public class PriorityQueueTest {

    @Test
    void poll() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(10);
        pq.add(15);
        pq.add(20);
        pq.add(17);

        while (!pq.isEmpty()) {
            System.out.println("pq.poll() = " + pq.poll());
        }
    }

    @Test
    void taskPQ() {
        PriorityQueue<Task> pq = new PriorityQueue<>(new TaskComparator());
        pq.add(new Task("t1", 3));
        pq.add(new Task("t2", 1));
        pq.add(new Task("t3", 2));

        while (!pq.isEmpty()) {
            Task task = pq.poll();
            System.out.println("task = " + task);
        }
    }

    static class Task {

        private String name;
        private int priority;

        public Task(String name, int priority) {
            this.name = name;
            this.priority = priority;
        }

        public int getPriority() {
            return priority;
        }

        @Override
        public String toString() {
            return "Task[name=" + name + ", priority=" + priority + "]";
        }
    }

    static class TaskComparator implements Comparator<Task> {

        @Override
        public int compare(Task t1, Task t2) {
            return Integer.compare(t1.getPriority(), t2.getPriority());
        }
    }


    @Test
    void topKFrequent() {
        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        int k = 2;

        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        Queue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(freqMap::get));
        for (Integer n : freqMap.keySet()) {
            heap.add(n);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        int[] ans = IntStream.range(0, k)
                               .map(i -> heap.poll())
                               .toArray();
        assertThat(ans).containsExactlyInAnyOrder(1, 2);
    }

    static class Counter {

        private int num;
        private int count;

        public Counter(int num, int count) {
            this.num = num;
            this.count = count;
        }

        public int getCount() {
            return count;
        }
    }

    static class CounterComparator implements Comparator<Counter> {

        @Override
        public int compare(Counter c1, Counter c2) {
            return Integer.compare(c1.getCount(), c2.getCount());
        }
    }
}
