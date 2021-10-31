package com.demian.hw1.part1;

import org.apache.commons.lang3.StringUtils;

public class Main {

  public static void main(String[] args) {
    Second s = new Second();
    s.printHello("world");

    System.out.println(StringUtils.upperCase("some lowercase string..."));
  }

}
