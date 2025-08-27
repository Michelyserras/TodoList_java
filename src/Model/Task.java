package Model;

import java.time.LocalDate;
import java.util.*;

public class Task {
    private int id;
    private String nome;
    private String descrição;
    private LocalDate dataTermino;
    private int prioridade;
    private Status status;
    private String categoria;


    public Task(String nome, String descrição, LocalDate dataTermino, int prioridade, Status status, String categoria) {
        this.id = id;
        this.nome = nome;
        this.descrição = descrição;
        this.dataTermino = dataTermino;
        this.prioridade = prioridade;
        this.status = status;
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrição() { return descrição; }

    public void setDescrição(String descrição) { this.descrição = descrição; }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    @Override
    public String toString() {
        return "Task{id=" + id +
                ", nome='" + nome + '\'' +
                ", descrição=" + descrição +
                ", prioridade=" + prioridade +
                ", categoria='" + categoria + '\'' +
                ", status=" + status + '}';
    }


}
