package com.compass.order.repositories;

import java.util.Collection;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.compass.order.entities.Order;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

	Collection<Order> findByCustomerId(Long id);

}
