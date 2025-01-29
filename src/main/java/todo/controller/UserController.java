package todo.controller;

import todo.model.entity.User;
import todo.model.service.IUserService;

public class UserController implements IUserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public int createUser(String name, String password, String email) {
        if (name == null || name.isEmpty() || email == null || email.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Nome e e-mail são obrigatórios!");
        }

        User user = new User(name, password, email);
        int userId = userService.createUser(user);
        System.out.println("Usuário criado com ID: " + userId);
        return userId;
    }
}
