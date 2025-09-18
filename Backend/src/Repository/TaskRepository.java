package Repository;

import Model.Status;
import Model.Task;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository {
    public void createTask(Task task);
    public Task findTask(int id);
    public void deleteTask(int id);
    public List<Task> findAllTasks();
    public List<Task> findByCategoria(String name);
    public List<Task> findByPrioridade(int num);
    public List<Task> findByStatus(Status status);
    public List<Task> findByDate(LocalDate data);
}
