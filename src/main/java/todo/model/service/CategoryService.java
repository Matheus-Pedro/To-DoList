package todo.model.service;

import todo.model.dao.ICategoryDAO;
import todo.model.dao.IUserDAO;
import todo.model.entity.Category;
import todo.model.entity.User;
import todo.model.service.ICategoryService;
import java.util.List;

public class CategoryService implements ICategoryService {

    private final ICategoryDAO categoryDAO;
    private final IUserDAO userDAO;

    public CategoryService(ICategoryDAO categoryDAO, IUserDAO userDAO) {
        this.categoryDAO = categoryDAO;
        this.userDAO = userDAO;
    }

    @Override
    public Category createCategory(Category category) {
        validateCategory(category);

        try {
            int generatedId = categoryDAO.createCategory(category);
            return categoryDAO.retrieveCategoryById(generatedId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create category", e);
        }
    }

    @Override
    public Category getCategoryById(int id) {
        try {
            Category category = categoryDAO.retrieveCategoryById(id);
            if(category == null) {
                throw new IllegalArgumentException("Category not found with ID: " + id);
            }
            return category;
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve category", e);
        }
    }

    @Override
    public List<Category> getCategoriesByUser(int userId) {
        validateUserExists(userId);

        try {
            return categoryDAO.retrieveCategoriesByUser(userId);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve categories for user ID: " + userId, e);
        }
    }

    @Override
    public Category updateCategory(Category category) {
        validateCategory(category);
        getCategoryById(category.getId()); // Verifica se existe

        try {
            categoryDAO.updateCategory(category);
            return categoryDAO.retrieveCategoryById(category.getId());
        } catch (Exception e) {
            throw new RuntimeException("Failed to update category ID: " + category.getId(), e);
        }
    }

    @Override
    public boolean deleteCategory(int id) {
        getCategoryById(id); // Verifica se existe

        try {
            return categoryDAO.deleteCategory(id) > 0;
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete category ID: " + id, e);
        }
    }

    private void validateCategory(Category category) {
        if(category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }

        if(category.getName() == null || category.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Category name is required");
        }

        if(category.getUser() == null || category.getUser().getId() == 0) {
            throw new IllegalArgumentException("User must be specified for category");
        }

        validateUserExists(category.getUser().getId());
    }

    private void validateUserExists(int userId) {
        User user = userDAO.retrieveUserById(userId);
        if(user == null) {
            throw new IllegalArgumentException("User not found with ID: " + userId);
        }
    }
}