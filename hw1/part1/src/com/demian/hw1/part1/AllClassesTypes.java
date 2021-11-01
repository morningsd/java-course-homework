package com.demian.hw1.part1;

public class AllClassesTypes {

  private String innerName;
  private static String nestedName;

  public static void main(String[] args) {
    AllClassesTypes.Inner inner = new AllClassesTypes().new Inner();
    inner.setInnerName("inner");
    System.out.println("Hello, " + inner.getInnerName());

    AllClassesTypes.Nested nested = new AllClassesTypes.Nested();
    nested.setNestedName("nested");
    System.out.println("Hello, " + nested.getNestedName());

    LocalInterface local = getLocalInstance();
    local.sayHello();

    LocalInterface anonymous = getLocalAnonymousInstance();
    anonymous.sayHello();

  }

  private static LocalInterface getLocalInstance() {
    class Local implements LocalInterface {

      @Override
      public void sayHello() {
        System.out.println("Hello, local");
      }

    }

    return new Local();
  }

  private static LocalInterface getLocalAnonymousInstance() {
    return new LocalInterface() {
      @Override
      public void sayHello() {
        System.out.println("Hello, anonymous");
      }
    };
  }

  class Inner {

    public String getInnerName() {
      return innerName;
    }

    public void setInnerName(String innerName) {
      AllClassesTypes.this.innerName = innerName;
    }

  }

  static class Nested {

    public String getNestedName() {
      return nestedName;
    }

    public void setNestedName(String nestedName) {
      AllClassesTypes.nestedName = nestedName;
    }

  }
}
