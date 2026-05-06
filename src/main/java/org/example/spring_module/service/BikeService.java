package org.example.spring_module.service;

import org.example.spring_module.entity.bike.BikeEntity;
import org.example.spring_module.repository.bike.BikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeService {

  private final BikeRepository repository;

  public BikeService(BikeRepository repository) {
    this.repository = repository;
  }

  public List<BikeEntity> getAll() {
    return repository.findAll();
  }

  public BikeEntity getById(Long id) {
    return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Bike not found: " + id));
  }

  public BikeEntity save(BikeEntity bike) {
    return repository.save(bike);
  }

  public void delete(Long id) {
    repository.deleteById(id);
  }
}