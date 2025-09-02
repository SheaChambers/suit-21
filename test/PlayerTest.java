import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    void getName() {
        Player player = new Player("Alice");
        assertEquals("Alice", player.getName(), "Player name should match the provided name.");
    }

    @Test
    void addCard() {
        Player player = new Player("Alice");
        Card card = new Card(0, "Hearts");
        player.addCard(card);
        assertTrue(player.getHand().contains(card), "Hand should contain the added card.");
    }

    @Test
    void clearHand() {
        Player player = new Player("Alice");
        player.addCard(new Card(0, "Hearts"));
        player.clearHand();
        assertTrue(player.getHand().isEmpty(), "Hand should be empty after clearing.");
    }

    @Test
    void displayHand() {
        // Setup
        Player player = new Player("TestPlayer");

        // Add exactly five cards to the player's hand
        player.addCard(new Card(0, "Hearts")); // "2 of Hearts"
        player.addCard(new Card(1, "Diamonds")); // "3 of Diamonds"
        player.addCard(new Card(2, "Clubs")); // "4 of Clubs"
        player.addCard(new Card(3, "Spades")); // "5 of Spades"
        player.addCard(new Card(12, "Hearts")); // "Ace of Hearts"

        // Expected output
        String expectedOutput = """
                A. 2 of Hearts
                B. 3 of Diamonds
                C. 4 of Clubs
                D. 5 of Spades
                E. Ace of Hearts
                """;

        // Capture the console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream)); // Redirect System.out to capture output

        try {
            player.displayHand(); // Call the method
        } finally {
            System.setOut(originalOut); // Restore System.out
        }

        // Validate the output
        String actualOutput = outputStream.toString().trim(); // Remove extra spaces or newlines

        // Normalize expected and actual outputs
        String normalizedExpected = expectedOutput.trim().replaceAll("\\s+", " ");
        String normalizedActual = actualOutput.trim().replaceAll("\\s+", " ");

        // Assertion
        assertEquals(normalizedExpected, normalizedActual, "Hand display should match the expected format.");

    }


    @Test
    void swapCard() {
        Player player = new Player("Alice");
        Card initialCard = new Card(0, "Hearts");
        Card newCard = new Card(1, "Diamonds");
        player.addCard(initialCard);
        player.swapCard(newCard, 0);
        assertEquals(newCard, player.getHand().get(0), "The swapped card should replace the original card.");
    }

    @Test
    void calculateMaxScore() {
        Player player = new Player("Alice");
        player.addCard(new Card(0, "Hearts"));
        player.addCard(new Card(12, "Hearts")); // Ace
        assertEquals(13, player.calculateMaxScore(), "Max score calculation should consider Ace as 11 when suitable.");
    }

    @Test
    void getSuitScores() {
        Player player = new Player("Alice");
        player.addCard(new Card(0, "Hearts"));
        player.addCard(new Card(12, "Hearts")); // Ace
        String expectedScores = "Hearts: 13, Diamonds: 0, Clubs: 0, Spades: 0";
        assertEquals(expectedScores, player.getSuitScores(), "Suit scores should reflect the sum of ranks per suit.");
    }

    @Test
    void hasScoreOf21() {
        Player player = new Player("Alice");
        player.addCard(new Card(10, "Hearts"));
        player.addCard(new Card(12, "Hearts")); // Ace
        assertTrue(player.hasScoreOf21(), "Player should have a score of 21.");
    }

    @Test
    void addPoints() {
        Player player = new Player("Alice");
        player.addPoints(10);
        assertEquals(10, player.getPoints(), "Points should be updated correctly after addition.");
    }

    @Test
    void getPoints() {
        Player player = new Player("Alice");
        player.addPoints(5);
        assertEquals(5, player.getPoints(), "getPoints should return the correct points.");
    }

    @Test
    void getHand() {
        Player player = new Player("Alice");
        Card card = new Card(0, "Hearts");
        player.addCard(card);
        List<Card> hand = player.getHand();
        assertEquals(1, hand.size(), "Hand should contain one card.");
        assertEquals(card, hand.get(0), "Hand should contain the added card.");
    }

    @Test
    void setLastDecision() {
        Player player = new Player("Alice");
        player.setLastDecision("Replaced 2 of Hearts with 3 of Diamonds");
        assertEquals("Replaced 2 of Hearts with 3 of Diamonds", player.getLastDecision(), "Last decision should be set correctly.");
    }

    @Test
    void getLastDecision() {
        Player player = new Player("Alice");
        assertEquals("No decision made", player.getLastDecision(), "Default decision should be 'No decision made'.");
        player.setLastDecision("Replaced 2 of Hearts with 3 of Diamonds");
        assertEquals("Replaced 2 of Hearts with 3 of Diamonds", player.getLastDecision(), "getLastDecision should return the correct decision.");
    }

}