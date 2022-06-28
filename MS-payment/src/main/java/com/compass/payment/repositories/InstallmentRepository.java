package com.compass.payment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compass.payment.entities.Installment;

@Repository
public interface InstallmentRepository extends JpaRepository<Installment, Long> {

}
