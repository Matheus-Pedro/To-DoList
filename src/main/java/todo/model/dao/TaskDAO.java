package todo.model.dao;

import todo.PostgresConnection;
import todo.model.entity.Task;
import todo.model.entity.User;
import todo.model.entity.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO implements ITaskDAO {

    @Override
    public int createTask(Task task) {
        try (Connection connection = PostgresConnection.getConnection()) {
            String query = "INSERT INTO Task (title, description, due_date, status, user_id, category_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?) RETURNING id";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, task.getTitle());
            ps.setString(2, task.getDescription());

            // Handle nullable dueDate
            if(task.getDueDate() != null) {
                ps.setDate(3, new java.sql.Date(task.getDueDate().getTime()));
            } else {
                ps.setNull(3, Types.DATE);
            }

            ps.setString(4, task.getStatus());

            // Validação de User obrigatório
            if(task.getUser() == null) {
                throw new SQLException("User must be specified for the task");
            }
            ps.setInt(5, task.getUser().getId());

            // Handle nullable category
            if(task.getCategory() != null) {
                ps.setInt(6, task.getCategory().getId());
            } else {
                ps.setNull(6, Types.INTEGER);
            }

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao criar tarefa: " + e.getMessage());
        }
        return -1;
    }

    @Override
    public Task retrieveTask(int taskId) {
        try (Connection connection = PostgresConnection.getConnection()) {
            String query = "SELECT * FROM Task WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, taskId);

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));

                // Handle nullable dueDate
                Date dueDate = rs.getDate("due_date");
                task.setDueDate(dueDate != null ? new Date(dueDate.getTime()) : null);

                task.setStatus(rs.getString("status"));

                // Recupera User
                UserDAO userDAO = new UserDAO();
                int userId = rs.getInt("user_id");
                User user = userDAO.retrieveUserById(userId);
                if(user == null) {
                    throw new SQLException("User not found for ID: " + userId);
                }
                task.setUser(user);

                // Handle nullable category
                int categoryId = rs.getInt("category_id");
                if(!rs.wasNull()) {
                    CategoryDAO categoryDAO = new CategoryDAO();
                    Category category = categoryDAO.retrieveCategoryById(categoryId);
                    task.setCategory(category);
                }

                return task;
            }
        } catch (SQLException e) {
            System.err.println("Erro ao recuperar tarefa: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Task> retrieveTasksByUser(int userId) {
        List<Task> tasks = new ArrayList<>();

        try (Connection connection = PostgresConnection.getConnection()) {
            // Otimização: busca o user uma única vez
            UserDAO userDAO = new UserDAO();
            User user = userDAO.retrieveUserById(userId);
            if(user == null) {
                System.err.println("Usuário não encontrado: " + userId);
                return tasks;
            }

            String query = "SELECT * FROM Task WHERE user_id = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setTitle(rs.getString("title"));
                task.setDescription(rs.getString("description"));

                // Handle nullable dueDate
                Date dueDate = rs.getDate("due_date");
                task.setDueDate(dueDate != null ? new Date(dueDate.getTime()) : null);

                task.setStatus(rs.getString("status"));
                task.setUser(user); // Usa o user já recuperado

                // Handle nullable category
                int categoryId = rs.getInt("category_id");
                if(!rs.wasNull()) {
                    CategoryDAO categoryDAO = new CategoryDAO();
                    task.setCategory(categoryDAO.retrieveCategoryById(categoryId));
                }

                tasks.add(task);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar tarefas: " + e.getMessage());
        }
        return tasks;
    }

    @Override
    public int updateTask(Task task) {
        try (Connection connection = PostgresConnection.getConnection()) {
            String query = "UPDATE Task SET title = ?, description = ?, due_date = ?, " +
                    "status = ?, user_id = ?, category_id = ? WHERE id = ? RETURNING id";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, task.getTitle());
            ps.setString(2, task.getDescription());

            // Handle nullable dueDate
            if(task.getDueDate() != null) {
                ps.setDate(3, new java.sql.Date(task.getDueDate().getTime()));
            } else {
                ps.setNull(3, Types.DATE);
            }

            ps.setString(4, task.getStatus());

            // Validação de User obrigatório
            if(task.getUser() == null) {
                throw new SQLException("User must be specified for the task");
            }
            ps.setInt(5, task.getUser().getId());

            // Handle nullable category
            if(task.getCategory() != null) {
                ps.setInt(6, task.getCategory().getId());
            } else {
                ps.setNull(6, Types.INTEGER);
            }

            ps.setInt(7, task.getId());

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar tarefa: " + e.getMessage());
        }
        return -1;
    }

    // Método deleteTask permanece inalterado
    @Override
    public int deleteTask(int taskId) {
        try (Connection connection = PostgresConnection.getConnection()) {
            String query = "DELETE FROM Task WHERE id = ? RETURNING id";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, taskId);

            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao deletar tarefa: " + e.getMessage());
        }
        return -1;
    }
}