package org.example.spring_module.controller;

import org.example.spring_module.entity.bike.BikeEntity;
import org.example.spring_module.service.BikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bikes")
public class BikeController {

  private final BikeService service;

  public BikeController(BikeService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<List<BikeEntity>> getAll() {
    return ResponseEntity.ok(service.getAll());
  }

  @GetMapping("/{id}")
  public ResponseEntity<BikeEntity> getById(@PathVariable Long id) {
    return ResponseEntity.ok(service.getById(id));
  }

  @PostMapping
  public ResponseEntity<BikeEntity> create(@RequestBody BikeEntity bike) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.save(bike));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}