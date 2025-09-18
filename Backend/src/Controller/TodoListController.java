package Controller;

import Model.Status;
import Model.Task;
import Model.TodoList;
import Service.TodoListService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class TodoListController {

    private final TodoListService service;
    private TodoList listaAtiva;
    private final Scanner scanner = new Scanner(System.in);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public TodoListController(TodoListService service) {
        this.service = service;
    }

    public void menu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- MENU ---");
            System.out.println("1 - Criar nova lista");
            System.out.println("2 - Selecionar lista");
            System.out.println("3 - Adicionar tarefa");
            System.out.println("4 - Listar tarefas");
            System.out.println("5 - Deletar tarefa");
            System.out.println("6 - Buscar tarefas por categoria");
            System.out.println("7 - Buscar tarefas por prioridade");
            System.out.println("8 - Buscar tarefas por status");
            System.out.println("9 - Buscar tarefas por data");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = Integer.parseInt(scanner.nextLine().trim());

            try {
                switch (opcao) {
                    case 1:
                        criarLista();
                        break;
                    case 2:
                        selecionarLista();
                        break;
                    case 3:
                        adicionarTarefa();
                        break;
                    case 4:
                        listarTarefas();
                        break;
                    case 5:
                        deletarTarefa();
                        break;
                    case 6:
                        buscarTarefasPorCategoria();
                        break;
                    case 7:
                        buscarTarefasPorPrioridade();
                        break;
                    case 8:
                        buscarTarefasPorStatus();
                        break;
                    case 9:
                        buscarTarefasPorData();
                        break;

                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida");
                        break;
                }

            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private void criarLista() throws Exception {
        System.out.print("Digite o nome da nova lista: ");
        String nome = scanner.nextLine();
        listaAtiva = service.criarLista(nome);
        System.out.println("Lista '" + listaAtiva.getNome() + "' criada e selecionada!");
    }

    private void selecionarLista() {
        List<TodoList> listas = service.listarListas();
        if (listas.isEmpty()) {
            System.out.println("Não há listas criadas.");
            return;
        }

        System.out.println("Listas disponíveis:");
        for (int i = 0; i < listas.size(); i++) {
            System.out.println(i + " - " + listas.get(i).getNome());
        }
        System.out.print("Escolha o índice da lista: ");
        int idx = Integer.parseInt(scanner.nextLine().trim());
        if (idx < 0 || idx >= listas.size()) {
            System.out.println("Índice inválido.");
            return;
        }
        listaAtiva = listas.get(idx);
        System.out.println("Lista '" + listaAtiva.getNome() + "' selecionada!");
    }

    private void adicionarTarefa() {
        if (listaAtiva == null) {
            System.out.println("Selecione uma lista primeiro.");
            return;
        }
        try {
            System.out.print("Nome da tarefa: ");
            String nome = scanner.nextLine().trim();
            if (nome.isEmpty()) {
                System.out.println("O nome da tarefa não pode ser vazio.");
                return;
            }
            System.out.print("Descrição: ");
            String descricao = scanner.nextLine().trim();
            if (descricao.isEmpty()) {
                System.out.println("A descrição não pode ser vazia.");
                return;
            }
            System.out.print("Data término (dd/MM/yyyy): ");
            LocalDate data;
            try {
                data = LocalDate.parse(scanner.nextLine().trim(), formatter);
            } catch (Exception e) {
                System.out.println("Data inválida. Use o formato dd/MM/yyyy.");
                return;
            }
            System.out.print("Prioridade (1-5): ");
            int prioridade;
            try {
                prioridade = Integer.parseInt(scanner.nextLine().trim());
                if (prioridade < 1 || prioridade > 5) {
                    System.out.println("Prioridade deve ser entre 1 e 5.");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Prioridade inválida. Digite um número entre 1 e 5.");
                return;
            }
            System.out.print("Categoria: ");
            String categoria = scanner.nextLine().trim();
            if (categoria.isEmpty()) {
                System.out.println("A categoria não pode ser vazia.");
                return;
            }
            System.out.print("Status (TODO, DOING, DONE): ");
            String statusInput = scanner.nextLine().toUpperCase().trim();
            Status statusEscolhido;
            try {
                statusEscolhido = Status.valueOf(statusInput);
            } catch (Exception e) {
                System.out.println("Status inválido. Use TODO, DOING ou DONE.");
                return;
            }
            Task tarefa = new Task(nome, descricao, data, prioridade, statusEscolhido, categoria);
            service.addTarefa(listaAtiva, tarefa);
            System.out.println("Tarefa adicionada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao adicionar tarefa: " + e.getClass().getSimpleName() + (e.getMessage() != null ? ": " + e.getMessage() : ""));
            e.printStackTrace();
        }
    }


    private void listarTarefas() throws Exception {
        if (listaAtiva == null) {
            System.out.println("Selecione uma lista primeiro.");
            return;
        }
        List<Task> tarefas = service.listarTodasAsTarefas(listaAtiva);
        if (tarefas.isEmpty()) {
            System.out.println("Nenhuma tarefa cadastrada.");
            return;
        }
        System.out.println("Tarefas da lista '" + listaAtiva.getNome() + "':");
        for (Task t : tarefas) {
            System.out.println(t);
        }
    }

    private void deletarTarefa() throws Exception {
        if (listaAtiva == null) {
            System.out.println("Selecione uma lista primeiro.");
            return;
        }
        System.out.print("Digite o ID da tarefa a deletar: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        service.deletarTarefa(listaAtiva, id);
        System.out.println("Tarefa deletada com sucesso!");
    }

    private void buscarTarefasPorCategoria() throws Exception {
        if (listaAtiva == null) {
            System.out.println("Selecione uma lista primeiro.");
            return;
        }
        System.out.print("Digite a categoria: ");
        String categoria = scanner.nextLine();
        List<Task> tarefas = service.buscarTarefasPorCategoria(listaAtiva, categoria);
        for (Task t : tarefas) {
            System.out.println(t);
        }
    }

    private void buscarTarefasPorPrioridade() throws Exception {
        if (listaAtiva == null) {
            System.out.println("Selecione uma lista primeiro.");
            return;
        }
        System.out.print("Digite a prioridade (1-5): ");
        int prioridade = Integer.parseInt(scanner.nextLine().trim());
        List<Task> tarefas = service.listarTarefasPorPrioridade(listaAtiva, prioridade);
        for (Task t : tarefas) {
            System.out.println(t);
        }
    }


    private void buscarTarefasPorData() throws Exception {
        if (listaAtiva == null) {
            System.out.println("Selecione uma lista primeiro.");
            return;
        }
        System.out.print("Digite a data (dd/MM/yyyy): ");
        LocalDate data = LocalDate.parse(scanner.nextLine(), formatter);
        List<Task> tarefas = service.buscarTarefasPorData(listaAtiva, data);
        for (Task t : tarefas) {
            System.out.println(t);
        }
    }

    private void buscarTarefasPorStatus() throws Exception {
        if(listaAtiva == null){
            System.out.println("Selecione uma lista primeiro.");
            return;
        }
        System.out.print("Digite o status (TODO, DOING, DONE): ");
        String statusInput = scanner.nextLine().toUpperCase();
        try {
            Status status = Status.valueOf(statusInput);
            List<Task> tarefas = service.buscarTarefasPorStatus(listaAtiva, status);
            for(Task t : tarefas){
                System.out.println(t);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Status inválido. Tente novamente.");
        }
    }
}
