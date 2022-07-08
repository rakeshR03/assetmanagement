package com.example.assetmanagement.controllers;

import com.example.assetmanagement.dtos.CategoryDTO;
import com.example.assetmanagement.dtos.CategoryResponseDTO;
import com.example.assetmanagement.models.Category;
import com.example.assetmanagement.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/categories")
    public ResponseEntity<Object> addCategory(@RequestBody CategoryDTO categoryDTO){
        return categoryService.addCategory(categoryDTO.getName(), categoryDTO.getDescription());

    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Object> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO){
        return categoryService.updateCategory(id, categoryDTO);
    }

    @GetMapping("/categories")
    public ResponseEntity<Object> findAll(){
        return categoryService.getAllCategory();
    }
}
