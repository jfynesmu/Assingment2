import java.io.*;
import java.util.*;

public class Task3 {
    static class Job {
        int id;
        int processingTime;
        int arrivalTime;

        public Job(int id, int processingTime, int arrivalTime) {
            this.id = id;
            this.processingTime = processingTime;
            this.arrivalTime = arrivalTime;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("task3-input.txt"));
        List<Job> jobs = new ArrayList<>();
        PriorityQueue<Job> pq = new PriorityQueue<>(Comparator.comparingInt(j -> j.processingTime));

        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(" "); // Split by space instead of comma
            int id = Integer.parseInt(parts[0]);
            int processingTime = Integer.parseInt(parts[1]);
            int arrivalTime = Integer.parseInt(parts[2]);
            jobs.add(new Job(id, processingTime, arrivalTime));
        }

        jobs.sort(Comparator.comparingInt(j -> j.arrivalTime)); // Sort by arrival time
        int currentTime = 0, index = 0;
        int totalCompletionTime = 0;
        List<Integer> executionOrder = new ArrayList<>();

        while (!pq.isEmpty() || index < jobs.size()) {
            while (index < jobs.size() && jobs.get(index).arrivalTime <= currentTime) {
                pq.offer(jobs.get(index));
                index++;
            }

            if (!pq.isEmpty()) {
                Job job = pq.poll();
                currentTime += job.processingTime;
                totalCompletionTime += currentTime;
                executionOrder.add(job.id);
            } else {
                currentTime = jobs.get(index).arrivalTime;
            }
        }

        System.out.println("Execution order: " + executionOrder);
        System.out.println("Average completion time: " + (double) totalCompletionTime / executionOrder.size());
    }
}

