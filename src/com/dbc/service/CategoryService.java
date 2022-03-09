package com.dbc.service;

import com.dbc.entities.Category;
import com.dbc.repository.CategoryRepository;

import java.sql.SQLException;

public class CategoryService {

    private CategoryRepository categoryRepository;

    public static void main(String[] args) {
        //TODO - tem erro
        CategoryService categoryService = new CategoryService();
        categoryService.remove(11);

        categoryService.list();
    }

    public CategoryService() {
        categoryRepository = new CategoryRepository();
    }

    public void add(Category category) {
        try {
            Category addedCategory = categoryRepository.add(category);
            System.out.println("Categoria adicionada com sucesso! " + addedCategory);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remove(Integer id) {
        try {
            boolean isRemoved = categoryRepository.remove(id);
            System.out.println("removido? " + isRemoved + "| com id=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Integer id, Category category) {
        try {
            boolean isUpdated = categoryRepository.update(id, category);
            System.out.println("editado? " + isUpdated + "| com id=" + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void list() {
        try {
            categoryRepository.list().forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
