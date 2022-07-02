package com.example.assetmanagement.services;

import com.example.assetmanagement.dtos.CategoryDTO;
import com.example.assetmanagement.models.Category;
import com.example.assetmanagement.repositories.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public Category addCategory(String name, String description){

        Category category = new Category();
        category.setName(name);
        category.setDescription(description);

        return categoryRepo.save(category);
    }

    public Category updateCategory(Long id, CategoryDTO categoryDTO){
        Optional<Category> option = categoryRepo.findById(id);

        if(!option.isPresent()){
            throw new RuntimeException("invalid id");
        }

        Category category = option.get();

        if(categoryDTO.getName() != null){
            category.setName(categoryDTO.getName());
        }

        if(categoryDTO.getDescription() != null){
            category.setDescription(categoryDTO.getDescription());
        }

        return categoryRepo.save(category);
    }

    public List<Category> getAllCategory(){
        return categoryRepo.findAll();
    }
}
