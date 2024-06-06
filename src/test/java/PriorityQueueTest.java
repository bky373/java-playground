import java.util.Comparator;
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
}
