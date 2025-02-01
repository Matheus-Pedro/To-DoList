package todo.model.dao;

import todo.model.entity.Task;
import java.util.List;

public interface ITaskDAO {
    int createTask(Task task);
    Task retrieveTask(int taskId);
    List<Task> retrieveTasksByUser(int userId);
    int updateTask(Task task);
    int deleteTask(int taskId);
}