package com.compass.msclaudit.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.compass.msclaudit.feignclients.response.Order;

@Repository
public interface AuditRepository extends MongoRepository<Order, String> {

}
