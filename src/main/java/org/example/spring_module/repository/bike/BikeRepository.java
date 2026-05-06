package org.example.spring_module.repository.bike;

import org.example.spring_module.entity.bike.BikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BikeRepository extends JpaRepository<BikeEntity, Long> {
}