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
        try {
            int id = userDAO.createUser(user);
            return id;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar usuário", e);
        }
    }
}
