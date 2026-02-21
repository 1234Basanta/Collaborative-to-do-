import java.util.ArrayList;
import java.util.List;

class Task {
    int id;
    String title;
    boolean isCompleted;

    Task(int id, String title) {
        this.id = id;
        this.title = title;
        this.isCompleted = false;
    }
}

class TaskManager {
    private final List<Task> tasks = new ArrayList<>();

    // "synchronized" ensures only one thread adds a task at a time
    public synchronized void addTask(String title) {
        int id = tasks.size() + 1;
        tasks.add(new Task(id, title));
        System.out.println("Added: " + title + " (By Thread: " + Thread.currentThread().getName() + ")");
    }

    public synchronized void showTasks() {
        System.out.println("\n--- Current To-Do List ---");
        for (Task t : tasks) {
            System.out.println("[" + (t.isCompleted ? "X" : " ") + "] " + t.id + ": " + t.title);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();

        // Simulating two users adding tasks concurrently
        Thread user1 = new Thread(() -> manager.addTask("Fix Java Bugs"));
        Thread user2 = new Thread(() -> manager.addTask("Write Documentation"));

        user1.start();
        user2.start();

        try {
            user1.join();
            user2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        manager.showTasks();
    }
}
