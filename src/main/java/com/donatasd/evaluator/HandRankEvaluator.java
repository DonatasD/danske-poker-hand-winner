package com.donatasd.evaluator;

import com.donatasd.domain.Card;
import com.donatasd.domain.HandRank;
import com.donatasd.domain.Rank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Donatas Daubaras
 */
public class HandRankEvaluator {

  public static boolean isPlayer1Winner(List<Card> player1Hand, List<Card> player2Hand) {
    return evaluateHandRank(player1Hand).getValue() > evaluateHandRank(player2Hand).getValue();
  }

  protected static Map.Entry<HandRank, Integer> evaluateHandRank(List<Card> hand) {
    var sortedByRank = hand
        .stream()
        .sorted((card1, card2) -> Integer.compare(card1.getRank().getValue(),
            card2.getRank().getValue()))
        .collect(Collectors.toList());
    var duplicateMap = getDuplicateMap(sortedByRank);
    if (isStraightFlush(sortedByRank)) {
      return Map.entry(
          HandRank.StraightFlush,
          HandRank.StraightFlush.getValue() + getValueBasedOnCardRanks(sortedByRank)
      );
    }
    if (isFourOfKind(sortedByRank, duplicateMap)) {
      return Map.entry(
          HandRank.FourOfKind,
          HandRank.FourOfKind.getValue() + sortedByRank.get(2).getRank().getValue()
      );
    }
    if (isFullHouse(sortedByRank, duplicateMap)) {
      return Map.entry(
          HandRank.FullHouse,
          HandRank.FullHouse.getValue() + sortedByRank.get(2).getRank().getValue()
      );
    }
    if (isFlush(sortedByRank)) {
      return Map.entry(
          HandRank.Flush,
          HandRank.Flush.getValue() + getValueBasedOnCardRanks(sortedByRank)
      );
    }
    if (isStraight(sortedByRank)) {
      return Map.entry(
          HandRank.Straight,
          HandRank.Straight.getValue() + getValueBasedOnCardRanks(sortedByRank)
      );
    }
    if (isThreeOfKind(sortedByRank, duplicateMap)) {
      return Map.entry(
          HandRank.ThreeOfKind,
          HandRank.ThreeOfKind.getValue() + sortedByRank.get(2).getRank().getValue()
      );
    }
    if (isTwoPair(sortedByRank, duplicateMap)) {
      var biggerPair = duplicateMap
          .entrySet()
          .stream()
          .filter(entry -> entry.getValue() == 2)
          .map(Entry::getKey)
          .max(Integer::compare)
          .orElse(0);
      var smallerPair = duplicateMap
          .entrySet()
          .stream()
          .filter(entry -> entry.getValue() == 2)
          .map(Entry::getKey)
          .min(Integer::compare)
          .orElse(0);
      var highCard = duplicateMap
          .entrySet()
          .stream()
          .filter(entry -> entry.getValue() == 1)
          .map(entry -> entry.getKey())
          .findFirst()
          .orElse(0);
      return Map.entry(
          HandRank.TwoPair,
          (int) (HandRank.TwoPair.getValue() + (biggerPair * Math.pow(14, 2)) + (smallerPair * 14)
              + highCard)
      );
    }
    if (isPair(sortedByRank, duplicateMap)) {
      var pair = duplicateMap
          .entrySet()
          .stream()
          .filter(entry -> entry.getValue() == 2)
          .map(Entry::getKey)
          .findFirst()
          .orElse(0);
      var sortedHighCards = sortedByRank
          .stream()
          .filter(card -> card.getRank().getValue() != pair)
          .sorted((card1, card2) -> Integer
              .compare(card1.getRank().getValue(), card2.getRank().getValue()))
          .collect(Collectors.toList());
      return Map.entry(
          HandRank.OnePair,
          (int) (HandRank.OnePair.getValue() + pair * Math.pow(14, 3) + getValueBasedOnCardRanks(
              sortedHighCards))
      );
    }
    return Map.entry(
        HandRank.HighCard,
        HandRank.HighCard.getValue() + getValueBasedOnCardRanks(sortedByRank)
    );
  }

  private static boolean isStraightFlush(List<Card> sortedHand) {
    return isFlush(sortedHand) && isStraight(sortedHand);
  }

  private static boolean isFlush(List<Card> sortedHand) {
    return sortedHand.stream().map(Card::getSuit).distinct().count() == 1;
  }

  private static boolean isFourOfKind(List<Card> sortedHand, Map<Integer, Integer> duplicateMap) {
    return duplicateMap.values().stream().filter(count -> count == 4).findAny().isPresent();
  }

  private static boolean isFullHouse(List<Card> sortedHand, Map<Integer, Integer> duplicateMap) {
    return isThreeOfKind(sortedHand, duplicateMap) && isPair(sortedHand, duplicateMap);
  }

  private static boolean isStraight(List<Card> sortedHand) {
    if (sortedHand.get(sortedHand.size() - 1).getRank().equals(Rank.Ace)) {
      if (sortedHand.get(0).getRank().equals(Rank.Two) || sortedHand.get(0).getRank()
          .equals(Rank.Ten)) {
        return isInOrder(sortedHand.subList(0, sortedHand.size() - 2));
      }
    }
    return isInOrder(sortedHand);
  }

  private static boolean isThreeOfKind(List<Card> sortedHand, Map<Integer, Integer> duplicateMap) {
    return duplicateMap.values().stream().filter(count -> count == 3).findAny().isPresent();
  }

  private static boolean isTwoPair(List<Card> sortedHand, Map<Integer, Integer> duplicateMap) {
    return duplicateMap.values().stream().filter(count -> count == 2).count() == 2;
  }

  private static boolean isPair(List<Card> sortedHand, Map<Integer, Integer> duplicateMap) {
    return duplicateMap.values().stream().filter(count -> count == 2).findAny().isPresent();
  }

  private static boolean isInOrder(List<Card> sortedHand) {
    var ranks = sortedHand.stream().map(Card::getRank).map(Rank::getValue)
        .collect(Collectors.toList());
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

  private static int getValueBasedOnCardRanks(List<Card> sortedHand) {
    return IntStream.range(0, sortedHand.size()).reduce(
        0,
        (total, index) -> (int) (total + (Math.pow(14, index) * sortedHand.get(index).getRank()
            .getValue()))
    );
  }
}
