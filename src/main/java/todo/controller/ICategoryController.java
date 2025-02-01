package todo.controller;

import todo.model.entity.Category;
import java.util.List;

public interface ICategoryController {
    // Cria uma nova categoria associada a um usuário
    int createCategory(String name, int userId);

    // Recupera uma categoria pelo ID
    Category getCategory(int id);

    // Lista todas as categorias de um usuário
    List<Category> getCategoriesByUser(int userId);

    // Atualiza uma categoria existente
    boolean updateCategory(int id, String newName, int userId);

    // Remove uma categoria
    boolean deleteCategory(int id);
}