package todo.controller;

import todo.model.entity.Category;
import todo.model.entity.User;
import todo.model.service.ICategoryService;
import todo.model.service.IUserService;
import java.util.List;

public class CategoryController implements ICategoryController {
    private final ICategoryService categoryService;
    private final IUserService userService;

    public CategoryController(ICategoryService categoryService, IUserService userService) {
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @Override
    public int createCategory(String name, int userId) {
        try {
            // Validações básicas
            if(name == null || name.trim().isEmpty()) {
                return -2; // Nome inválido
            }

            User user = userService.getUserById(userId);
            if(user == null) {
                return -3; // Usuário não encontrado
            }

            Category category = new Category();
            category.setName(name.trim());
            category.setUser(user);

            int categoryId = categoryService.createCategory(category).getId();
            System.out.println("Categoria criada com ID: " + categoryId);
            return categoryId;

        } catch (IllegalArgumentException e) {
            return -1; // Erro geral
        } catch (RuntimeException e) {
            return -1; // Erro geral
        }
    }

    @Override
    public Category getCategory(int id) {
        try {
            return categoryService.getCategoryById(id);
        } catch (RuntimeException e) {
            System.err.println("Erro ao buscar categoria: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Category> getCategoriesByUser(int userId) {
        try {
            return categoryService.getCategoriesByUser(userId);
        } catch (RuntimeException e) {
            System.err.println("Erro ao listar categorias: " + e.getMessage());
            return List.of(); // Retorna lista vazia em caso de erro
        }
    }

    @Override
    public int updateCategory(int id, String newName, int userId) {
        try {
            Category category = categoryService.getCategoryById(id);
            if(category == null) {
                return -2; // Categoria não encontrada
            }

            if(category.getUser().getId() != userId) {
                return -3; // Usuário não autorizado
            }

            if(newName == null || newName.trim().isEmpty()) {
                return -4; // Novo nome inválido
            }

            category.setName(newName.trim());
            categoryService.updateCategory(category);
            return 1; // Sucesso

        } catch (RuntimeException e) {
            return -1; // Erro geral
        }
    }

    @Override
    public int deleteCategory(int id) {
        try {
            boolean success = categoryService.deleteCategory(id);
            return success ? 1 : 0; // 1 = Sucesso, 0 = Falha
        } catch (RuntimeException e) {
            return -1; // Erro geral
        }
    }
}