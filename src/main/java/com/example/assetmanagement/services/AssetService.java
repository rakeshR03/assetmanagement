package com.example.assetmanagement.services;

import com.example.assetmanagement.dtos.AssetDTO;
import com.example.assetmanagement.dtos.AssetResponseDTO;
import com.example.assetmanagement.models.Asset;
import com.example.assetmanagement.models.Assignment_status;
import com.example.assetmanagement.models.Employee;
import com.example.assetmanagement.repositories.AssetRepo;
import com.example.assetmanagement.repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssetService {

    @Autowired
    private AssetRepo assetRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    public Asset addAsset(AssetDTO assetDTO){
        Asset asset = new Asset();
        asset.setName(assetDTO.getName());
        asset.setPurchaseDate(assetDTO.getPurchaseDate());
        asset.setConditionNote(assetDTO.getConditionNote());
        asset.setAssignment_status(Assignment_status.AVAILABLE.name());
        asset.setEmployee(null);
        asset.setCategory(assetDTO.getCategory());

        return assetRepo.save(asset);
    }

    public List<Asset> findAllAsset(){
        return assetRepo.findAll();
    }

    public int deleteAsset(long id){

        Optional<Asset> optional = assetRepo.findById(id);
        if(!optional.isPresent()){
            throw new RuntimeException("invalid id");
        }

        Asset asset = optional.get();

        if(!asset.getAssignment_status().equalsIgnoreCase(Assignment_status.ASSIGNED.name())){
            assetRepo.deleteById(id);
            return 1;
        }
        return -1; // -1, if cannot be deleted
    }

    public Asset findAssetByName(String name){
        return assetRepo.findByName(name);
    }

    public ResponseEntity<Object> assignAsset(long assetId, long employeeId){

        Optional<Asset> optional = assetRepo.findById(assetId);
        if(!optional.isPresent()){
            throw new RuntimeException("invalid asset id");
        }

        Optional<Employee> optionalEmployee = employeeRepo.findById(employeeId);
        if(!optionalEmployee.isPresent()){
            throw new RuntimeException("invalid employee id");
        }
        Asset asset = optional.get();
        asset.setAssignment_status(Assignment_status.ASSIGNED.name());
        asset.setEmployee(optionalEmployee.get());

        Asset savedAsset = assetRepo.save(asset);
        AssetResponseDTO assetResponseDTO = new AssetResponseDTO();
        assetResponseDTO.setId(savedAsset.getId());
        assetResponseDTO.setName(savedAsset.getName());
        assetResponseDTO.setConditionNote(savedAsset.getConditionNote());
        assetResponseDTO.setAssignment_status(savedAsset.getAssignment_status());
        assetResponseDTO.setCategory(savedAsset.getCategory());
        assetResponseDTO.setEmployee(savedAsset.getEmployee());

        return  ResponseEntity.status(HttpStatus.OK).body("asset assigned");
    }

    public ResponseEntity<Object> updateAsset(long id, AssetDTO assetDTO){
        try{
            Optional<Asset> optional = assetRepo.findById(id);
            if(!optional.isPresent()){
                throw new RuntimeException("invalid asset id");
            }

            Asset asset = optional.get();
            if(assetDTO.getName() != null){
                asset.setName(assetDTO.getName());
            }

            if(assetDTO.getCategory() != null){
                asset.setCategory(assetDTO.getCategory());
            }

            if(assetDTO.getConditionNote() != null){
                asset.setConditionNote(assetDTO.getConditionNote());
            }

            if(assetDTO.getPurchaseDate() != null){
                asset.setPurchaseDate(assetDTO.getPurchaseDate());
            }

            if(assetDTO.getAssignment_status() != null){
                asset.setAssignment_status(assetDTO.getAssignment_status());
            }
            assetRepo.save(asset);

            return ResponseEntity.status(HttpStatus.OK).body("asset updated");
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while updating");
        }
    }

    public ResponseEntity<Object> recoverAsset(long assetId){
        Optional<Asset> optional = assetRepo.findById(assetId);
        if(!optional.isPresent()){
            throw new RuntimeException("invalid asset id");
        }

        Asset asset = optional.get();
        if(!asset.getAssignment_status().equalsIgnoreCase( Assignment_status.ASSIGNED.name())){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Asset is not in assigned state");
        }
        asset.setEmployee(null);
        asset.setAssignment_status(Assignment_status.RECOVERED.name());

        Asset savedAsset = assetRepo.save(asset);

        return ResponseEntity.status(HttpStatus.OK).body("Asset is recovered");
    }
}
