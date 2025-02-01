// ICategoryDAO.java
package todo.model.dao;

import todo.model.entity.Category;
import java.util.List;

public interface ICategoryDAO {
    int createCategory(Category category);
    Category retrieveCategoryById(int id); // Nome modificado
    List<Category> retrieveCategoriesByUser(int userId);
    int updateCategory(Category category);
    int deleteCategory(int id);
}