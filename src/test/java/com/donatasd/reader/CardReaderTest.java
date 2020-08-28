package com.donatasd.reader;

import static org.junit.jupiter.api.Assertions.*;

import com.donatasd.domain.Card;
import com.donatasd.domain.Rank;
import com.donatasd.domain.Suit;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Test;

/**
 * Test suite for Card Reader
 *
 * @author Donatas Daubaras
 */
public class CardReaderTest {

  @Test
  public void readPlayerCards() {
    var playerCards =  CardReader.readPlayerCards(List.of("AH", "AD", "10C", "2C", "5S"));
    var expectedPlayerCards = List.of(
        Card.builder().rank(Rank.Ace).suit(Suit.Heart).build(),
        Card.builder().rank(Rank.Ace).suit(Suit.Diamond).build(),
        Card.builder().rank(Rank.Ten).suit(Suit.Club).build(),
        Card.builder().rank(Rank.Two).suit(Suit.Club).build(),
        Card.builder().rank(Rank.Five).suit(Suit.Spade).build()
    );
    assertTrue(Objects.equals(playerCards, expectedPlayerCards));
  }

  @Test
  public void readCardThrowsException() {
    List.of("13H", "14D", "AE", "2F", "5N").forEach(value -> {
      assertThrows(
          IllegalArgumentException.class,
          () -> CardReader.readCard(value)
      );
    });
  }
}
