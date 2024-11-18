import java.io.*;
import java.util.*;

public class Task1 {
    static class Job {
        int id;
        int processingTime;

        public Job(int id, int processingTime) {
            this.id = id;
            this.processingTime = processingTime;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("task1-input.txt"));
        PriorityQueue<Job> pq = new PriorityQueue<>(Comparator.comparingInt(j -> j.processingTime));

        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(" ");
            int id = Integer.parseInt(parts[0]);
            int processingTime = Integer.parseInt(parts[1]);
            pq.offer(new Job(id, processingTime));
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

