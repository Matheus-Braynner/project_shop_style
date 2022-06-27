package com.compass.mscatalog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.compass.mscatalog.entities.Media;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {

}
