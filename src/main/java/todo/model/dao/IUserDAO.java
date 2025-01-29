package todo.model.dao;

import todo.model.entity.User;

import java.sql.ResultSet;

public interface IUserDAO {
    public int createUser(User user);
    public User retrieveUser(String username);
    public User retrieveUserById(int id);
    public int updateUser(User user);
    public int deleteUser(String username);
}
