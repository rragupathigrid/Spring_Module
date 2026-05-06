package org.example.spring_module.entity.car;

import jakarta.persistence.*;

@Entity
@Table(name = "cars")
public class CarEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  public CarEntity() {}
  public CarEntity(String name) { this.name = name; }

  public Long getId()             { return id; }
  public String getName()         { return name; }
  public void setId(Long id)      { this.id = id; }
  public void setName(String name){ this.name = name; }
}