import Controller.TodoListController;
import Service.TodoListService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        TodoListService todoListService = new TodoListService();
        TodoListController todoListController = new TodoListController(todoListService);

        todoListController.menu();
    }
}