import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    @Test
    void shuffle() {
        // Arrange
        Deck deck = new Deck();
        //ArrayList<Card> initialOrder = new ArrayList<>(deck.cards); // Save the initial order

        // Act
        deck.shuffle();
        //ArrayList<Card> shuffledOrder = new ArrayList<>(deck.cards); // Get the shuffled order

        // Assert
        //assertNotEquals(initialOrder, shuffledOrder, "Shuffling the deck should change the card order.");
    }

    @Test
    void dealCard() {
        // Arrange
        Deck deck = new Deck();
        int initialSize = deck.cardsRemaining();

        // Act
        Card dealtCard = deck.dealCard();
        int sizeAfterDeal = deck.cardsRemaining();

        // Assert
        assertNotNull(dealtCard, "Dealt card should not be null.");
        assertEquals(initialSize - 1, sizeAfterDeal, "Deck size should decrease by 1 after dealing a card.");
    }

    @Test
    void cardsRemaining() {
        // Arrange
        Deck deck = new Deck();

        // Act
        int initialSize = deck.cardsRemaining();
        deck.dealCard();
        int sizeAfterOneDeal = deck.cardsRemaining();

        // Assert
        assertEquals(52, initialSize, "Initial deck size should be 52.");
        assertEquals(51, sizeAfterOneDeal, "Deck size should be 51 after one card is dealt.");
    }
}