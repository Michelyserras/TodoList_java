package Service;

import Model.Status;
import Model.Task;

import Repository.TaskRepository;

import java.time.LocalDate;
import java.util.List;

public class TodoListService {
    private TaskRepository todoListRepository;

    public TodoListService(TaskRepository todoListRepository){
        this.todoListRepository = todoListRepository;
    }

    public void addTarefa(Task task) throws Exception {
        if(task.getNome().isEmpty() || task.getCategoria().isEmpty() || task.getDataTermino() == null || task.getStatus() == null){
            throw new Exception("Os dados devem estar completos");
        }

        if(task.getPrioridade() > 5 || task.getPrioridade() < 0){
            System.out.println("A prioridade deve ser entre 1-5");
        }

        Task newTask = new Task(
                task.getNome(),
                task.getDescrição(),
                task.getDataTermino(),
                task.getPrioridade(),
                task.getStatus(),
                task.getCategoria()
        );
        todoListRepository.createTask(newTask);
    }

    public Task buscarTarefaPorId(int id) throws Exception {
        Task tarefaEncontrada = todoListRepository.findTask(id);
        if(tarefaEncontrada != null){
            return tarefaEncontrada;
        }
        throw new Exception("A tarefa com o id=" + id + "não foi encontrada");
    }

    public List<Task> buscarTarefasPorData(LocalDate data) throws Exception {
        List<Task> listaTarefasPorData = todoListRepository.findByDate(data);
        if(listaTarefasPorData.isEmpty()){
            throw new Exception("Não existe nenhuma tarefa com a data: " + data);
        }
        return listaTarefasPorData;
    }

    public List<Task> buscarTarefasPorCategoria(String categoria) throws Exception {
        List<Task> listaTarefasPorCategoria = todoListRepository.findByCategoria(categoria);
        if(listaTarefasPorCategoria.isEmpty()){
            throw new Exception("Não existem tarefas cadastradas com a categoria: " + categoria);
        }
        return listaTarefasPorCategoria;
    }

    public List<Task> buscarTarefasPorStatus(Status status) throws Exception {
        List<Task> listaTarefasPorStatus = todoListRepository.findByStatus(status);
        if(listaTarefasPorStatus.isEmpty()){
            throw new Exception("Não existem tarefas com o status: " + status);
        }
        return listaTarefasPorStatus;
    }

    public List<Task> listarTodasAsTarefas() throws Exception {
        List<Task> listaTarefas = todoListRepository.findAllTasks();
        if(listaTarefas.isEmpty()){
            throw new Exception("Não há nenhuma tarefa cadastrada");
        }
        return listaTarefas;
    }

    public List<Task> listarTarefasPorPrioridade(int num) throws Exception {
        if(num > 5 || num < 0){
            System.out.println("O valor deve ser entre 1 - 5");
        }
        List<Task> listaPorPrioridade = todoListRepository.findByPrioridade(num);
        if(listaPorPrioridade.isEmpty()){
            throw new Exception("Não existe nenhuma tarefa com a prioridade: " + num);
        }
        return listaPorPrioridade;
    }

    public void deletarTarefa(int id) throws Exception {
        Task tarefaExiste = todoListRepository.findTask(id);
        if(tarefaExiste == null){
            throw new Exception("Tarefa a ser deletada não existe");
        }
        todoListRepository.deleteTask(id);
    }



}
