package todo;

import todo.controller.CategoryController;
import todo.controller.ICategoryController;
import todo.controller.IUserController;
import todo.controller.UserController;
import todo.model.dao.CategoryDAO;
import todo.model.dao.UserDAO;
import todo.model.service.CategoryService;
import todo.model.service.UserService;
import todo.view.RetrieveUserView;
import todo.view.UserView;

public class Main {
    public static void main(String[] args) {
        IUserController userController = new UserController(new UserService(new UserDAO()));
        ICategoryController categoryController = new CategoryController(new CategoryService(new CategoryDAO(), new UserDAO()), new UserService(new UserDAO()));


        new RetrieveUserView(userController);
    }
}
