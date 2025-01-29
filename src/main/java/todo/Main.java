package todo;

import todo.controller.IUserController;
import todo.controller.UserController;
import todo.model.dao.UserDAO;
import todo.model.service.UserService;
import todo.view.UserView;

public class Main {
    public static void main(String[] args) {
        IUserController userController = new UserController(new UserService(new UserDAO()));

        new UserView(userController);
    }
}
