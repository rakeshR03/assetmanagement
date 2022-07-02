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
        Asset asset = assetService.addAsset(assetDTO);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().
                path("/{id}").buildAndExpand(asset.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/assets/{id}")
    public ResponseEntity<Object> deleteAsset(@PathVariable long id){
        int res = assetService.deleteAsset(id);
        if(res == 1){
            return ResponseEntity.status(HttpStatus.OK).body("1 row deleted");
        }
        else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).
                    body("cannot delete, asset in assigned state");
        }
    }

    @GetMapping("/assets")
    public List<AssetResponseDTO> findAllAsset(){
        List<Asset> assets = assetService.findAllAsset();
        List<AssetResponseDTO> assetResponseDTOList = new ArrayList<>();

        for(Asset asset: assets){
            AssetResponseDTO assetResponseDTO = new AssetResponseDTO();
            assetResponseDTO.setId(asset.getId());
            assetResponseDTO.setName(asset.getName());
            assetResponseDTO.setPurchaseDate(asset.getPurchaseDate());
            assetResponseDTO.setConditionNote(asset.getConditionNote());
            assetResponseDTO.setAssignment_status(asset.getAssignment_status());
            assetResponseDTO.setCategory(asset.getCategory());
            assetResponseDTO.setEmployee(asset.getEmployee());

            assetResponseDTOList.add(assetResponseDTO);
        }
        return assetResponseDTOList;
    }

    @GetMapping("/assets/{name}")
    public ResponseEntity<Object> findAssetByName(@PathVariable String name){
        Asset asset = assetService.findAssetByName(name);

        AssetResponseDTO assetResponseDTO = new AssetResponseDTO();
        assetResponseDTO.setId(asset.getId());
        assetResponseDTO.setName(asset.getName());
        assetResponseDTO.setPurchaseDate(asset.getPurchaseDate());
        assetResponseDTO.setConditionNote(asset.getConditionNote());
        assetResponseDTO.setAssignment_status(asset.getAssignment_status());
        assetResponseDTO.setCategory(asset.getCategory());
        assetResponseDTO.setEmployee(asset.getEmployee());

        return ResponseEntity.status(HttpStatus.OK).body(assetResponseDTO);

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
