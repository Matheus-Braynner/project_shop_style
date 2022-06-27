package com.compass.mscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compass.mscatalog.entities.Sku;

@Repository
public interface SkuRepository extends JpaRepository<Sku, Long> {

}
