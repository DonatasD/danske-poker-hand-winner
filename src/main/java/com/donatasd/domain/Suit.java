package com.donatasd.domain;

/**
 * @author Donatas Daubaras
 */
public enum Suit {
  Diamond("D"),
  Club("C"),
  Heart("H"),
  Spade("S");

  private final String representation;

  Suit(String representation) {
    this.representation = representation;
  }

  public String getRepresentation() {
    return representation;
  }

  public static Suit findByRepresentation(String representation) throws IllegalArgumentException {
    for(Suit suit : values()){
      if(suit.getRepresentation().equals(representation)){
        return suit;
      }
    }
    throw new IllegalArgumentException(String.format("Invalid suit supplied as: %s",
        representation));
  }
}
