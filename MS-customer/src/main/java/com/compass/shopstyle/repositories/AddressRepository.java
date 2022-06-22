package com.compass.shopstyle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compass.shopstyle.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
