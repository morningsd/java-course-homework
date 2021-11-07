package com.demian.hw1.part2oop.player;

import com.demian.hw1.part2oop.TicTacToe;

public abstract class Player {

  private final char playerCharacter;
  private final TicTacToe ticTacToe;

  protected Player(char playerCharacter, TicTacToe ticTacToe) {
    this.playerCharacter = playerCharacter;
    this.ticTacToe = ticTacToe;
  }

  public abstract int chooseNextMove();

  public char getPlayerCharacter() {
    return playerCharacter;
  }

  public TicTacToe getTicTacToe() {
    return ticTacToe;
  }
}
