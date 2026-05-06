package org.example.spring_module;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class App {

  private final Car car;
  private final Bike bike1;

  public App(Car car, @Qualifier("bike1") Bike bike1) {
    this.car   = car;
    this.bike1 = bike1;
  }

  public void show() {
    car.print();
    bike1.print();
  }
}