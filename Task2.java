import java.io.*;
import java.util.*;

public class Task2 {
    static class Job {
        int id;
        int processingTime;
        int priority;

        public Job(int id, int processingTime, int priority) {
            this.id = id;
            this.processingTime = processingTime;
            this.priority = priority;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("task2-input.txt"));
        PriorityQueue<Job> pq = new PriorityQueue<>((j1, j2) -> {
            if (j1.priority == j2.priority)
                return Integer.compare(j1.processingTime, j2.processingTime);
            return Integer.compare(j1.priority, j2.priority);
        });

        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(" ");
            int id = Integer.parseInt(parts[0]);
            int processingTime = Integer.parseInt(parts[1]);
            int priority = Integer.parseInt(parts[2]);
            pq.offer(new Job(id, processingTime, priority));
        }

        List<Integer> executionOrder = new ArrayList<>();
        int currentTime = 0;
        int totalCompletionTime = 0;

        while (!pq.isEmpty()) {
            Job job = pq.poll();
            executionOrder.add(job.id);
            currentTime += job.processingTime;
            totalCompletionTime += currentTime;
        }

        System.out.println("Execution order: " + executionOrder);
        System.out.println("Average completion time: " + (double) totalCompletionTime / executionOrder.size());
    }
}

