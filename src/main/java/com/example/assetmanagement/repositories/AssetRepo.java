package com.example.assetmanagement.repositories;

import com.example.assetmanagement.models.Asset;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssetRepo extends JpaRepository<Asset, Long> {

    @Override
    Asset save(Asset entity);

    @Override
    List<Asset> findAll();

    @Override
    Optional<Asset> findById(Long aLong);

    @Override
    void deleteById(Long aLong);

    Asset findByName(String name);
}
