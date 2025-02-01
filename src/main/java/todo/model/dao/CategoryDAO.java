// CategoryDAO.java
package todo.model.dao;

import todo.PostgresConnection;
import todo.model.entity.Category;
import todo.model.entity.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements ICategoryDAO {

    @Override
    public int createCategory(Category category) {
        try (Connection connection = PostgresConnection.getConnection()) {
            String query = "INSERT INTO Category (name, user_id) VALUES (?, ?) RETURNING id";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, category.getName());
            ps.setInt(2, category.getUser().getId());

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao criar categoria: " + e.getMessage());
        }
        return -1;
    }

    @Override
    public Category retrieveCategoryById(int id) { // Nome corrigido
        try (Connection connection = PostgresConnection.getConnection()) {
            String query = "SELECT * FROM Category WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));

                UserDAO userDAO = new UserDAO();
                User user = userDAO.retrieveUserById(rs.getInt("user_id"));
                category.setUser(user);

                return category;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao recuperar categoria: " + e.getMessage());
        }
        return null;
    }
    @Override
    public List<Category> retrieveCategoriesByUser(int userId) {
        List<Category> categories = new ArrayList<>();
        try (Connection connection = PostgresConnection.getConnection()) {
            String query = "SELECT * FROM Category WHERE user_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));

                UserDAO userDAO = new UserDAO();
                category.setUser(userDAO.retrieveUserById(userId));

                categories.add(category);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar categorias: " + e.getMessage());
        }
        return categories;
    }

    @Override
    public int updateCategory(Category category) {
        try (Connection connection = PostgresConnection.getConnection()) {
            String query = "UPDATE Category SET name = ?, user_id = ? WHERE id = ? RETURNING id";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, category.getName());
            ps.setInt(2, category.getUser().getId());
            ps.setInt(3, category.getId());

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar categoria: " + e.getMessage());
        }
        return -1;
    }

    @Override
    public int deleteCategory(int id) {
        try (Connection connection = PostgresConnection.getConnection()) {
            String query = "DELETE FROM Category WHERE id = ? RETURNING id";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar categoria: " + e.getMessage());
        }
        return -1;
    }
}