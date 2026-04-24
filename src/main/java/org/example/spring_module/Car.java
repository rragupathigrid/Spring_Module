package org.example.spring_module;

public class Car implements Vehicle{

  private final String type;

  public Car(String type){
    System.out.println("Car constructor is calling");
    this.type = type;
  }

  @Override
  public void print() {
    System.out.println("Car is running "+type);
  }

}
