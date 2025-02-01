package todo.controller;

import todo.model.entity.User;
import todo.model.service.Email;
import todo.model.service.IUserService;

public class UserController implements IUserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public int createUser(String name, String password, String email) {
        try {
            User user = new User(name, password, email);
            int userId = userService.createUser(user);  // Chama o serviço para criar o usuário

            System.out.println("Usuário criado com ID: " + userId);
            return userId;
        } catch (IllegalArgumentException e) {
            if (e.getMessage().contains("E-mail inválido")) {
                return -2;  // E-mail inválido
            }
            return -1;
        } catch (RuntimeException e) {
            return -1;
        }
    }

    @Override
    public User retrieveUser(String username) {
        try {
            return userService.retrieveUser(username);
        } catch (RuntimeException e) {
            return null;
        }
    }

}
