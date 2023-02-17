package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.util.ArrayList;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        System.out.println("Welcome to Task (stream) manager\n");
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();

        System.out.println();
        System.out.println("Printing deadlines");
        //printDeadlines(tasksData);
        printDeadlines(tasksData);

        System.out.println("Total number of deadlines: " + countDeadlinesUsingStreams(tasksData));
        //printData(tasksData);
        //printDataUsingStream(tasksData);
    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    public static void printData(ArrayList<Task> tasksData) {
        System.out.println("Print data using iteration:");
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }
    public static void printDataUsingStream(ArrayList<Task> tasks) {

        tasks.parallelStream()      //convert to stream
                .forEach(System.out::println);

    }
    public static void printDeadlines(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }
    public static void printDeadLinesUsingStreams(ArrayList<Task> tasks) {
        System.out.println("Print deadlines using streams");
        tasks.stream()
                .filter(t -> t instanceof Deadline) //filter takes a predicate/condition
                .forEach(System.out::println);
    }

    public static int countDeadlinesUsingStreams(ArrayList<Task> tasks) {
        int count = (int) tasks.stream()
                .filter(t -> t instanceof Deadline)
                .count();
        return count;
    }
}
