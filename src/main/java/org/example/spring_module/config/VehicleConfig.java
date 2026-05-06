package org.example.spring_module.config;

import org.example.spring_module.Bike;
import org.example.spring_module.Car;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class VehicleConfig {

  @Value("${vehicle.car.type}")
  private String carType;

  @Value("${vehicle.bike.type}")
  private String bikeType;

  @Bean
  @Primary
  public Car car() {
    return new Car(carType);
  }


  @Bean("slowCar")
  public Car slowCar() {
    return new Car("Slow " + carType);
  }

  @Bean
  @Primary
  public Bike bike() {
    return new Bike(bikeType);
  }


  @Bean("bike1")
  public Bike bike1() {
    return new Bike("Normal " + bikeType);
  }
}