package org.example.spring_module.controller;

import org.example.spring_module.entity.car.CarEntity;
import org.example.spring_module.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

  private final CarService service;

  public CarController(CarService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<CarEntity>> getAll() {
    return ResponseEntity.ok(service.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<CarEntity> getById(@PathVariable Long id) {
    return ResponseEntity.ok(service.getById(id));
  }

  @PostMapping
  public ResponseEntity<CarEntity> create(@RequestBody CarEntity car) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.save(car));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}