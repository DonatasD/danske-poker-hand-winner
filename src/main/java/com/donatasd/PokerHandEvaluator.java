package com.donatasd;

import com.donatasd.evaluator.HandRankEvaluator;
import com.donatasd.reader.CardReader;
import java.util.Arrays;
import lombok.SneakyThrows;

/**
 * @author Donatas Daubaras
 */
public class PokerHandEvaluator {

  @SneakyThrows
  public static void main(String[] args) {
    System.out.println("=============RESULT=============");
    System.out.println(
        CardReader.readPokerFile("poker.txt")
            .map(line -> Arrays.asList(line.split(" ")))
            .map(CardReader::readPlayerCards)
            // Split into two piles first 5 (player 1) last 5 (player 2)
            .map(cardList -> HandRankEvaluator.isPlayer1Winner(
                cardList.subList(0, 5),
                cardList.subList(5, 10)
            ))
            .filter(player1Won -> player1Won)
            .count()
    );
    System.out.println("================================");
  }

}
