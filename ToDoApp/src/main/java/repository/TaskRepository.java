package repository;

import domain.Status;
import domain.Task;
import domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TaskRepository implements TaskRepositoryInterface{
    private JdbcUtils dbUtils;

    public TaskRepository(Properties props) {
        dbUtils = new JdbcUtils(props);
    }

    @Override
    public void create(Task task) {
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStnt = con.prepareStatement("insert into tasks (username, status, name) values (?, ?, ?)")) {
            preStnt.setString(1, task.getUser().getUsername());
            preStnt.setString(2, task.getStatus().toString());
            preStnt.setString(3, task.getName());
            preStnt.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error DB " + ex);
        }
    }

    @Override
    public void delete(Task task) {

    }

    @Override
    public void update(Task task, Integer id) {

    }

    @Override
    public void updateStatus(Status status, Integer id) {

    }

    @Override
    public Task findById(Integer id) {
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStnt = con.prepareStatement("select * from tasks where id = ?")) {
            preStnt.setInt(1, id);
            try(ResultSet result = preStnt.executeQuery()) {
                if (result.next()) {
                    String name = result.getString("name");
                    String statusString = result.getString("status");
                    Status status = Status.getStatus(statusString);
                    String username = result.getString("username");
                    User user = new User(username);
                    Task task = new Task(id, user, status, name);
                    return task;
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error DB " + ex);
        }
        return null;
    }

    @Override
    public List<Task> findByUser(User user) {
        List<Task> taskList = new ArrayList<>();
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStnt = con.prepareStatement("select * from tasks where username = ?")) {
            preStnt.setString(1, user.getUsername());
            try(ResultSet result = preStnt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("id");
                    String name = result.getString("name");
                    String statusString = result.getString("status");
                    Status status = Status.getStatus(statusString);
                    Task task = new Task(id, user, status, name);
                    taskList.add(task);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error DB " + ex);
        }
        return taskList;
    }

    @Override
    public List<Task> findAll() {
        List<Task> taskList = new ArrayList<>();
        Connection con = dbUtils.getConnection();
        try(PreparedStatement preStnt = con.prepareStatement("select * from tasks")) {
            try(ResultSet result = preStnt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("id");
                    String name = result.getString("name");
                    String statusString = result.getString("status");
                    Status status = Status.getStatus(statusString);
                    String username = result.getString("username");
                    User user = new User(username);
                    Task task = new Task(id, user, status, name);
                    taskList.add(task);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error DB " + ex);
        }
        return taskList;
    }
}
