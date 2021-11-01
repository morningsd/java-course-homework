package com.demian.hw1.part1;

public class AnonymousLambdaTypes {

  public static void main(String[] args) {
    LocalInterface local1 = getAnonymousInstance();
    local1.sayHello();

    LocalInterface local2 = getLambdaInstance();
    local2.sayHello();
  }

  private static LocalInterface getAnonymousInstance() {
      return new LocalInterface() {
        @Override
        public void sayHello() {
          System.out.println("Hello from anonymous class");
        }
      };
  }

  private static LocalInterface getLambdaInstance() {
    return () -> {
      System.out.println("Hello from lambda expression");
    };
  }
}
