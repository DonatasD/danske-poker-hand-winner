package com.donatasd.domain;

import lombok.Data;

/**
 * @author Donatas Daubaras
 */
public enum HandRank {
  RoyalFlush(10), StraightFlush(9), FourOfKind(8), FullHouse(7), Flush(6), Straight(5),
  ThreeOfKind(4), TwoPair(3), OnePair(2), HighCard(1);

  private final int value;

  HandRank(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
