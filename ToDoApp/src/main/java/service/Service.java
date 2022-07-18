package service;

import domain.Task;
import domain.User;
import repository.TaskRepository;
import repository.UserRepository;

import java.util.List;

public class Service {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public Service(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    public void createUser(User user) {
        userRepository.create(user);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void createTask(Task task) {
        taskRepository.create(task);
    }

    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

    public void updateTask(Task task) {
        taskRepository.update(task, task.getId());
    }

    public void updateTaskStatus(Task task) {
        taskRepository.updateStatus(task.getStatus(), task.getId());
    }

    public Task findTaskById(Integer id) {
        Task task = taskRepository.findById(id);
        if (task == null)
            return null;

        User user = findUserByUsername(task.getUser().getUsername());
        task.setUser(user);
        return task;
    }

    public List<Task> findTasksByUser(User user) {
        return taskRepository.findByUser(user);
    }

    public List<Task> findAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        for (Task task : tasks) {
            User user = findUserByUsername(task.getUser().getUsername());
            task.setUser(user);
        }
        return tasks;
    }
}
