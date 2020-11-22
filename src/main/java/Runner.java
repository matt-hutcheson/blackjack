import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Runner {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Deck deck = new Deck();
        deck.populate();
        deck.shuffle();
        Game game = new Game(deck);

        System.out.println("Welcome to Blackjack!");
        System.out.println("How many players would like to play?: ");

        String input = scanner.next();
        int players = parseInt(input);

        game.addDealer();

        for (int i = 1; i <= players; i++) {
            String prompt = String.format("Player %s, enter your name: ", (i));
            System.out.println(prompt);
            String playerName = scanner.next();
            Player player = new Player(playerName);
            game.addPlayer(player);
        }

        System.out.println("Deal cards? (Y/N): ");
        String deal = scanner.next().toLowerCase();
        do {
            if (deal.matches("y") || deal.matches("yes")) {
                for (Player player : game.getPlayers()) {
                    game.dealCard(player);
                    game.dealCard(player);
                    player.calcHandScores();
                }
                for (int i = 1; i < game.getPlayers().size(); i++) {
                    Player player = game.getPlayers().get(i);
                    do {
                        System.out.println("Dealer has: ");
                        System.out.println("Card 1: " + game.getDealer().getCard(0).getRank() + " of " + game.getDealer().getCard(0).getSuit());
                        System.out.println("Card 2: hidden");
                        System.out.println(player.getName() + " has: ");
                        for (int j = 0; j < player.getHand().size(); j++) {
                            Card card = player.getHand().get(j);
                            System.out.println("Card " + (i+1) + ": " + card.getRank() + " of " + card.getSuit());
                        }
                        player.calcHandScores();
                        player.checkIfBust();
                        if (!player.getBust()) {
                            System.out.println("Stick/twist?: ");
                            String choice = scanner.next().toLowerCase();
                            if (choice.matches("twist")) {
                                game.dealCard(player);
                            } else if (choice.matches("stick")) {
                                player.setStuck(true);
                            }
                        }
                    } while (!player.getBust() && !player.getStuck());
                    if (player.getStuck()) {
                        System.out.println(player.getName() + " has stuck on " + player.getBestScore());
                    } else if (player.getBust()) {
                        System.out.println(player.getName() + " is bust with " + player.getResults().get(0));
                    }
                }
                System.out.println("Dealer's Turn.");
                game.dealerTurn();
                game.calcResults();
                System.out.println("Dealer has: ");
                System.out.println("Card 1: " + game.getDealer().getCard(0).getRank() + " of " + game.getDealer().getCard(0).getSuit());
                System.out.println("Card 2: " + game.getDealer().getCard(1).getRank() + " of " + game.getDealer().getCard(1).getSuit());
                if (game.getDealer().getHand().size() > 2) {
                    for (int i = 2; i < game.getDealer().getHand().size(); i++) {
                        System.out.println("Dealer draws");
                        System.out.println("Card " + game.getDealer().getHand().get(i).getRank() + " of " + game.getDealer().getHand().get(i).getSuit());
                    }
                }
                if (game.getDealer().getBust()) {
                    System.out.println("Dealer is bust with " + game.getDealer().getResults().get(0));
                } else if (game.getDealer().getStuck()) {
                    System.out.println("Dealer sticks with " + game.getDealer().getBestScore());
                }
                if (game.getResults().get("Winners").size() > 0) {
                    for (Player player : game.getResults().get("Winners")) {
                        System.out.println(player.getName() + " wins! Beating the dealer with " + player.getBestScore());
                    }
                } else if (game.getResults().get("Draws").size() > 0) {
                    for (Player player : game.getResults().get("Draws")) {
                        System.out.println(player.getName() + " draws with the dealer with " + player.getBestScore());
                    }
                } else if (game.getResults().get("Losers").size() > 0) {
                    for (Player player : game.getResults().get("Losers")) {
                        System.out.println(player.getName() + " loses to the dealer with " + player.getBestScore());
                    }
                }
                game.nextTurnReset();
                System.out.println("Play again? (Y/N): ");
                deal = scanner.next().toLowerCase();
            }
        } while (deal.matches("y"));
    }
}
