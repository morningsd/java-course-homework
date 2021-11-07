package com.demian.hw1.part2oop;

import java.util.Arrays;

public class Field {

  private static final int FIELD_SIZE = 3;
  private static final String TABLE = "+---+---+---+\n"
      + "| 1 | 2 | 3 |\n"
      + "|---+---+---|\n"
      + "| 4 | 5 | 6 |\n"
      + "|---+---+---|\n"
      + "| 7 | 8 | 9 |\n"
      + "+---+---+---+\n";
  private final char[] currentFieldState = new char[9];

  public Field() {
    Arrays.fill(currentFieldState, ' ');
  }

  public boolean makeMove(char playerCharacter, int coordinate) {
    if (currentFieldState[coordinate] == ' ') {
      currentFieldState[coordinate] = playerCharacter;
      return true;
    } else {
      System.out.println("This square is busy. Repeat your move");
      return false;
    }
  }

  public void printCurrentFieldState() {
    String temporaryTable = TABLE;
    for (int i = 0; i < currentFieldState.length; i++) {
      temporaryTable = temporaryTable.replace(Character.forDigit(i + 1, 10), currentFieldState[i]);
    }
    System.out.print(temporaryTable);
  }

  public void printInitialTable() {
    System.out.println(TABLE);
  }

  public boolean checkIfGameOver(char[] fieldState, char currentPlayerCharacter) {
    if (checkWinner(fieldState, currentPlayerCharacter)) {
      System.out.println("Congratulations! " + currentPlayerCharacter + " won!");
      return true;
    } else if (checkDraw(fieldState)) {
      System.out.println("Draw! No empty fields left");
      return true;
    }
    return false;
  }

  public boolean checkDraw(char[] fieldState) {
    for (char square : fieldState) {
      if (square == ' ') {
        return false;
      }
    }
    return true;
  }

  public boolean checkWinner(char[] fieldState, char currentPlayerCharacter) {
    return (checkRows(fieldState, currentPlayerCharacter)
        || checkColumns(fieldState, currentPlayerCharacter)
        || checkDiagonals(fieldState, currentPlayerCharacter));
  }

  private boolean checkDiagonals(char[] fieldState, char currentPlayerCharacter) {
    return checkLine(fieldState, 0, 4, currentPlayerCharacter)
        || checkLine(fieldState, 2, 2, currentPlayerCharacter);
  }

  private boolean checkColumns(char[] fieldState, char currentPlayerCharacter) {
    for (int i = 0; i < FIELD_SIZE; i++) {
      if (checkLine(fieldState, i, 3, currentPlayerCharacter)) {
        return true;
      }
    }
    return false;
  }

  private boolean checkRows(char[] fieldState, char currentPlayerCharacter) {
    for (int i = 0; i < FIELD_SIZE; i++) {
      if (checkLine(fieldState, i * 3, 1, currentPlayerCharacter)) {
        return true;
      }
    }
    return false;
  }

  private boolean checkLine(char[] fieldState, int start, int step, char currentPlayerCharacter) {
    for (int i = 0; i < FIELD_SIZE; i++) {
      if (fieldState[start + step * i] != currentPlayerCharacter) {
        return false;
      }
    }
    return true;
  }

  public char[] getCurrentFieldState() {
    return currentFieldState;
  }
}
