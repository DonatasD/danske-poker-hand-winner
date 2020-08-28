package com.donatasd.reader;

import com.donatasd.domain.Card;
import com.donatasd.domain.Rank;
import com.donatasd.domain.Suit;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Donatas Daubaras
 */
public class CardReader {

  protected static List<Card> readPlayerCards(List<String> cards) {
    return cards.stream().map(CardReader::readCard).collect(Collectors.toList());
  }

  protected static Card readCard(String card) throws IllegalArgumentException {
    var cardCharacterLength = card.length();
    if (!List.of(2, 3).contains(cardCharacterLength)) {
      throw new IllegalArgumentException(
          String.format("Unable to read card: %s. Invalid character number", card)
      );
    }
    var rank = Rank.findByRepresentation(card.substring(0, cardCharacterLength == 2 ? 1 : 2));
    var suit = Suit.findByRepresentation(String.valueOf(card.charAt(card.length() - 1)));
    return Card.builder().rank(rank).suit(suit).build();
  }
}
