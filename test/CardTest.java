import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    @Test
    void getRank() {
        Card card = new Card(0, "Hearts"); // 2 of Hearts
        assertEquals("2", card.getRank(), "Rank should be 2.");
    }

    @Test
    void getSuit() {
        Card card = new Card(0, "Diamonds"); // 2 of Diamonds
        assertEquals("Diamonds", card.getSuit(), "Suit should be Diamonds.");
    }

    @Test
    void getRankValue() {
        Card card1 = new Card(0, "Spades"); // 2 of Spades
        Card card2 = new Card(9, "Clubs"); // Jack of Clubs
        Card card3 = new Card(12, "Hearts"); // Ace of Hearts
        assertEquals(2, card1.getRankValue(), "Rank value of 2 of Spades should be 2.");
        assertEquals(10, card2.getRankValue(), "Rank value of Jack of Clubs should be 10.");
        assertEquals(11, card3.getRankValue(), "Rank value of Ace of Hearts should be 11.");
    }

    @Test
    void getAlternativeAceValue() {
        Card aceCard = new Card(12, "Hearts"); // Ace of Hearts
        assertEquals(1, aceCard.getAlternativeAceValue(), "Alternative Ace value should be 1.");
    }

    @Test
    void getColour() {
        Card redCard = new Card(0, "Diamonds"); // 2 of Diamonds
        Card blackCard = new Card(0, "Spades"); // 2 of Spades
        assertEquals("Red", redCard.getColour(), "Diamonds should be red.");
        assertEquals("Black", blackCard.getColour(), "Spades should be black.");
    }

    @Test
    void isBiggerThan() {
        Card smallerCard = new Card(0, "Hearts"); // 2 of Hearts
        Card biggerCard = new Card(12, "Hearts"); // Ace of Hearts
        assertTrue(biggerCard.isBiggerThan(smallerCard), "Ace of Hearts should be bigger than 2 of Hearts.");
        assertFalse(smallerCard.isBiggerThan(biggerCard), "2 of Hearts should not be bigger than Ace of Hearts.");
    }

    @Test
    void testToString() {
        Card card = new Card(0, "Hearts"); // 2 of Hearts
        assertEquals("2 of Hearts", card.toString(), "toString should return '2 of Hearts'.");
    }

    @Test
    void compareTo() {
        Card card1 = new Card(0, "Hearts"); // 2 of Hearts
        Card card2 = new Card(12, "Diamonds"); // Ace of Diamonds
        assertTrue(card1.compareTo(card2) < 0, "2 of Hearts should be less than Ace of Diamonds.");
        assertTrue(card2.compareTo(card1) > 0, "Ace of Diamonds should be greater than 2 of Hearts.");
        assertEquals(0, card1.compareTo(new Card(0, "Clubs")), "2 of Hearts should be equal to 2 of Clubs by rank.");
    }
}