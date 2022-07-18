package ui;

import domain.Status;
import domain.Task;
import domain.User;
import service.Service;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class UI {
    private Service service;
    Scanner scanner = new Scanner(System.in);

    public UI(Service service) {
        this.service = service;
    }

    private void print_menu() {
        System.out.println("Menu: ");
        System.out.println("0 - exit");
        System.out.println("1 - create user");
        System.out.println("2 - find user by username");
        System.out.println("3 - show all tasks");
        System.out.println("4 - create task");
        System.out.println("5 - delete task");
        System.out.println("6 - update task");
        System.out.println("7 - update task status");
        System.out.println("8 - find task by id");
        System.out.println("9 - find tasks by user");
    }

    private void createUser() {}

    private void findUserByUsername() {
        System.out.println("username: ");
        String username = scanner.nextLine();
        User user = service.findUserByUsername(username);
        if (user == null) {
            System.out.println("User not found! :<");
            return;
        }
        System.out.println(user);
    }

    private void showAllTasks() {
        List<Task> tasks = service.findAllTasks();
        System.out.println("Printing all tasks: ");
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    private void createTask() {
        System.out.println("username: ");
        String username = scanner.nextLine();
        User user = service.findUserByUsername(username);
        if (user == null) {
            System.out.println("There is no such user! :<");
            return;
        }

        System.out.println("name: ");
        String name = scanner.nextLine();
        if (Objects.equals(name, "")) {
            System.out.println("Please enter a valid name! :<");
            return;
        }

        System.out.println("status: ");
        String statusString = scanner.nextLine();
        Status status = Status.getStatus(statusString);
        if (status == null) {
            System.out.println("Please enter a valid status! :<");
            return;
        }

        Task task = new Task(user, status, name);
        service.createTask(task);
        System.out.println("Task created successfully: " + task);
    }

    private void deleteTask() {}

    private void updateTask() {}

    private void updateTaskStatus() {}

    private void findTaskById() {
        System.out.println("id: ");
        String idString = scanner.nextLine();
        int id = 0;
        try {
            id = Integer.parseInt(idString);
        } catch (Exception e) {
            System.out.println("Please enter a valid id!");
            return;
        }

        Task task = service.findTaskById(id);
        if (task == null) {
            System.out.println("There is no task with this id! :<");
            return;
        }
        System.out.println(task);
    }

    private void findTasksByUser() {
        System.out.println("username: ");
        String username = scanner.nextLine();
        User user = service.findUserByUsername(username);
        if (user == null) {
            System.out.println("There is no such user! :<");
            return;
        }

        List<Task> tasks = service.findTasksByUser(user);
        System.out.println("Printing all tasks for user: " + user);
        if(tasks.isEmpty()){
            System.out.println("There are no tasks for this user! :<");
            return;
        }
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    public void run() {
        print_menu();
//        try {
//            wait(110);
//        } catch (Exception e) {
//            System.out.println("Cannot wait");
//        }
        while (true) {
            String string = scanner.nextLine();

            int option = 0;
            try {
                option = Integer.parseInt(string);
            } catch (Exception e) {
                System.out.println("Please enter a valid number!");
                continue;
            }

            if(option == 0) {
                System.out.println("Bye bye! :>");
                return;
            }

            if(option == 1) {
                createUser();
            }
            else if(option == 2) {
                findUserByUsername();
            }
            else if(option == 3) {
                showAllTasks();
            }
            else if(option == 4) {
                createTask();
            }
            else if(option == 5) {
                deleteTask();
            }
            else if(option == 6) {
                updateTask();
            }
            else if(option == 7) {
                updateTaskStatus();
            }
            else if(option == 8) {
                findTaskById();
            }
            else if(option == 9) {
                findTasksByUser();
            }
            else {
                System.out.println("Please enter a valid command!");
            }
            print_menu();
        }
    }
}
