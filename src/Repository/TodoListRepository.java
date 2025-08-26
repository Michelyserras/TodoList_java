package Repository;

import Model.Status;
import Model.Task;
import Model.TodoList;

import java.util.Collections;
import java.util.List;

public class TodoListRepository implements TaskRepository{

    @Override
    public void createTask(Task task) {

    }

    @Override
    public Task findTask(int id) {
        return null;
    }

    @Override
    public void deleteTask(int id) {

    }

    @Override
    public List<Task> findAllTasks() {
        return Collections.emptyList();
    }

    @Override
    public void findByCategoria(int id) {

    }

    @Override
    public List<Task> findByPrioridade(int id) {
        return Collections.emptyList();
    }

    @Override
    public List<Task> findByStatus(Status status) {
        return Collections.emptyList();
    }
}
