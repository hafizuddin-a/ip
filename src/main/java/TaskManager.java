import java.util.Scanner;
public class TaskManager {
    private Task[] tasks;
    private int taskCount;

    private void printList() {
        System.out.println("______________________________________________________________\n"
                + " Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(" " + (i + 1) + ". [" + tasks[i].getTaskType() + "] "
                    + "[" + tasks[i].getStatusIcon() + "] "
                    + tasks[i].getDescription());
        }
        System.out.println("_____________________________________________________________");
    }

    private void markTask(int index, boolean isDone) {
        if (isDone) {
            System.out.println("______________________________________________________________\n"
                    + " Good job! I've marked this task as done:\n"
                    + " [X] " + tasks[index].getDescription() + "\n"
                    + "_____________________________________________________________");
        } else {
            System.out.println("______________________________________________________________\n"
                    + " Okay! I've marked this task as not done yet:\n"
                    + " [ ] " + tasks[index].getDescription() + "\n"
                    + "_____________________________________________________________");
        }
    }

    private void addTask(Task task, String input) {
        if (input.startsWith("todo")) {
            tasks[taskCount] = new Todo(input.substring(5));
            printTask(tasks[taskCount]);
        } else if (input.startsWith("deadline")) {
            String[] words = input.split(" /by ");
            tasks[taskCount] = new Deadline(words[0].substring(9), words[1]);
            printTask(tasks[taskCount]);
        } else if (input.startsWith("event")) {
            String[] eventDetails = input.split(" /from | /to ");
            String description = eventDetails[0].substring(6);
            String from = eventDetails[1];
            String to = eventDetails[2];
            tasks[taskCount] = new Event(description, from, to);
            printTask(tasks[taskCount]);
        } else {
            tasks[taskCount] = task;
            printTask(tasks[taskCount]);
        }
    }

    private void printTask(Task task) {
        System.out.println("______________________________________________________________\n"
                + " Got it. I've added this task:\n"
                + " [" + task.getTaskType() + "] [" + task.getStatusIcon() + "] " + task.getDescription() + "\n"
                + " Now you have " + (taskCount + 1) + " tasks in the list.\n"
                + "_____________________________________________________________");
    }

    public TaskManager() {
        tasks = new Task[100];
        taskCount = 0;

        while (true) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                printList();
            } else if (input.startsWith("mark")) {
                String[] words = input.split(" ");
                int index = Integer.parseInt(words[1]) - 1;
                tasks[index].markTask(true);
                markTask(index, true);
            } else if (input.startsWith("unmark")) {
                String[] words = input.split(" ");
                int index = Integer.parseInt(words[1]) - 1;
                tasks[index].markTask(false);
                markTask(index, false);
            } else {
                Task task = new Task(input);
                addTask(task, input);
                taskCount++;
            }
        }
    }
}
