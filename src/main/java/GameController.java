import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
// Add this import at the top with other imports
import javafx.animation.PauseTransition;
import javafx.util.Duration;

import java.util.ArrayList;

public class GameController {

    private JavaFXTemplate mainApp;
    private Dealer dealer;
    private Player player1;
    private Player player2;

    // UI components for Dealer hand
    @FXML
    private HBox dealerHand;

    // UI components for Player 1
    @FXML
    private TextField player1PlayBet;
    @FXML
    private TextField player2PlayBet;
    @FXML
    private TextField player1PairPlusBet;
    @FXML
    private TextField player1AnteBet;
    @FXML
    private HBox player1Hand;
    @FXML
    private Label player1Winnings;
    @FXML
    private Button player1DealButton;
    @FXML
    private Button player1PlayButton;
    @FXML
    private Button player1FoldButton;

    // UI components for Player 2
    @FXML
    private TextField player2PairPlusBet;
    @FXML
    private TextField player2AnteBet;
    @FXML
    private HBox player2Hand;
    @FXML
    private Label player2Winnings;
    @FXML
    private Button player2DealButton;
    @FXML
    private Button player2PlayButton;
    @FXML
    private Button player2FoldButton;

    // Play Log
    @FXML
    private TextArea playLog;

    public GameController() {
        dealer = new Dealer();
        player1 = new Player();
        player2 = new Player();
    }

    @FXML
    public void initialize() {
        player1Hand.setStyle("-fx-spacing: 20px; -fx-alignment: center; -fx-padding: 15px; " +
                "-fx-background-color: rgba(0, 0, 0, 0.7); -fx-border-radius: 10px;");
        player2Hand.setStyle("-fx-spacing: 20px; -fx-alignment: center; -fx-padding: 15px; " +
                "-fx-background-color: rgba(0, 0, 0, 0.7); -fx-border-radius: 10px;");
        dealerHand.setStyle("-fx-spacing: 20px; -fx-alignment: center; -fx-padding: 15px; " +
                "-fx-background-color: rgba(0, 0, 0, 0.7); -fx-border-radius: 10px;");

        // Style for the play log
        player1PlayButton.setDisable(true);
        player1FoldButton.setDisable(true);
        player2PlayButton.setDisable(true);
        player2FoldButton.setDisable(true);
    }

    @FXML
    private void handlePlayer1Deal() {
        try {
            int anteBet = Integer.parseInt(player1AnteBet.getText());
            int pairPlusBet = Integer.parseInt(player1PairPlusBet.getText());
            player1.setAnteBet(anteBet);
            player1.setPairPlusBet(pairPlusBet);
        } catch (NumberFormatException e) {
            playLog.appendText("Invalid bet amount for Player 1.\n");
            return;
        }

        ArrayList<Card> player1Cards = dealer.dealHand();
        player1.setPlayerHand(player1Cards);
        displayCards(player1Hand, player1Cards);

        player1PlayButton.setDisable(false);
        player1FoldButton.setDisable(false);
        player1DealButton.setDisable(true);

        playLog.appendText("Player 1 dealt cards: " + player1Cards.toString() + "\n");
        playLog.appendText("Player 1 Ante Bet: $" + player1.getAnteBet() +
                ", Pair Plus Bet: $" + player1.getPairPlusBet() + "\n");
    }

    @FXML
    private void handlePlayer1Play() {
        int anteBet = Integer.parseInt(player1AnteBet.getText());
        player1PlayBet.setText(String.valueOf(anteBet));
        player1.setPlayBet(anteBet);
        playLog.appendText("Player 1 places play bet of $" + anteBet + "\n");
        player1PlayButton.setDisable(true);
        player1FoldButton.setDisable(true);

        checkForResults();
    }

    @FXML
    private void handlePlayer1Fold() {
        playLog.appendText("Player 1 folds with hand: " + player1.getPlayerHand().toString() + "\n");
        playLog.appendText("Player 1 loses ante bet of $" + player1.getAnteBet() + "\n");
        player1.updateTotalWinnings(-player1.getAnteBet());
        player1Winnings.setText("$" + player1.getTotalWinnings());
        player1PlayButton.setDisable(true);
        player1FoldButton.setDisable(true);

        checkForResults();
    }

    @FXML
    private void handlePlayer2Deal() {
        try {
            int anteBet = Integer.parseInt(player2AnteBet.getText());
            int pairPlusBet = Integer.parseInt(player2PairPlusBet.getText());
            player2.setAnteBet(anteBet);
            player2.setPairPlusBet(pairPlusBet);
        } catch (NumberFormatException e) {
            playLog.appendText("Invalid bet amount for Player 2.\n");
            return;
        }

        ArrayList<Card> player2Cards = dealer.dealHand();
        player2.setPlayerHand(player2Cards);
        displayCards(player2Hand, player2Cards);

        player2PlayButton.setDisable(false);
        player2FoldButton.setDisable(false);
        player2DealButton.setDisable(true);

        playLog.appendText("Player 2 dealt cards: " + player2Cards.toString() + "\n");
        playLog.appendText("Player 2 Ante Bet: $" + player2.getAnteBet() +
                ", Pair Plus Bet: $" + player2.getPairPlusBet() + "\n");
    }

    @FXML
    private void handlePlayer2Play() {
        int anteBet = Integer.parseInt(player2AnteBet.getText());
        player2PlayBet.setText(String.valueOf(anteBet));
        player2.setPlayBet(anteBet);
        playLog.appendText("Player 2 places play bet of $" + anteBet + "\n");
        player2PlayButton.setDisable(true);
        player2FoldButton.setDisable(true);

        checkForResults();
    }

    @FXML
    private void handlePlayer2Fold() {
        playLog.appendText("Player 2 folds with hand: " + player2.getPlayerHand().toString() + "\n");
        playLog.appendText("Player 2 loses ante bet of $" + player2.getAnteBet() + "\n");
        player2.updateTotalWinnings(-player2.getAnteBet());
        player2Winnings.setText("$" + player2.getTotalWinnings());
        player2PlayButton.setDisable(true);
        player2FoldButton.setDisable(true);

        checkForResults();
    }

    private void checkForResults() {
        // Check if both players have made their decisions
        if ((player1PlayButton.isDisable() && player1FoldButton.isDisable()) &&
                (player2PlayButton.isDisable() && player2FoldButton.isDisable())) {

            // Deal and display dealer's hand
            ArrayList<Card> dealerCards = dealer.dealDealersHand();
            displayCards(dealerHand, dealerCards);
            playLog.appendText("\nDealer's hand revealed: " + dealerCards.toString() + "\n");

            // Determine outcomes for both players
            determineOutcome(player1, dealerCards, player1Winnings);
            determineOutcome(player2, dealerCards, player2Winnings);

            playLog.appendText("\nFinal Winnings:\n");
            playLog.appendText("Player 1 Total Winnings: $" + player1.getTotalWinnings() + "\n");
            playLog.appendText("Player 2 Total Winnings: $" + player2.getTotalWinnings() + "\n");

            // Create pause transition of 5 seconds
            PauseTransition pause = new PauseTransition(Duration.seconds(5));
            pause.setOnFinished(event -> {
                // Clear all hands
                dealerHand.getChildren().clear();
                player1Hand.getChildren().clear();
                player2Hand.getChildren().clear();

                // Clear all bet fields
                player1AnteBet.clear();
                player1PairPlusBet.clear();
                player1PlayBet.clear();
                player2AnteBet.clear();
                player2PairPlusBet.clear();
                player2PlayBet.clear();

                // Enable deal buttons after pause
                player1DealButton.setDisable(false);
                player2DealButton.setDisable(false);
                playLog.appendText("\nCards cleared and bet fields reset. Ready for next round.\n");
            });
            pause.play();
        }
    }

    private void determineOutcome(Player player, ArrayList<Card> dealerCards, Label winningsLabel) {
        String playerIdentifier = (player == player1) ? "Player 1" : "Player 2";
        int totalWinnings = 0;

        // First evaluate pair plus bet - this is independent of dealer's hand
        int playerHandRank = ThreeCardLogic.evalHand(player.getPlayerHand());
        String handType = "";
        switch(playerHandRank) {
            case 0: handType = "High Card"; break;
            case 1: handType = "Straight Flush"; break;
            case 2: handType = "Three of a Kind"; break;
            case 3: handType = "Straight"; break;
            case 4: handType = "Flush"; break;
            case 5: handType = "Pair"; break;
        }

        // Handle pair plus bet outcome FIRST and SEPARATELY
        if (player.getPairPlusBet() > 0) {  // Only evaluate if there was a pair plus bet
            if (playerHandRank > 0 && playerHandRank <= 5) {  // Has a pair or better
                int pairPlusWinnings = calculatePairPlusWinnings(playerHandRank, player.getPairPlusBet());
                totalWinnings += pairPlusWinnings;
                playLog.appendText(playerIdentifier + " won $" + pairPlusWinnings +
                        " on Pair Plus bet with " + handType + "!\n");
            } else {
                totalWinnings -= player.getPairPlusBet();
                playLog.appendText(playerIdentifier + " lost $" + player.getPairPlusBet() +
                        " on Pair Plus bet - no pair or better.\n");
            }
        }

        // Now handle the ante and play bet comparison with dealer - SEPARATE from pair plus
        int result = ThreeCardLogic.compareHands(dealerCards, player.getPlayerHand());
        int dealerHandRank = ThreeCardLogic.evalHand(dealerCards);

        if (result == 2) {  // Player wins
            int antePlayWin = player.getAnteBet() + player.getPlayBet();
            totalWinnings += antePlayWin;
            playLog.appendText(playerIdentifier + " wins with " + handType + "!\n");
            playLog.appendText(playerIdentifier + "'s hand: " + player.getPlayerHand().toString() + "\n");
            playLog.appendText(playerIdentifier + " wins $" + antePlayWin + " from ante and play bets\n");
        } else if (result == 1) {  // Dealer wins
            int antePLayLoss = player.getAnteBet() + player.getPlayBet();
            totalWinnings -= antePLayLoss;
            String dealerHandType = "";
            switch(dealerHandRank) {
                case 0: dealerHandType = "High Card"; break;
                case 1: dealerHandType = "Straight Flush"; break;
                case 2: dealerHandType = "Three of a Kind"; break;
                case 3: dealerHandType = "Straight"; break;
                case 4: dealerHandType = "Flush"; break;
                case 5: dealerHandType = "Pair"; break;
            }
            playLog.appendText("Dealer wins against " + playerIdentifier + " with " + dealerHandType + "\n");
            playLog.appendText(playerIdentifier + "'s hand: " + player.getPlayerHand().toString() + "\n");
            playLog.appendText(playerIdentifier + " loses $" + antePLayLoss + " from ante and play bets\n");
        } else {  // Push/Tie
            playLog.appendText("Push! " + playerIdentifier + " and Dealer both have " + handType + "\n");
            playLog.appendText(playerIdentifier + "'s hand: " + player.getPlayerHand().toString() + "\n");
            playLog.appendText(playerIdentifier + " keeps their ante and play bets\n");
        }

        // Update total winnings
        player.updateTotalWinnings(totalWinnings);
        winningsLabel.setText("$" + player.getTotalWinnings());
    }

    private int calculatePairPlusWinnings(int handRank, int pairPlusBet) {
        switch(handRank) {
            case 1: return pairPlusBet * 40;  // Straight Flush
            case 2: return pairPlusBet * 30;  // Three of a Kind
            case 3: return pairPlusBet * 6;   // Straight
            case 4: return pairPlusBet * 3;   // Flush
            case 5: return pairPlusBet;       // Pair
            default: return -pairPlusBet;     // Loss
        }
    }

    private void displayCards(HBox handBox, ArrayList<Card> hand) {
        handBox.getChildren().clear();
        for (Card card : hand) {
            Label cardLabel = new Label(card.toString());
            cardLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #ffffff; " +
                    "-fx-padding: 10px 15px; -fx-background-color: rgba(0, 0, 0, 0.8); " +
                    "-fx-border-radius: 8px; -fx-background-radius: 8px; -fx-alignment: center;");
            handBox.getChildren().add(cardLabel);
        }
    }

    private void resetForNextRound() {
        player1DealButton.setDisable(false);
        player2DealButton.setDisable(false);

        dealerHand.getChildren().clear();
        player1Hand.getChildren().clear();
        player2Hand.getChildren().clear();
        playLog.appendText("\nReady for next round.\n");
    }

    public void setMainApp(JavaFXTemplate mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleExit() {
        if (mainApp != null) {
            mainApp.showExitScreen();
        }
    }

    @FXML
    private void handleFreshStart() {
        resetForNextRound();
        player1.updateTotalWinnings(-player1.getTotalWinnings());
        player2.updateTotalWinnings(-player2.getTotalWinnings());

        // Reset the display labels to show $0
        player1Winnings.setText("$0");
        player2Winnings.setText("$0");

        // Clear any text in bet fields
        player1AnteBet.clear();
        player1PairPlusBet.clear();
        player1PlayBet.clear();
        player2AnteBet.clear();
        player2PairPlusBet.clear();
        player2PlayBet.clear();

        playLog.appendText("Fresh start - All bets and winnings reset to $0.\n");
    }

    @FXML
    private void handleNewLook() {
        if (mainApp != null) {
            mainApp.applyNewLook();
        }
    }
}