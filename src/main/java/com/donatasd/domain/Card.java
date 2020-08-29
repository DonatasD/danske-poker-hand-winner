package com.donatasd.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Donatas Daubaras
 */
@Builder
@EqualsAndHashCode
@Getter
@ToString
public class Card {
   private final Rank rank;
   private final Suit suit;

   public Card(Rank rank, Suit suit) {
      this.rank = rank;
      this.suit = suit;
   }

}
