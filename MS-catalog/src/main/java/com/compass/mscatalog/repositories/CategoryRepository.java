package com.compass.mscatalog.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.compass.mscatalog.entities.Category;

@Repository
public interface CategoryRepository extends MongoRepository<Category, Long> {

}
