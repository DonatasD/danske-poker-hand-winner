package com.donatasd.domain;

import lombok.ToString;

/**
 * @author Donatas Daubaras
 */
@ToString
public enum Rank {

  Ace("A", 14), King("K", 13), Queen("Q", 12), Jack("J", 11), Ten("T", 10), Nine("9", 9),
  Eight("8", 8), Seven("7", 7), Six("6", 6), Five("5", 5), Four("4", 4), Three("3", 3), Two("2", 2);

  private final String representation;
  private final int value;

  Rank(String representation, int value) {
    this.representation = representation;
    this.value = value;
  }

  public String getRepresentation() {
    return representation;
  }

  public int getValue() {
    return value;
  }

  public static Rank findByRepresentation(String representation) throws IllegalArgumentException {
    for(Rank rank : values()){
      if(rank.getRepresentation().equals(representation)){
        return rank;
      }
    }
    throw new IllegalArgumentException(String.format("Invalid rank supplied as: %s",
        representation));
  }
}
