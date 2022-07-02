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
        Category category = categoryService.addCategory(categoryDTO.getName(), categoryDTO.getDescription());
        return ResponseEntity.status(HttpStatus.OK).body(category);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<Object> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO){
        Category category = categoryService.updateCategory(id, categoryDTO);
        if (category != null) {
            return ResponseEntity.status(HttpStatus.OK).body(category);
        }
        else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("cannot update row");
        }
    }

    @GetMapping("/categories")
    public ResponseEntity<Object> findAll(){
        List<Category> categoryList= categoryService.getAllCategory();

        List<CategoryResponseDTO> categoryResponseDTOList = new ArrayList<>();
        for(Category x: categoryList){
            CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
            categoryResponseDTO.setId(x.getId());
            categoryResponseDTO.setName(x.getName());
            categoryResponseDTO.setDescription(x.getDescription());
            categoryResponseDTOList.add(categoryResponseDTO);
        }
        return ResponseEntity.status(HttpStatus.OK).body(categoryResponseDTOList);
    }
}
