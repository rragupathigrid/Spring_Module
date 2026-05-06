package org.example.spring_module.entity.bike;

import jakarta.persistence.*;

@Entity
@Table(name = "bikes")
public class BikeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String name;

  public BikeEntity() {}
  public BikeEntity(String name) { this.name = name; }

  public Long getId()             { return id; }
  public String getName()         { return name; }
  public void setId(Long id)      { this.id = id; }
  public void setName(String name){ this.name = name; }
}