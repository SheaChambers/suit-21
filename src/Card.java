class Card implements Comparable<Card> {

    private int rank; // Represents the index of the rank (0 = "2", 12 = "Ace")
    private String suit; // Represents the suit of the card

    private static String[] ranks = { "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "Jack", "Queen", "King", "Ace" };
    private static String[] suits = { "Clubs", "Diamonds", "Hearts", "Spades" };

    public Card(int rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String getRank() {
        return Card.ranks[this.rank];
    }

    public String getSuit() {
        return this.suit;
    }

    /**
     * Returns the rank value of the card. Face cards are worth 10, and Ace defaults to 11.
     *
     * @return The numerical value of the card.
     */
    public int getRankValue() {
        if (this.rank >= 0 && this.rank <= 8) {
            return this.rank + 2; // 2 to 10
        } else if (this.rank >= 9 && this.rank <= 11) {
            return 10; // Jack, Queen, King
        } else if (this.rank == 12) {
            return 11; // Ace, default to 11
        } else {
            return 0; // Should not happen
        }
    }

    public int getAlternativeAceValue() {
        return this.rank == 12 ? 1 : getRankValue(); // Ace can also be worth 1
    }

    public String getColour() {
        if (this.getSuit().equals("Diamonds") || this.getSuit().equals("Hearts")) {
            return "Red";
        } else {
            return "Black";
        }
    }

    public boolean isBiggerThan(Card anotherCard) {
        return this.rank > anotherCard.rank;
    }

    @Override
    public String toString() {
        return getRank() + " of " + getSuit();
    }

    @Override
    public int compareTo(Card otherCard) {
        return Integer.compare(this.getRankValue(), otherCard.getRankValue());
    }
}