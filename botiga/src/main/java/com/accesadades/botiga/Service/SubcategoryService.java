package com.accesadades.botiga.Service;

import java.util.Set;

import com.accesadades.botiga.Model.Subcategory;

public interface SubcategoryService {

    Set<Subcategory> findAllSubcategories();

    Subcategory findSubcategoriesByName(String name);

    void saveSubcategory(Subcategory subcategory);

    void deleteSubcategory(Subcategory subcategory);
}