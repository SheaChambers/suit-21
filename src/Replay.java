import java.util.ArrayList;
import java.util.List;

class Replay {
    private ArrayList<String> roundLogs; // Stores the logs for each round
    private int totalRounds; // Tracks the total number of rounds logged

    public Replay() {
        this.roundLogs = new ArrayList<>();
        this.totalRounds = 0;
    }

    /**
     * Formats a list of cards into a readable string representation.
     *
     * @param hand The list of cards to format.
     * @return A string representation of the hand.
     */
    private String formatHand(List<Card> hand) {
        StringBuilder formattedHand = new StringBuilder();
        for (Card card : hand) {
            formattedHand.append(card.toString()).append("\n");
        }
        return formattedHand.toString();
    }

    /**
     * Logs the details of a round, including the player's hand before and after a swap,
     * the scores for each suit, and the player's decision.
     *
     * @param roundNumber        The round number.
     * @param player             The player whose action is being logged.
     * @param handBeforeSwap     The player's hand before the swap.
     * @param suitScoresBeforeSwap String representation of suit scores before the swap.
     * @param handAfterSwap      The player's hand after the swap.
     * @param suitScoresAfterSwap String representation of suit scores after the swap.
     */
    public void logRound(int roundNumber, Player player, List<Card> handBeforeSwap,
                         String suitScoresBeforeSwap, List<Card> handAfterSwap, String suitScoresAfterSwap) {
        StringBuilder log = new StringBuilder();

        log.append(player.getName()).append(" (Round ").append(roundNumber).append(")\n\n");
        log.append(formatHand(handBeforeSwap)).append("\n");
        log.append(suitScoresBeforeSwap).append("\n");

        log.append("\nDecision: ").append(player.getLastDecision()).append("\n\n");

        log.append(formatHand(handAfterSwap)).append("\n");
        log.append(suitScoresAfterSwap).append("\n");
        log.append("\n--------------------------------");

        roundLogs.add(log.toString()); // Add the formatted log to the list
        totalRounds++;
    }

    /**
     * Displays the entire game replay, showing all logged rounds in order.
     */
    public void displayReplay() {
        System.out.println("\n=== GAME REPLAY (" + totalRounds + " Moves) ===\n");
        for (String log : roundLogs) {
            System.out.println(log);
        }
    }

    // Getter for testing purposes
    public ArrayList<String> getRoundLogs() {
        return roundLogs;
    }
}