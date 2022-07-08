package com.example.assetmanagement.controllers;

import com.example.assetmanagement.dtos.AssetDTO;
import com.example.assetmanagement.dtos.AssetResponseDTO;
import com.example.assetmanagement.models.Asset;
import com.example.assetmanagement.models.Employee;
import com.example.assetmanagement.services.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AssetController {

    @Autowired
    private AssetService assetService;

    @PostMapping("/assets")
    public ResponseEntity<Object> addAsset(@RequestBody AssetDTO assetDTO){
        return assetService.addAsset(assetDTO);

    }

    @DeleteMapping("/assets/{id}")
    public ResponseEntity<Object> deleteAsset(@PathVariable long id){
        return assetService.deleteAsset(id);

    }

    @GetMapping("/assets")
    public ResponseEntity<Object> findAllAsset(){
        return assetService.findAllAsset();

    }

    @GetMapping("/assets/{name}")
    public ResponseEntity<Object> findAssetByName(@PathVariable String name){
        return assetService.findAssetByName(name);


    }

    @PutMapping("/assets/{id}/assign")
    public ResponseEntity<Object> assignAsset(@PathVariable long id, @RequestBody Employee employee){
        long employeeId = employee.getId();
        return assetService.assignAsset(id, employeeId);


    }

    @PutMapping("/assets/{id}")
    public ResponseEntity<Object> updateAsset(@PathVariable long id, @RequestBody AssetDTO assetDTO){
        return assetService.updateAsset(id, assetDTO);

    }

    @PutMapping("/assets/{id}/recoverasset")
    public ResponseEntity<Object> recoverAsset(long assetId){
        return assetService.recoverAsset(assetId);
    }
}
