package todo.controller;

import todo.model.entity.User;

public interface IUserController {
    public int createUser(String name, String password, String email);
}
