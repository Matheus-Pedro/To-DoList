package todo.model.dao;

import todo.PostgresConnection;
import todo.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO implements IUserDAO {

    @Override
    public int createUser(User user) {
        try (Connection connection= PostgresConnection.getConnection()){
            String query = "INSERT INTO \"User\" (username, password, email) VALUES (?, ?, ?) RETURNING id";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.username);
            ps.setString(2, user.password);
            ps.setString(3, user.email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }

        } catch (SQLException e){
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return -1;
    }

    @Override
    public User retrieveUser(String username) {
        try (Connection connection = PostgresConnection.getConnection()) {
            String query = "SELECT id, username, password, email, is_premium FROM \"User\" WHERE username = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao recuperar o usuário: " + e.getMessage());
        }
        return null;
    }

    @Override
    public User retrieveUserById(int id) {
        try (Connection connection = PostgresConnection.getConnection()) {
            String query = "SELECT id, username, password, email, is_premium FROM \"User\" WHERE username = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email")
                );
            }
        } catch (SQLException e) {
            System.err.println("Erro ao recuperar o usuário: " + e.getMessage());
        }
        return null;
    }

    @Override
    public int updateUser(User user) {
        try (Connection connection = PostgresConnection.getConnection()) {
            String query = "UPDATE \"User\" SET password = ?, email = ? WHERE username = ? RETURNING id";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.password);
            ps.setString(2, user.email);
            ps.setString(3, user.username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id"); // Retorna o ID do usuário atualizado
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar o usuário: " + e.getMessage());
        }
        return -1;
    }

    @Override
    public int deleteUser(String username) {
        try (Connection connection = PostgresConnection.getConnection()) {
            String query = "DELETE FROM \"User\" WHERE username = ? RETURNING id";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id"); // Retorna o ID do usuário deletado
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar o usuário: " + e.getMessage());
        }
        return -1;
    }
}
