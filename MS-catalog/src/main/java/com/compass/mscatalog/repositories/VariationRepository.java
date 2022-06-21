package com.compass.mscatalog.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.compass.mscatalog.entities.Variation;

@Repository
public interface VariationRepository extends MongoRepository<Variation, Long> {

}
