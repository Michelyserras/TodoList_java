package Model;

import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class TodoList {
    private String nome;
    private List<Task> tarefas;


    public TodoList(String nome){
        this.nome = nome;
        this.tarefas = new ArrayList<>();
    }

    public TodoList(String nome, List<Task> tarefas) {
        this.nome = nome;
        this.tarefas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Task> getTarefas() {
        return tarefas;
    }

    public void setTarefas(List<Task> tarefas) {
        this.tarefas = tarefas;
    }

    public void addTask(Task task){
        tarefas.add(task);
        tarefas.sort(Comparator.comparingInt(Task::getPrioridade));
    }

    public void removerTask(int id){
        tarefas.removeIf(task -> task.getId() == id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Lista: ").append(nome).append("\n");
        sb.append("Tarefas:\n");

        for (Task task : tarefas) {
            sb.append(" - ").append(task).append("\n"); // usa o toString da Task
        }

        return sb.toString();
    }

}
