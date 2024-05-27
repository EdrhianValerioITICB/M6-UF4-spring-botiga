package com.accesadades.botiga.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.Set;

import com.accesadades.botiga.Model.Subcategory;

@Repository
public interface SubcategoryRepository extends CrudRepository<Subcategory, Long> {

    @Override
    @NonNull
    Set<Subcategory> findAll();

    Subcategory findByName(String name);
}