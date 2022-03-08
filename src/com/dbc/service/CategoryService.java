package com.dbc.service;

import com.dbc.entities.Category;
import com.dbc.repository.CategoryRepository;

public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService() {
        categoryRepository = new CategoryRepository();
    }

    public void addCategory(Category category) {
        try {
            if (categoryRepository.nameAlreadyExists(category.getName().toLowerCase())) {
                throw new Exception("Nome de categoria já existente!");
            }

            Category addedCategory = categoryRepository.add(category);
            System.out.println("Categoria adicionada com sucesso! " + addedCategory);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
