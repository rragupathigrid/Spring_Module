package org.example.spring_module.service;

import org.example.spring_module.entity.car.CarEntity;
import org.example.spring_module.repository.car.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

  private final CarRepository repository;

  public CarService(CarRepository repository) {
    this.repository = repository;
  }

  public List<CarEntity> getAll() {
    return repository.findAll();
  }

  public CarEntity getById(Long id) {
    return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Car not found: " + id));
  }

  public CarEntity save(CarEntity car) {
    return repository.save(car);
  }

  public void delete(Long id) {
    repository.deleteById(id);
  }
}