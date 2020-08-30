package com.donatasd.reader;

import com.donatasd.domain.Card;
import com.donatasd.domain.Rank;
import com.donatasd.domain.Suit;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Donatas Daubaras
 */
public class CardReader {

  public static List<Card> readPlayerCards(List<String> cards) {
    return cards.stream().map(CardReader::readCard).collect(Collectors.toList());
  }

  protected static Card readCard(String card) throws IllegalArgumentException {
    if (card.length() != 2) {
      throw new IllegalArgumentException(
          String.format("Unable to read card: %s. Invalid character number", card)
      );
    }
    var rank = Rank.findByRepresentation(card.substring(0, 1));
    var suit = Suit.findByRepresentation(card.substring(1, 2));
    return Card.builder().rank(rank).suit(suit).build();
  }

  public static Stream<String> readPokerFile(String resourceFileName) throws IOException,
      URISyntaxException {
    var path = Paths.get(CardReader.class.getClassLoader().getResource(resourceFileName).toURI());
    return Files.lines(path);
  }
}
