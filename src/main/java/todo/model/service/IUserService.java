package todo.model.service;

import todo.model.entity.User;

public interface IUserService {
    public int createUser(User user);
    public User retrieveUser(String username);
    User getUserById(int id);
    public int authenticateUser(String username, String password);
}
