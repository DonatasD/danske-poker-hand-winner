package com.donatasd.evaluator;

import static org.junit.jupiter.api.Assertions.*;

import com.donatasd.domain.Card;
import com.donatasd.domain.HandRank;
import com.donatasd.domain.Rank;
import com.donatasd.domain.Suit;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * @author Donatas Daubaras
 */
public class HandRankEvaluatorTest {

  @Test
  public void evaluateHandRankDetectRoyalFlush() {
    var hand = List.of(
        Card.builder().suit(Suit.Heart).rank(Rank.Ace).build(),
        Card.builder().suit(Suit.Heart).rank(Rank.King).build(),
        Card.builder().suit(Suit.Heart).rank(Rank.Queen).build(),
        Card.builder().suit(Suit.Heart).rank(Rank.Jack).build(),
        Card.builder().suit(Suit.Heart).rank(Rank.Ten).build()
    );
    assertEquals(HandRankEvaluator.evaluateHandRank(hand), HandRank.RoyalFlush);
  }
  @Test
  public void evaluateHandRankDetectStraightFlush() {
    var hand = List.of(
        Card.builder().suit(Suit.Heart).rank(Rank.Nine).build(),
        Card.builder().suit(Suit.Heart).rank(Rank.King).build(),
        Card.builder().suit(Suit.Heart).rank(Rank.Queen).build(),
        Card.builder().suit(Suit.Heart).rank(Rank.Jack).build(),
        Card.builder().suit(Suit.Heart).rank(Rank.Ten).build()
    );
    assertEquals(HandRankEvaluator.evaluateHandRank(hand), HandRank.StraightFlush);
  }

  @Test
  public void evaluateHandRankDetectFourOfKind() {
    var hand = List.of(
        Card.builder().suit(Suit.Heart).rank(Rank.Nine).build(),
        Card.builder().suit(Suit.Diamond).rank(Rank.Nine).build(),
        Card.builder().suit(Suit.Spade).rank(Rank.Nine).build(),
        Card.builder().suit(Suit.Club).rank(Rank.Nine).build(),
        Card.builder().suit(Suit.Heart).rank(Rank.Ten).build()
    );
    assertEquals(HandRankEvaluator.evaluateHandRank(hand), HandRank.FourOfKind);
  }

  @Test
  public void evaluateHandRankDetectFullHouse() {
    var hand = List.of(
        Card.builder().suit(Suit.Heart).rank(Rank.Nine).build(),
        Card.builder().suit(Suit.Diamond).rank(Rank.Nine).build(),
        Card.builder().suit(Suit.Spade).rank(Rank.Nine).build(),
        Card.builder().suit(Suit.Club).rank(Rank.Ten).build(),
        Card.builder().suit(Suit.Heart).rank(Rank.Ten).build()
    );
    assertEquals(HandRankEvaluator.evaluateHandRank(hand), HandRank.FullHouse);
  }

  @Test
  public void evaluateHandRankDetectFlush() {
    var hand = List.of(
        Card.builder().suit(Suit.Heart).rank(Rank.Nine).build(),
        Card.builder().suit(Suit.Heart).rank(Rank.Two).build(),
        Card.builder().suit(Suit.Heart).rank(Rank.Three).build(),
        Card.builder().suit(Suit.Heart).rank(Rank.Five).build(),
        Card.builder().suit(Suit.Heart).rank(Rank.Ten).build()
    );
    assertEquals(HandRankEvaluator.evaluateHandRank(hand), HandRank.Flush);
  }

  @Test
  public void evaluateHandRankDetectStraight() {
    var hand = List.of(
        Card.builder().suit(Suit.Heart).rank(Rank.Nine).build(),
        Card.builder().suit(Suit.Spade).rank(Rank.Six).build(),
        Card.builder().suit(Suit.Heart).rank(Rank.Seven).build(),
        Card.builder().suit(Suit.Heart).rank(Rank.Eight).build(),
        Card.builder().suit(Suit.Heart).rank(Rank.Ten).build()
    );
    assertEquals(HandRankEvaluator.evaluateHandRank(hand), HandRank.Straight);
  }

  @Test
  public void evaluateHandRankDetectThreeOfKind() {
    var hand = List.of(
        Card.builder().suit(Suit.Heart).rank(Rank.Nine).build(),
        Card.builder().suit(Suit.Spade).rank(Rank.Nine).build(),
        Card.builder().suit(Suit.Diamond).rank(Rank.Nine).build(),
        Card.builder().suit(Suit.Heart).rank(Rank.Eight).build(),
        Card.builder().suit(Suit.Heart).rank(Rank.Ten).build()
    );
    assertEquals(HandRankEvaluator.evaluateHandRank(hand), HandRank.ThreeOfKind);
  }

  @Test
  public void evaluateHandRankDetectTwoPair() {
    var hand = List.of(
        Card.builder().suit(Suit.Heart).rank(Rank.Nine).build(),
        Card.builder().suit(Suit.Spade).rank(Rank.Nine).build(),
        Card.builder().suit(Suit.Diamond).rank(Rank.Eight).build(),
        Card.builder().suit(Suit.Heart).rank(Rank.Eight).build(),
        Card.builder().suit(Suit.Heart).rank(Rank.Ten).build()
    );
    assertEquals(HandRankEvaluator.evaluateHandRank(hand), HandRank.TwoPair);
  }

  @Test
  public void evaluateHandRankDetectOnePair() {
    var hand = List.of(
        Card.builder().suit(Suit.Heart).rank(Rank.Nine).build(),
        Card.builder().suit(Suit.Spade).rank(Rank.Nine).build(),
        Card.builder().suit(Suit.Diamond).rank(Rank.Jack).build(),
        Card.builder().suit(Suit.Heart).rank(Rank.Eight).build(),
        Card.builder().suit(Suit.Heart).rank(Rank.Ten).build()
    );
    assertEquals(HandRankEvaluator.evaluateHandRank(hand), HandRank.OnePair);
  }

  @Test
  public void evaluateHandRankDetectHighCard() {
    var hand = List.of(
        Card.builder().suit(Suit.Heart).rank(Rank.Ace).build(),
        Card.builder().suit(Suit.Spade).rank(Rank.Nine).build(),
        Card.builder().suit(Suit.Diamond).rank(Rank.Jack).build(),
        Card.builder().suit(Suit.Heart).rank(Rank.Eight).build(),
        Card.builder().suit(Suit.Heart).rank(Rank.Ten).build()
    );
    assertEquals(HandRankEvaluator.evaluateHandRank(hand), HandRank.HighCard);
  }

}
