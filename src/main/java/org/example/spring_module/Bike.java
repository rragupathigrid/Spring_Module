package org.example.spring_module;

public class Bike implements Vehicle{

  private final String type;

  public Bike(String type){
    System.out.println("Bike constructor is calling");
    this.type = type ;
  }

  public void print() {
    System.out.println(type+" bike is running");
  }
}
