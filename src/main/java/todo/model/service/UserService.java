package todo.model.service;

import todo.model.dao.IUserDAO;
import todo.model.dao.UserDAO;
import todo.model.entity.User;

public class UserService implements IUserService {
    private final IUserDAO userDAO;

    public UserService(IUserDAO userDAO){
        this.userDAO = userDAO;
    }

    @Override
    public int createUser(User user) {
        if (user.getName() == null || user.getName().isEmpty() ||
                user.getEmail() == null || user.getEmail().isEmpty() ||
                user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Nome, e-mail e senha são obrigatórios!");
        }

        Email emailValidator = new Email(user.getEmail());
        if (!emailValidator.validateEmail()) {
            throw new IllegalArgumentException("E-mail inválido!");
        }

        try {
            return userDAO.createUser(user);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar usuário", e);
        }
    }

    @Override
    public User retrieveUser(String username) {
        try {
            return userDAO.retrieveUser(username);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao encontrar usuário", e);
        }
    }

    @Override
    public int authenticateUser(String username, String password) {
        return 0;
    }
}
