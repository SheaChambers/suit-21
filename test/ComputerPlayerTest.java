import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComputerPlayerTest {
    @Test
    void swapCard() {
        ComputerPlayer computerPlayer = new ComputerPlayer("Computer");
        // Add initial cards to the hand
        computerPlayer.addCard(new Card(0, "Hearts")); // 2 of Hearts
        computerPlayer.addCard(new Card(1, "Diamonds")); // 3 of Diamonds
        computerPlayer.addCard(new Card(2, "Clubs")); // 4 of Clubs
        computerPlayer.addCard(new Card(3, "Spades")); // 5 of Spades
        computerPlayer.addCard(new Card(4, "Hearts")); // 6 of Hearts

        // Add a new card to swap
        Card newCard = new Card(12, "Hearts"); // Ace of Hearts
        computerPlayer.swapCard(newCard, -1); // Let the computer determine the best swap

        Card replacedCard = computerPlayer.getReplacedCard();

        assertNotNull(replacedCard, "Replaced card should not be null.");
        assertTrue(computerPlayer.getHand().contains(newCard), "New card should be in the hand.");
        assertFalse(computerPlayer.getHand().contains(replacedCard), "Replaced card should no longer be in the hand.");
    }

    @Test
    void getReplacedCard() {
        ComputerPlayer computerPlayer = new ComputerPlayer("Computer");
        // Add initial cards to the hand
        computerPlayer.addCard(new Card(0, "Hearts")); // 2 of Hearts
        computerPlayer.addCard(new Card(1, "Diamonds")); // 3 of Diamonds
        computerPlayer.addCard(new Card(2, "Clubs")); // 4 of Clubs
        computerPlayer.addCard(new Card(3, "Spades")); // 5 of Spades
        computerPlayer.addCard(new Card(4, "Hearts")); // 6 of Hearts

        // Add a new card to swap
        Card newCard = new Card(12, "Hearts"); // Ace of Hearts
        computerPlayer.swapCard(newCard, -1); // Let the computer determine the best swap

        Card replacedCard = computerPlayer.getReplacedCard();

        assertNotNull(replacedCard, "Replaced card should be returned.");
        assertFalse(computerPlayer.getHand().contains(replacedCard), "Replaced card should not be in the hand anymore.");
    }
}