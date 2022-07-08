package com.example.assetmanagement.services;

import com.example.assetmanagement.dtos.CategoryDTO;
import com.example.assetmanagement.dtos.CategoryResponseDTO;
import com.example.assetmanagement.models.Category;
import com.example.assetmanagement.repositories.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public ResponseEntity<Object> addCategory(String name, String description){

        try{
            Category category = new Category();
            category.setName(name);
            category.setDescription(description);

            Category  savedCategory = categoryRepo.save(category);
            return ResponseEntity.status(HttpStatus.OK).body(savedCategory);
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("cannot add category");
        }
    }

    public ResponseEntity<Object> updateCategory(Long id, CategoryDTO categoryDTO){
        try{
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

            Category  savedCategory= categoryRepo.save(category);

            return ResponseEntity.status(HttpStatus.OK).body(savedCategory);
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("cannot update category");
        }
    }

    public ResponseEntity<Object> getAllCategory(){

        try{
            List<Category> categoryList = categoryRepo.findAll();

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
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("cannot fetch data");
        }
    }
}
