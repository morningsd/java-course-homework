package com.demian.hw1.part2oop.player;

import com.demian.hw1.part2oop.TicTacToe;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PersonPlayer extends Player {

  private final Scanner scanner = new Scanner(System.in);

  public PersonPlayer(TicTacToe ticTacToe, char playerCharacter) {
    super(playerCharacter, ticTacToe);
  }

  @Override
  public int chooseNextMove() {
    System.out.println("Choose your square (" + getPlayerCharacter() + "): ");
    int coordinate;
    do {
      try {
        coordinate = scanner.nextInt();
      } catch (InputMismatchException ime) {
        System.out.println("Incorrect input. Try again.");
        scanner.next();
        continue;
      }

      if ((coordinate < 1) || (coordinate > 9)) {
        System.out.println("Wrong coordinate...");
        continue;
      }
      break;
    } while (true);
    return coordinate - 1;
  }
}
