package domain;

import java.util.Objects;

public class Task {
    private Integer id;
    private User user;
    private Status status;
    private String name;

    public Task(Integer id, User user, Status status, String name) {
        this.id = id;
        this.user = user;
        this.status = status;
        this.name = name;
    }

    public Task(User user, Status status, String name) {
        this.user = user;
        this.status = status;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", user=" + user +
                ", status=" + status +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) && Objects.equals(user, task.user) && status == task.status && Objects.equals(name, task.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, status, name);
    }
}
