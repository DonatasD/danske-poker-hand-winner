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
    assertEquals(HandRank.RoyalFlush, HandRankEvaluator.evaluateHandRank(hand));
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
    assertEquals(HandRank.StraightFlush, HandRankEvaluator.evaluateHandRank(hand));
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
    assertEquals(HandRank.FourOfKind, HandRankEvaluator.evaluateHandRank(hand));
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
    assertEquals(HandRank.FullHouse, HandRankEvaluator.evaluateHandRank(hand));
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
    assertEquals(HandRank.Flush, HandRankEvaluator.evaluateHandRank(hand));
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
    assertEquals(HandRank.Straight, HandRankEvaluator.evaluateHandRank(hand));
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
    assertEquals(HandRank.ThreeOfKind, HandRankEvaluator.evaluateHandRank(hand));
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
    assertEquals(HandRank.TwoPair, HandRankEvaluator.evaluateHandRank(hand));
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
    assertEquals(HandRank.OnePair, HandRankEvaluator.evaluateHandRank(hand));
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
    assertEquals(HandRank.HighCard, HandRankEvaluator.evaluateHandRank(hand));
  }

}
