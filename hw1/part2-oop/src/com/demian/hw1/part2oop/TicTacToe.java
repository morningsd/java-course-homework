package com.demian.hw1.part2oop;

import com.demian.hw1.part2oop.player.ComputerPlayer;
import com.demian.hw1.part2oop.player.PersonPlayer;
import com.demian.hw1.part2oop.player.Player;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {

  private final Field field;
  private final Player[] players = new Player[2];
  private int currentPlayerIndex = 0;

  public TicTacToe() {
    this.field = new Field();
    this.players[0] = new PersonPlayer(this, 'X');
  }

  public void start() {
    field.printInitialTable();
    System.out.println("Welcome to the TicTacToe!\n"
        + "You can see coordinates of squares above.\n"
        + "To play against friend press 1, to play against computer press 2.\n\n"
        + "Note! Press Ctrl+c to stop the game.\n");

    Scanner scanner = new Scanner(System.in);
    boolean init = false;
    do {
      System.out.println("To play against friend press 1, to play against computer press 2.\n"
          + "Enter a number: ");
      int gameType;
      try {
        gameType = scanner.nextInt();
      } catch (InputMismatchException ime) {
        System.out.println("Incorrect input. Try again.");
        scanner.next();
        continue;
      }

      switch (gameType) {
        case 1:
          this.players[1] = new PersonPlayer(this, 'O');
          break;
        case 2:
          this.players[1] = new ComputerPlayer(this, 'O', players, field);
          break;
        default:
          System.out.println("Unknown value. Try again.");
          continue;
      }

      init = true;
    } while (!init);

    play();
  }

  private void play() {
    boolean gameOver;
    Player currentPlayer;
    int nextMoveCoordinate;
    char nextMovePlayerCharacter;
    do {
      currentPlayer = players[currentPlayerIndex];

      // while move is not valid - repeat
      nextMovePlayerCharacter = currentPlayer.getPlayerCharacter();

      boolean validMove = false;
      while (!validMove) {
        nextMoveCoordinate = currentPlayer.chooseNextMove();
        validMove = field.makeMove(nextMovePlayerCharacter, nextMoveCoordinate);
      }

      field.printCurrentFieldState();

      gameOver = field.checkIfGameOver(field.getCurrentFieldState(), currentPlayer.getPlayerCharacter());

      currentPlayerIndex = (currentPlayerIndex + 1) % 2; // players.length = 2
    } while (!gameOver);

  }

  public int getCurrentPlayerIndex() {
    return currentPlayerIndex;
  }
}
