import java.util.ArrayList;
import java.util.Collections;

class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        // Initialize deck with all 52 cards
        for (String suit : suits) {
            for (int rank = 0; rank < ranks.length; rank++) {
                cards.add(new Card(rank, suit));
            }
        }
    }

    /**
     * Shuffles the deck of cards.
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Deals a card from the top of the deck.
     *
     * @return The dealt card.
     */
    public Card dealCard() {
        return cards.remove(cards.size() - 1);
    }

    /**
     * Gets the number of cards remaining in the deck.
     *
     * @return Number of cards left.
     */
    public int cardsRemaining() {
        return cards.size();
    }
}