import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

// Main class for the Suit21 game
public class Suit21Game {
    private ArrayList<Player> players; // List of players participating in the game
    private ArrayList<Player> playerOrder; // Preserves the original player order
    private Deck deck; // The deck of cards used in the game
    private int numberOfGames; // Number of games to be played
    private Scanner scanner;

    // Constructor initializes players, deck, and number of games
    public Suit21Game(int numberOfPlayers, int numberOfGames) {
        players = new ArrayList<>();
        playerOrder = new ArrayList<>();
        deck = new Deck();
        this.numberOfGames = numberOfGames;
        scanner = new Scanner(System.in);
        initializePlayers(numberOfPlayers); // Set up players
        playerOrder.addAll(players); // Save the original player order
    }

    // Resets the player list to its original order
    private void resetPlayerOrder() {
        // Restore the players list to its original order
        players.clear();
        players.addAll(playerOrder);
    }

    // Prompts the user to enter player names and initializes players
    private void initializePlayers(int numberOfPlayers) {
        int computerCount = 0; // Tracks the number of computer players

        for (int i = 0; i < numberOfPlayers; i++) {
            String name;

            // Loop to ensure valid input for player names
            do {
                System.out.print("Enter name for Player " + (i + 1) + ": ");
                name = scanner.nextLine();

                if (name.length() > 20) {
                    System.out.println("Name is too long! Please enter a name with 20 characters or fewer.\n");
                    continue;
                }

                if (name.isEmpty()) {
                    System.out.println("Invalid input. Please enter a name.\n");
                }
            } while (name.isEmpty() || name.length() > 20);

            // If the name is "computer", create a ComputerPlayer
            if (name.equalsIgnoreCase("computer")) {
                computerCount++;
                if (computerCount == 1) {
                    name = "Computer";
                } else {
                    name = "Computer " + computerCount;
                }
                players.add(new ComputerPlayer(name));
            } else {
                players.add(new Player(name));
            }
        }
    }

    // Checks if the card selection input is valid
    private boolean isValidCardSelection(String input) {
        return input.length() == 1 && input.charAt(0) >= 'A' && input.charAt(0) <= 'E';
    }

    // Awards points to players who achieved a score of 21
    private void awardPoints() {
        ArrayList<Player> winners = new ArrayList<>();

        System.out.println("\nWinners:");
        for (Player player : players) {
            if (player.hasScoreOf21()) {
                winners.add(player);
                System.out.println(player.getName());
            }
        }
        System.out.print("\n");
        if (!winners.isEmpty()) {
            double points = 1.0 / winners.size();
            for (Player winner : winners) {
                winner.addPoints(points);
            }
        } else {
            System.out.println("No player scored 21 this game. No points awarded");
        }
    }

    // Pauses the game until the player presses Enter
    private void waitForPlayer() {
        System.out.print("Press Enter to continue...");
        scanner.nextLine(); // Waits for the player to press Enter
    }

    // Handles rounds of gameplay and logs replay data
    private void playRounds(Replay replay) {
        int roundNumber = 1;
        boolean gameWon = false;

        // Loop through rounds until a game is won or the deck is empty
        while (!gameWon && deck.cardsRemaining() >= players.size()) {
            for (Player player : players) {
                System.out.println("\n--------------------");
                System.out.println(player.getName() + " (Round " + roundNumber + ")");
                System.out.println("--------------------");

                // Deal initial hand for the first round
                if (roundNumber == 1) {
                    player.clearHand();
                    for (int i = 0; i < 5; i++) {
                        player.addCard(deck.dealCard());
                    }
                }

                // Display game state information
                System.out.println("Deck Remaining: " + deck.cardsRemaining());
                System.out.println("\nYour current hand:");
                player.displayHand();
                System.out.println("\nYour suit scores:");
                System.out.println(player.getSuitScores());

                // Record state before swapping cards
                List<Card> handBeforeSwap = new ArrayList<>(player.getHand());
                String suitScoresBeforeSwap = player.getSuitScores();

                // Handle card swap logic for computer and human players
                if (player instanceof ComputerPlayer) {
                    Card newCard = deck.dealCard();
                    ((ComputerPlayer) player).swapCard(newCard, -1);
                    Card replacedCard = ((ComputerPlayer) player).getReplacedCard();
                    player.setLastDecision("Replaced " + replacedCard + " with " + newCard);
                } else {
                    System.out.print("\nEnter the letter (A-E) of the card you want to swap: ");
                    String input = scanner.nextLine().toUpperCase();

                    // Validate and handle input for card swap
                    while (!isValidCardSelection(input)) {
                        System.out.print("Invalid selection. Please enter a letter between A and E:");
                        input = scanner.nextLine().toUpperCase();
                        System.out.println("\n");
                    }

                    int index = input.charAt(0) - 'A';

                    Card replacedCard = player.getHand().get(index);
                    Card newCard = deck.dealCard();
                    player.swapCard(newCard, index);
                    player.setLastDecision("Replaced " + replacedCard + " with " + newCard);
                }

                // Record state after swapping cards
                List<Card> handAfterSwap = new ArrayList<>(player.getHand());
                String suitScoresAfterSwap = player.getSuitScores();

                // Log round details to replay
                replay.logRound(roundNumber, player, handBeforeSwap, suitScoresBeforeSwap, handAfterSwap, suitScoresAfterSwap);

                // Display updated hand and suit scores
                System.out.println("\nYour new hand:");
                player.displayHand();
                System.out.println("\nYour suit scores:");
                System.out.println(player.getSuitScores());
                System.out.println("\nYour turn is over\n");

                // Check if the player has won
                if (player.hasScoreOf21()) {
                    System.out.println(player.getName() + " has 21!\n");
                    gameWon = true;
                }

                // Check if the deck is empty
                if (deck.cardsRemaining() == 0) {
                    System.out.println("Deck is empty. No points awarded\nGame Over!");
                    return;
                }

                waitForPlayer(); // Pause before the next player's turn
            }
            roundNumber++;
        }

        // Handle end of game conditions if deck runs out
        if (deck.cardsRemaining() < players.size() || !gameWon) {
            System.out.println("\nDeck does not have enough cards for all players. Game Over!");
            System.out.println("No points will be awarded for this game.\n");
        } else {
            awardPoints();
        }
    }

    // Displays scores sorted by maximum score
    private void displayScores() {
        players.sort((p1, p2) -> Integer.compare(p2.calculateMaxScore(), p1.calculateMaxScore()));
        for (Player player : playerOrder) {
            System.out.println(player.getName() + "'s score: " + player.calculateMaxScore());
        }
    }

    // Displays final scores sorted by total points
    private void displayFinalScores() {
        players.sort((p1, p2) -> Double.compare(p2.getPoints(), p1.getPoints()));
        System.out.println("\nFinal Scores:");
        int scoreWidth = 25;

        for (Player player : playerOrder) {
            System.out.printf("%-" + scoreWidth + "s %.2f points\n", player.getName(), player.getPoints());
        }
    }

    // Starts the game and manages the gameplay flow
    public void startGame() {
        for (int game = 0; game < numberOfGames; game++) {
            resetPlayerOrder(); // Reset player order for each game
            System.out.println("\n**************\n*** Game " + (game + 1) + " ***\n**************");
            deck = new Deck();
            deck.shuffle();

            Replay replay = new Replay();
            playRounds(replay);
            displayScores();

            // Prompt the user to view the replay
            String input;
            do {
                System.out.print("\nWould you like to view the replay for this game? (Y/N): ");
                input = scanner.nextLine().trim().toLowerCase();
                if (!input.equals("y") && !input.equals("n")) {
                    System.out.println("Invalid input. Please enter 'Y' for yes or 'N' for no");
                }
            } while (!input.equals("y") && !input.equals("n"));

            if (input.equals("y")) {
                replay.displayReplay();
            }
        }
        displayFinalScores();
    }

    // Main method - Entry point for the application
    public static void main(String[] args) {
        System.out.println("**************************\n*** WELCOME TO SUIT-21 ***\n**************************");
        Scanner scanner = new Scanner(System.in);

        // Prompt for the number of games
        int numberOfGames = -1;
        while (numberOfGames <= 0 || numberOfGames > 5) {
            System.out.print("\nEnter number of games (1-5): ");
            String input = scanner.nextLine();
            try {
                numberOfGames = Integer.parseInt(input);
                if (numberOfGames <= 0 || numberOfGames > 5) {
                    System.out.println("Invalid number of games! Please enter a number between 1 and 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number for the number of games.");
            }
        }

        // Prompt for the number of players
        int numberOfPlayers = -1;
        while (numberOfPlayers < 2 || numberOfPlayers > 6) {
            System.out.print("\nEnter number of players (2-6): ");
            String input = scanner.nextLine();
            try {
                numberOfPlayers = Integer.parseInt(input);
                if (numberOfPlayers < 2 || numberOfPlayers > 6) {
                    System.out.println("Invalid number of players. Please enter a number between 2 and 6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number between 2 and 6.");
            }
        }

        // Create a new game instance and start the game
        Suit21Game game = new Suit21Game(numberOfPlayers, numberOfGames);
        game.startGame();
    }
}