package com.donatasd.evaluator;

import com.donatasd.domain.Card;
import com.donatasd.domain.HandRank;
import com.donatasd.domain.Rank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Donatas Daubaras
 */
public class HandRankEvaluator {

  protected static HandRank evaluateHandRank(List<Card> hand) {
    var sortedByRank =
        hand.stream().sorted(((card1, card2) -> card1.getRank().getValue() - card2.getRank().getValue())).collect(
            Collectors.toList());
    if (isRoyalFlush(sortedByRank)) {
      return HandRank.RoyalFlush;
    }
    if (isStraightFlush(sortedByRank)) {
      return HandRank.StraightFlush;
    }
    if (isFourOfKind(sortedByRank)) {
      return HandRank.FourOfKind;
    }
    if (isFullHouse(sortedByRank)) {
      return HandRank.FullHouse;
    }
    if (isFlush(sortedByRank)) {
      return HandRank.Flush;
    }
    if (isStraight(sortedByRank)) {
      return HandRank.Straight;
    }
    if (isThreeOfKind(sortedByRank)) {
      return HandRank.ThreeOfKind;
    }
    if (isTwoPair(sortedByRank)) {
      return HandRank.TwoPair;
    }
    if (isPair(sortedByRank)) {
      return HandRank.OnePair;
    }
    return HandRank.HighCard;
  }

  private static boolean isRoyalFlush(List<Card> sortedHand) {
      return isFlush(sortedHand) && isStraight(sortedHand) && findHighCard(sortedHand).getRank().equals(Rank.Ace);
  }

  private static boolean isStraightFlush(List<Card> sortedHand) {
    return isFlush(sortedHand) && isStraight(sortedHand);
  }

  private static boolean isFlush(List<Card> sortedHand) {
    return sortedHand.stream().map(Card::getSuit).distinct().count() == 1;
  }

  private static boolean isFourOfKind(List<Card> sortedHand) {
    var duplicateCounter = getDuplicateMap(sortedHand);
    return duplicateCounter.values().stream().filter(count -> count == 4).findAny().isPresent();
  }

  private static boolean isFullHouse(List<Card> sortedHand) {
    var duplicateCounter = getDuplicateMap(sortedHand);
    return isThreeOfKind(sortedHand) && isPair(sortedHand);
  }

  private static boolean isStraight(List<Card> sortedHand) {
    if (sortedHand.get(sortedHand.size() - 1).getRank().equals(Rank.Ace)) {
      if (sortedHand.get(0).getRank().equals(Rank.Two) || sortedHand.get(0).getRank().equals(Rank.Ten)) {
        return isInOrder(sortedHand.subList(0, sortedHand.size() - 2));
      }
    }
    return isInOrder(sortedHand);
  }

  private static boolean isThreeOfKind(List<Card> sortedHand) {
    var duplicateCounter = getDuplicateMap(sortedHand);
    return duplicateCounter.values().stream().filter(count -> count == 3).findAny().isPresent();
  }

  private static boolean isTwoPair(List<Card> sortedHand) {
    var duplicateCounter = getDuplicateMap(sortedHand);
    return duplicateCounter.values().stream().filter(count -> count == 2).count() == 2;
  }

  private static boolean isPair(List<Card> sortedHand) {
    var duplicateCounter = getDuplicateMap(sortedHand);
    return duplicateCounter.values().stream().filter(count -> count == 2).findAny().isPresent();
  }

  private static Card findHighCard(List<Card> sortedHand) {
    return sortedHand.get(sortedHand.size() - 1);
  }

  private static boolean isInOrder(List<Card> sortedHand) {
    var ranks = sortedHand.stream().map(Card::getRank).map(Rank::getValue).collect(Collectors.toList());
    for (int i = 1; i < ranks.size(); i++) {
      if (ranks.get(i) != (ranks.get(i - 1) + 1)) {
        return false;
      }
    }
    return true;
  }

  private static Map<Integer, Integer> getDuplicateMap(List<Card> sortedHand) {
    var duplicateCounter = new HashMap<Integer, Integer>();
    sortedHand.forEach(card -> duplicateCounter.put(
        card.getRank().getValue(),
        duplicateCounter.getOrDefault(card.getRank().getValue(), 0) + 1
    ));
    return duplicateCounter;
  }
}
