package com.accesadades.botiga.Service;

import java.util.Set;

import com.accesadades.botiga.Model.Category;

public interface CategoryService {

    Set<Category> findAllCategories();

    Category findCategoriesByName(String name);

    void saveCategory(Category category);

    void deleteCategory(Category category);
}