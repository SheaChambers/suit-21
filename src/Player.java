import java.util.ArrayList;

class Player {
    private String name; // The name of the player
    private ArrayList<Card> hand; // The player's hand, represented as a list of Card objects
    private double points; // The player's current points in the game
    private String lastDecision; // The player's last action or decision, stored as a string


    public Player(String name) {
        this.name = name;
        hand = new ArrayList<>();
        points = 0;
    }

    // Returns the player's name
    public String getName() {
        return name;
    }

    // Adds a card to the player's hand
    public void addCard(Card card) {
        hand.add(card);
    }

    // Clears the player's hand, removing all cards
    public void clearHand() {
        hand.clear();
    }

    // Displays the player's hand with letter labels (e.g., A. Card1, B. Card2)
    public void displayHand() {
        char letter = 'A';
        for (Card card : hand) {
            System.out.println(letter + ". " + card);
            letter++;
        }
    }

    // Swaps a card in the player's hand at the specified index with a new card
    public void swapCard(Card newCard, int index) {
        if (index >= 0 && index < hand.size()) {
            Card oldCard = hand.get(index); // Store the card being replaced
            hand.set(index, newCard); // Replace the card
            setLastDecision("Replaced " + oldCard + " with " + newCard); // Record the decision
            System.out.println("Replacing " + hand.get(index) + " with " + newCard);
        } else {
            System.out.println("Invalid index. No card swapped."); // Handle invalid index
        }
    }

    // Calculates the maximum score possible for the player based on their hand
    public int calculateMaxScore() {
        int maxScore = 0;

        // Iterate through each suit and calculate the score
        for (String suit : new String[]{"Hearts", "Diamonds", "Clubs", "Spades"}) {
            int score = 0;
            int aceCount = 0;

            // Calculate the score for the current suit
            for (Card card : hand) {
                if (card.getSuit().equals(suit)) {
                    int rankValue = card.getRankValue();
                    if (card.getRank().equals("Ace")) {
                        rankValue = 11; // Treat Ace as 11 initially
                        aceCount++;
                    }
                    score += rankValue;
                }
            }

            // Adjust score if it exceeds 21 by treating some Aces as 1
            while (score > 21 && aceCount > 0) {
                score -= 10;
                aceCount--;
            }

            // Update the maximum score if this suit's score is higher
            if (score == 21) {
                return 21;
            } else if (score > maxScore) {
                maxScore = score;
            }
        }
        return maxScore;
    }

    // Returns a string representing scores for each suit in the player's hand
    public String getSuitScores() {
        int heartsScore = 0, diamondsScore = 0, clubsScore = 0, spadesScore = 0;
        int heartsAces = 0, diamondsAces = 0, clubsAces = 0, spadesAces = 0;

        // Calculate scores for each suit
        for (Card card : hand) {
            switch (card.getSuit()) {
                case "Hearts":
                    if (card.getRank().equals("Ace")) {
                        heartsScore += 11; // Treat Ace as 11 initially
                        heartsAces++;
                    } else {
                        heartsScore += card.getRankValue();
                    }
                    break;
                case "Diamonds":
                    if (card.getRank().equals("Ace")) {
                        diamondsScore += 11;
                        diamondsAces++;
                    } else {
                        diamondsScore += card.getRankValue();
                    }
                    break;
                case "Clubs":
                    if (card.getRank().equals("Ace")) {
                        clubsScore += 11;
                        clubsAces++;
                    } else {
                        clubsScore += card.getRankValue();
                    }
                    break;
                case "Spades":
                    if (card.getRank().equals("Ace")) {
                        spadesScore += 11;
                        spadesAces++;
                    } else {
                        spadesScore += card.getRankValue();
                    }
                    break;
            }
        }

        // Adjust scores for each suit to handle Aces if score exceeds 21
        while (heartsScore > 21 && heartsAces > 0) {
            heartsScore -= 10;
            heartsAces--;
        }

        while (diamondsScore > 21 && diamondsAces > 0) {
            diamondsScore -= 10;
            diamondsAces--;
        }

        while (clubsScore > 21 && clubsAces > 0) {
            clubsScore -= 10;
            clubsAces--;
        }

        while (spadesScore > 21 && spadesAces > 0) {
            spadesScore -= 10;
            spadesAces--;
        }

        // Return formatted scores for all suits
        return "Hearts: " + heartsScore + ", Diamonds: " + diamondsScore +
                ", Clubs: " + clubsScore + ", Spades: " + spadesScore;
    }

    // Checks if the player has a score of exactly 21 in any suit
    public boolean hasScoreOf21() {
        int heartsScore = 0, diamondsScore = 0, clubsScore = 0, spadesScore = 0;
        int heartsAces = 0, diamondsAces = 0, clubsAces = 0, spadesAces = 0;

        for (Card card : hand) {
            switch (card.getSuit()) {
                case "Hearts":
                    if (card.getRank().equals("Ace")) {
                        heartsScore += 11;
                        heartsAces++;
                    } else {
                        heartsScore += card.getRankValue();
                    }
                    break;
                case "Diamonds":
                    if (card.getRank().equals("Ace")) {
                        diamondsScore += 11;
                        diamondsAces++;
                    } else {
                        diamondsScore += card.getRankValue();
                    }
                    break;
                case "Clubs":
                    if (card.getRank().equals("Ace")) {
                        clubsScore += 11;
                        clubsAces++;
                    } else {
                        clubsScore += card.getRankValue();
                    }
                    break;
                case "Spades":
                    if (card.getRank().equals("Ace")) {
                        spadesScore += 11;
                        spadesAces++;
                    } else {
                        spadesScore += card.getRankValue();
                    }
                    break;
            }
        }

        while (heartsScore > 21 && heartsAces > 0) {
            heartsScore -= 10;
            heartsAces--;
        }

        while (diamondsScore > 21 && diamondsAces > 0) {
            diamondsScore -= 10;
            diamondsAces--;
        }

        while (clubsScore > 21 && clubsAces > 0) {
            clubsScore -= 10;
            clubsAces--;
        }

        while (spadesScore > 21 && spadesAces > 0) {
            spadesScore -= 10;
            spadesAces--;
        }

        return heartsScore == 21 || diamondsScore == 21 || clubsScore == 21 || spadesScore == 21;
    }

    // Adds points to the player's total score
    public void addPoints(double points) {
        this.points += points;
    }

    // Returns the player's total score
    public double getPoints() {
        return points;
    }

    // Returns the player's hand as a list of Card objects
    public ArrayList<Card> getHand() {
        return hand;
    }

    // Records the player's last decision
    public void setLastDecision(String decision) {
        this.lastDecision = decision;
    }

    // Returns the player's last decision, or a default message if none exists
    public String getLastDecision() {
        return lastDecision == null ? "No decision made" : lastDecision;
    }
}