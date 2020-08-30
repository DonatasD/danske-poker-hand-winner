package com.donatasd.domain;

/**
 * @author Donatas Daubaras
 */
public enum HandRank {
  StraightFlush(8000000), FourOfKind(6000000), FullHouse(6000000), Flush(5000000), Straight(
      4000000),
  ThreeOfKind(3000000), TwoPair(2000000), OnePair(1000000), HighCard(0);

  private final int value;

  HandRank(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
