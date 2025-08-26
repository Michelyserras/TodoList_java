package Repository;

import Model.Status;
import Model.Task;

import java.util.List;

public interface TaskRepository {
    public void createTask(Task task);
    public Task findTask(int id);
    public void deleteTask(int id);
    public List<Task> findAllTasks();
    public void findByCategoria(int id);
    public List<Task> findByPrioridade(int id);
    public List<Task> findByStatus(Status status);
}
