package todo.model.service;

import todo.model.entity.Category;
import java.util.List;

public interface ICategoryService {
    Category createCategory(Category category);
    Category getCategoryById(int id);
    List<Category> getCategoriesByUser(int userId);
    Category updateCategory(Category category);
    boolean deleteCategory(int id);
}