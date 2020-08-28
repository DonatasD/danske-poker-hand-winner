package com.donatasd.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Donatas Daubaras
 */
@Builder
@EqualsAndHashCode
public class Card {
   private final Rank rank;
   private final Suit suit;

   public Card(Rank rank, Suit suit) {
      this.rank = rank;
      this.suit = suit;
   }

}
