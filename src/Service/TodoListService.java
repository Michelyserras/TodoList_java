package Service;

import Model.Status;
import Model.Task;
import Model.TodoList;
import Repository.TaskRepository;
import Repository.TodoListRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TodoListService {
    private final Map<TodoList, TaskRepository> repositorios = new HashMap<>();
    private final List<TodoList> listas = new ArrayList<>();

    private TaskRepository getRepo(TodoList lista) throws Exception {
        TaskRepository todoListRepository = repositorios.get(lista);
        if (todoListRepository == null) {
            throw new Exception("Lista não encontrada");
        }
        return todoListRepository;
    }

  // src/Service/TodoListService.java
  public void addTarefa(TodoList lista, Task task) throws Exception {
      if(task.getNome().isEmpty() || task.getCategoria().isEmpty() || task.getDataTermino() == null || task.getStatus() == null){
          throw new Exception("Os dados devem estar completos");
      }

      if(task.getPrioridade() > 5 || task.getPrioridade() < 1){
          throw new Exception("A prioridade deve ser entre 1-5");
      }

      Task newTask = new Task(
              task.getNome(),
              task.getDescricao(),
              task.getDataTermino(),
              task.getPrioridade(),
              task.getStatus(),
              task.getCategoria()
      );
      getRepo(lista).createTask(newTask);
  }

    public Task buscarTarefaPorId(TodoList lista, int id) throws Exception {
        Task tarefaEncontrada = getRepo(lista).findTask(id);
        if(tarefaEncontrada != null){
            return tarefaEncontrada;
        }
        throw new Exception("A tarefa com o id=" + id + "não foi encontrada");
    }

    public List<Task> buscarTarefasPorData(TodoList lista, LocalDate data) throws Exception {
        List<Task> listaTarefasPorData = getRepo(lista).findByDate(data);
        if(listaTarefasPorData.isEmpty()){
            throw new Exception("Não existe nenhuma tarefa com a data: " + data);
        }
        return listaTarefasPorData;
    }

    public List<Task> buscarTarefasPorCategoria(TodoList lista, String categoria) throws Exception {
        List<Task> listaTarefasPorCategoria = getRepo(lista).findByCategoria(categoria);
        if(listaTarefasPorCategoria.isEmpty()){
            throw new Exception("Não existem tarefas cadastradas com a categoria: " + categoria);
        }
        return listaTarefasPorCategoria;
    }

    public List<Task> buscarTarefasPorStatus(TodoList lista, Status status) throws Exception {
        List<Task> listaTarefasPorStatus = getRepo(lista).findByStatus(status);
        if(listaTarefasPorStatus.isEmpty()){
            throw new Exception("Não existem tarefas com o status: " + status);
        }
        return listaTarefasPorStatus;
    }

    public List<Task> listarTodasAsTarefas(TodoList lista) throws Exception {
        List<Task> listaTarefas = getRepo(lista).findAllTasks();
        if(listaTarefas.isEmpty()){
            throw new Exception("Não há nenhuma tarefa cadastrada");
        }
        return listaTarefas;
    }

    public List<Task> listarTarefasPorPrioridade(TodoList lista, int num) throws Exception {
        if(num > 5 || num < 0){
            System.out.println("O valor deve ser entre 1 - 5");
        }
        List<Task> listaPorPrioridade = getRepo(lista).findByPrioridade(num);
        if(listaPorPrioridade.isEmpty()){
            throw new Exception("Não existe nenhuma tarefa com a prioridade: " + num);
        }
        return listaPorPrioridade;
    }

    public void deletarTarefa(TodoList lista, int id) throws Exception {
        Task tarefaExiste = getRepo(lista).findTask(id);
        if(tarefaExiste == null){
            throw new Exception("Tarefa a ser deletada não existe");
        }
        getRepo(lista).deleteTask(id);
    }

    public TodoList criarLista(String nome) throws Exception {
        TodoList novaLista = new TodoList(nome);
        listas.add(novaLista);
        repositorios.put(novaLista, new TodoListRepository(novaLista));
        return novaLista;
    }

    public List<TodoList> listarListas() {
        return listas;
    }
}
