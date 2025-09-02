import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReplayTest {
    @Test
    void logRoundTest() {
        // Arrange
        Replay replay = new Replay();

        Player player = new Player("Test Player");
        player.setLastDecision("Replaced Queen of Diamonds with Ace of Spades");

        List<Card> handBeforeSwap = new ArrayList<>();
        handBeforeSwap.add(new Card(11, "Diamonds")); // Queen of Diamonds
        handBeforeSwap.add(new Card(7, "Hearts"));    // 7 of Hearts

        List<Card> handAfterSwap = new ArrayList<>();
        handAfterSwap.add(new Card(1, "Spades"));     // Ace of Spades
        handAfterSwap.add(new Card(7, "Hearts"));    // 7 of Hearts

        String suitScoresBeforeSwap = "Hearts: 7, Diamonds: 11, Clubs: 0, Spades: 0";
        String suitScoresAfterSwap = "Hearts: 7, Diamonds: 0, Clubs: 0, Spades: 11";

        // Act
        replay.logRound(1, player, handBeforeSwap, suitScoresBeforeSwap, handAfterSwap, suitScoresAfterSwap);

        // Debugging (optional)
        System.out.println("Replay Logs: " + replay.getRoundLogs());

        // Assert
        assertFalse(replay.getRoundLogs().isEmpty(), "Replay logs should not be empty.");
        String log = replay.getRoundLogs().get(0);
        assertTrue(log.contains("Test Player"), "Log should include player name.");
        assertTrue(log.contains("Replaced Queen of Diamonds with Ace of Spades"), "Log should include the decision.");
        assertTrue(log.contains("Queen of Diamonds"), "Log should include hand before swap.");
        assertTrue(log.contains("Ace of Spades"), "Log should include hand after swap.");
    }

    @Test
    void displayReplayTest() {
        // Arrange
        Replay replay = new Replay();

        replay.getRoundLogs().add("Player1: Replaced 2 of Hearts with 5 of Clubs");
        replay.getRoundLogs().add("Player2: Replaced 10 of Diamonds with Jack of Spades");

        // Act and Assert
        assertDoesNotThrow(replay::displayReplay, "Display replay should not throw exceptions.");
    }
}