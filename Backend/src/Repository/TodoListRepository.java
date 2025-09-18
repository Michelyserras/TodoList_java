package Repository;

import Model.Status;
import Model.Task;
import Model.TodoList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TodoListRepository implements TaskRepository{
    private TodoList todoList;
    private int count = 0;

    public TodoListRepository(TodoList todoList){
        this.todoList = todoList;
    }

    @Override
    public void createTask(Task task) {
        task.setId(count++);
        todoList.addTask(task);
    }

    @Override
    public Task findTask(int id) {
        for(Task task: todoList.getTarefas()){
            if(task.getId() == id){
                return task;
            }
        }
        return null;
    }

    @Override
    public void deleteTask(int id) {
        todoList.removerTask(id);
    }

    @Override
    public List<Task> findAllTasks() {
        return todoList.getTarefas();
    }

    @Override
    public List<Task> findByCategoria(String name) {
        List<Task> listaPorCategoria = new ArrayList<>();
        for(Task task: todoList.getTarefas()){
            if(task.getCategoria().equals(name)){
                listaPorCategoria.add(task);
            }
        }
        return listaPorCategoria;
    }

    @Override
    public List<Task> findByPrioridade(int num) {
        List<Task> listaPorPrioridade = new ArrayList<>();
        for(Task task: todoList.getTarefas()){
            if(task.getPrioridade() == num){
                listaPorPrioridade.add(task);
            }
        }
        return listaPorPrioridade;
    }

    @Override
    public List<Task> findByStatus(Status status) {
        List<Task> listaPorStatus = new ArrayList<>();
        for(Task task: todoList.getTarefas()){
            if(task.getStatus().equals(status)){
                listaPorStatus.add(task);
            }
        }
        return listaPorStatus;
    }

    public List<Task> findByDate(LocalDate dataFinal){
        List<Task> listaPorData = new ArrayList<>();
        for(Task task: todoList.getTarefas()){
            if(task.getDataTermino().equals(dataFinal)){
                listaPorData.add(task);
            }
        }
        return listaPorData;
    }
}
