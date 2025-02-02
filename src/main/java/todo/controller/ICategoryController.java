package todo.controller;

import todo.model.entity.Category;
import java.util.List;

public interface ICategoryController {
    int createCategory(String name, int userId);
    Category getCategory(int id);
    List<Category> getCategoriesByUser(int userId);
    int updateCategory(int id, String newName, int userId);
    int deleteCategory(int id);
}