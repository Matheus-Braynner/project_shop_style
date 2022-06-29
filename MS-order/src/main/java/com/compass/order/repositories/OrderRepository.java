package com.compass.order.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compass.order.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
