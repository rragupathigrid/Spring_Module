package org.example.spring_module.config;

import org.example.spring_module.Bike;
import org.example.spring_module.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@org.springframework.context.annotation.Configuration
public class Configuration {

  @Bean
  @Primary
  public Car car(){
    return new Car("Fast");
  }

  @Bean
  public Car car1(){
    return new Car("slow");
  }

  @Bean
  public Bike bike(){
    return new Bike("race");
  }

  @Bean
  public Bike bike1(){
    return new Bike("normal");
  }
}
