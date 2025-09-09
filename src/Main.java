import Controller.TodoListController;
import Service.TodoListService;


public class Main {
    public static void main(String[] args) {
        TodoListService todoListService = new TodoListService();
        TodoListController todoListController = new TodoListController(todoListService);

        todoListController.menu();
    }
}