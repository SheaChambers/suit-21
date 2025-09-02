class ComputerPlayer extends Player {
    private Card replacedCard; // Tracks the card that was replaced during a swap

    public ComputerPlayer(String name) {
        super(name);
    }

    /**
     * Finds the index of the card with the lowest value in the player's hand.
     *
     * @return Index of the card with the lowest value.
     */
    private int findLowestValueCardIndex() {
        int lowestValue = Integer.MAX_VALUE;
        int index = -1;

        // Iterate through the hand to find the card with the lowest rank value
        for (int i = 0; i < getHand().size(); i++) {
            Card card = getHand().get(i);
            int value = card.getRankValue();

            // Special handling for Ace, which can have an alternative value
            if (card.getRank().equals("Ace")) {
                value = card.getAlternativeAceValue();
            }
            if (value < lowestValue) {
                lowestValue = value;
                index = i;
            }
        }
        return index;
    }

    /**
     * Swaps a card in the player's hand with a new card to improve the score.
     * The card to be swapped is chosen based on maximizing the improvement
     * towards the target score.
     *
     * @param newCard The new card to be added to the hand.
     * @param index   The index of the card to be replaced (not used here).
     */
    @Override
    public void swapCard(Card newCard, int index) {
        int bestSwapIndex = -1;
        int closestTo21 = calculateMaxScore(); // Current highest score close to 21
        int targetScore = 21;

        int maxImprovement = 0;

        // Try replacing each card to see which swap provides the best improvement
        for (int i = 0; i < getHand().size(); i++) {
            Card originalCard = getHand().get(i);
            getHand().set(i, newCard); // Temporarily swap card

            int newScore = calculateMaxScore();
            int improvement = newScore - closestTo21;

            // Update the best swap index if this swap improves the score
            if (improvement > maxImprovement &&
                    Math.abs(targetScore - newScore) <= Math.abs(targetScore - closestTo21)) {
                maxImprovement = improvement;
                bestSwapIndex = i;
            }

            getHand().set(i, originalCard); // Restore original card
        }

        // If no improvement is found, replace the card with the lowest value
        if (bestSwapIndex == -1) {
            bestSwapIndex = findLowestValueCardIndex();
        }

        replacedCard = getHand().get(bestSwapIndex); // Save replaced card
        getHand().set(bestSwapIndex, newCard); // Perform the swap
        setLastDecision("Replaced " + replacedCard + " with " + newCard);
        System.out.println("\n" + getName().toUpperCase() + " CHOICE - " + replacedCard + " replaced by " + newCard);
    }

    /**
     * Retrieves the last card that was replaced in a swap.
     *
     * @return The card that was replaced.
     */
    public Card getReplacedCard() {
        return replacedCard;
    }
}