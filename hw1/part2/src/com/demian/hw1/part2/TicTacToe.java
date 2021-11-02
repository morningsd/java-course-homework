package com.demian.hw1.part2;

import java.util.Arrays;
import java.util.Scanner;

/**
 * This class contains implementation of Tic Tac Toe game
 * <p>Create an instance of this class and invoke method start() to play the game.
 * Follow this link to see the rules <a href="https://www.exploratorium.edu/brain_explorer/tictactoe.html">TicTacToe</a>
 * </p>
 *
 * @author Oleksii Krasnovskyi
 */
public class TicTacToe {

  private static final Scanner SCANNER = new Scanner(System.in);
  private static final char[] PLAYERS = {'X', 'O'};
  private static final int FIELD_SIZE = 3;
  private static int currentPlayerPos = 0;
  private static final char[] busySquares = new char[9];
  private static final String TABLE = "+---+---+---+\n"
      + "| 1 | 2 | 3 |\n"
      + "|---+---+---|\n"
      + "| 4 | 5 | 6 |\n"
      + "|---+---+---|\n"
      + "| 7 | 8 | 9 |\n"
      + "+---+---+---+\n";

  /**
   * Sole constructor.
   */
  public TicTacToe() {
    Arrays.fill(busySquares, ' ');
  }

  /**
   * Main method that starts the game.
   */
  public static void start() {
    printTable(new char[]{'1', '2', '3', '4', '5', '6', '7', '8', '9'});
    System.out.println("Welcome to the TicTacToe!\n"
        + "You can see coordinates of squares above.\n"
        + "To play against friend press 1, to play against computer press 2.\n\n"
        + "Note! Press Ctrl+c to stop the game.\n");
    int gameType;
    do {
      System.out.println("To play against friend press 1, to play against computer press 2.\n"
          + "Enter a number: ");
      gameType = SCANNER.nextInt();
      if (gameType == 1) {
        againstFriend();
        break;
      } else if (gameType == 2) {
        againstComputer();
        break;
      }
    } while (true);
  }

  private static void againstFriend() {
    int coordinate;
    boolean game = true;
    char currentPlayer;
    do {
      currentPlayer = PLAYERS[currentPlayerPos];
      System.out.println("Choose your square (" + PLAYERS[currentPlayerPos] + "): ");
      coordinate = SCANNER.nextInt();
      if ((coordinate < 1) || (coordinate > 9)) {
        System.out.println("Wrong coordinate...");
        continue;
      }

      if (!makeMove(coordinate - 1, currentPlayer)) {
        continue;
      }

      game = isGameContinues(currentPlayer);

      currentPlayerPos = (currentPlayerPos + 1) % 2;
    } while (game);
  }

  private static void againstComputer() {
    int playerChoice;
    do {
      System.out.println("To play as X press 1, to play as O press 2.\n"
          + "Enter a number: ");
      playerChoice = SCANNER.nextInt();
    } while ((playerChoice != 1) && (playerChoice != 2));
    char userPlayer = PLAYERS[playerChoice - 1];
    char currentPlayer;
    boolean game = true;
    int coordinate;
    do {
      currentPlayer = PLAYERS[currentPlayerPos];
      if (userPlayer == currentPlayer) {
        System.out.println("Choose your square (" + PLAYERS[currentPlayerPos] + "): ");
        coordinate = SCANNER.nextInt();
        if ((coordinate < 1) || (coordinate > 9)) {
          System.out.println("Wrong coordinate...");
          continue;
        }
        coordinate--;
      } else {
        coordinate = getCountedCoordinate(currentPlayerPos, Arrays.copyOf(busySquares, busySquares.length), 1).getKey();
      }
      if (!makeMove(coordinate, currentPlayer)) {
        continue;
      }

      game = isGameContinues(currentPlayer);

      currentPlayerPos = (currentPlayerPos + 1) % 2;
    } while (game);

  }

  private static boolean isGameContinues(char currentPlayer) {
    if (checkWinner(busySquares, currentPlayer)) {
      System.out.println("Congratulations! " + currentPlayer + " won!");
      return false;
    } else if (checkDraw(busySquares)) {
      System.out.println("Draw! No empty fields left");
      return false;
    }
    return true;
  }

  /*
    Trying to implement minimax algorithm.
    To see explanation go to https://habr.com/ru/post/329058/
   */
  private static Pair<Integer, Integer> getCountedCoordinate(int currentPlayerPos, char[] field, int coefficient) {
    int bestPosition = -1;
    int bestPositionScore = Integer.MIN_VALUE;
    int sum = 0;
    char currentPlayer = PLAYERS[currentPlayerPos];
    for (int i = 0; i < field.length; i++) {
      if (field[i] == ' ') {
        char[] nextMoveField = Arrays.copyOf(field, field.length);
        nextMoveField[i] = currentPlayer;
        int score;
        if (checkWinner(nextMoveField, currentPlayer)) {
          score = 10 * coefficient;
        } else if (checkDraw(nextMoveField)) {
          score = 0;
        } else {
          int newCurrentPlayerPos = (currentPlayerPos + 1) % 2;
          score = getCountedCoordinate(newCurrentPlayerPos, nextMoveField, -coefficient).getValue();
        }

        sum += score;
        if (bestPositionScore * coefficient < score * coefficient) {
          bestPositionScore = score;
          bestPosition = i;
        }

      }

    }
    return new Pair<>(bestPosition, sum);
  }

  private static boolean makeMove(int coordinate, char player) {
    if (busySquares[coordinate] != ' ') {
      System.out.println("This square is already busy, please choose another one.\n");
      return false;
    }

    busySquares[coordinate] = player;
    printTable(busySquares);
    return true;
  }

  private static boolean checkWinner(char[] field, char player) {
    return (checkRows(field, player) || checkColumns(field, player) || checkDiagonals(field, player));
  }

  private static boolean checkDraw(char[] field) {
    for (char square : field) {
      if (square == ' ') {
        return false;
      }
    }
    return true;
  }

  private static boolean checkDiagonals(char[] field, char player) {
    return checkLine(field, 0, 4, player) || checkLine(field, 2, 2, player);
  }

  private static boolean checkColumns(char[] field, char player) {
    for (int i = 0; i < FIELD_SIZE; i++) {
      if (checkLine(field, i, 3, player)) {
        return true;
      }
    }
    return false;
  }

  private static boolean checkRows(char[] field, char player) {
    for (int i = 0; i < FIELD_SIZE; i++) {
      if (checkLine(field, i * 3, 1, player)) {
        return true;
      }
    }
    return false;
  }

  private static boolean checkLine(char[] field, int start, int step, char player) {
    for (int i = 0; i < FIELD_SIZE; i++) {
      if (field[start + step * i] != player) {
        return false;
      }
    }
    return true;
  }

  private static void printTable(char[] busySquares) {
    String temporaryTable = TABLE;
    for (int i = 0; i < busySquares.length; i++) {
      temporaryTable = temporaryTable.replace(Character.forDigit(i + 1, 10), busySquares[i]);
    }
    System.out.print(temporaryTable);
  }

}
