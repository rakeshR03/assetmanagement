package com.example.assetmanagement.repositories;

import com.example.assetmanagement.models.Category;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

    @Override
    Category save(Category entity);

    @Override
    Optional<Category> findById(Long aLong);

    @Override
    List<Category> findAll();
}
