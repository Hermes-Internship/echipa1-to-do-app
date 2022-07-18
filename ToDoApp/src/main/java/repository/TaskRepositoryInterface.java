package repository;

import domain.Status;
import domain.Task;
import domain.User;

import java.util.List;

public interface TaskRepositoryInterface {
    void create(Task task);
    void delete(Task task);
    void update(Task task, Integer id);
    void updateStatus(Status status, Integer id);
    Task findById(Integer id);
    List<Task> findByUser(User user);
    List<Task> findAll();
}
