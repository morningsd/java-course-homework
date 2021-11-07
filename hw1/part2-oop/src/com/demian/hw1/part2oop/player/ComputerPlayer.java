package com.demian.hw1.part2oop.player;

import com.demian.hw1.part2oop.Field;
import com.demian.hw1.part2oop.TicTacToe;
import com.demian.hw1.part2oop.util.Pair;
import java.util.Arrays;

public class ComputerPlayer extends Player {

  private final Player[] players;
  private final Field field;

  public ComputerPlayer(TicTacToe ticTacToe, char playerCharacter, Player[] players, Field field) {
    super(playerCharacter, ticTacToe);
    this.players = players;
    this.field = field;
  }

  @Override
  public int chooseNextMove() {
    return minimax(getTicTacToe().getCurrentPlayerIndex(), field.getCurrentFieldState(), 1).getKey();
  }

  private Pair<Integer, Integer> minimax(int currentPlayerIndex, char[] fieldState, int coefficient) {
    int bestPosition = -1;
    int bestPositionScore = Integer.MIN_VALUE;
    int sum = 0;
    char currentPlayerCharacter = players[currentPlayerIndex].getPlayerCharacter();

    for (int i = 0; i < fieldState.length; i++) {
      if (fieldState[i] == ' ') {
        char[] nextMoveField = Arrays.copyOf(fieldState, fieldState.length);
        nextMoveField[i] = currentPlayerCharacter;
        int score;
        if (field.checkWinner(nextMoveField, currentPlayerCharacter)) {
          score = 10 * coefficient;
        } else if (field.checkDraw(nextMoveField)) {
          score = 0;
        } else {
          int newCurrentPlayerPos = (currentPlayerIndex + 1) % 2;
          score = minimax(newCurrentPlayerPos, nextMoveField, -coefficient).getValue();
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


}
