package com.compass.msaudit.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.compass.msaudit.entities.Order;

@Repository
public interface AuditRepository extends MongoRepository<Order, String> {

}
