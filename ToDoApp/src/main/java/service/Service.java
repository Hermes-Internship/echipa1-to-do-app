package service;

import repository.TaskRepository;
import repository.UserRepository;

public class Service {
    private UserRepository userRepository;
    private TaskRepository taskRepository;

    public Service(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }
}
