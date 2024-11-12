import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

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
        player1PlayButton.setDisable(true);
        player1FoldButton.setDisable(true);
        player2PlayButton.setDisable(true);
        player2FoldButton.setDisable(true);
    }

    @FXML
    private void handlePlayer1Deal() {
        try {
            player1.setAnteBet(Integer.parseInt(player1AnteBet.getText()));
            player1.setPairPlusBet(Integer.parseInt(player1PairPlusBet.getText()));
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

        playLog.appendText("Player 1 dealt cards.\n");
    }

    @FXML
    private void handlePlayer1Play() {
        int anteBet = Integer.parseInt(player1AnteBet.getText());
        player1PlayBet.setText(String.valueOf(anteBet));  // Play bet equals Ante bet
        player1.setPlayBet(anteBet);
        playLog.appendText("Player 1 places play bet.\n");
        player1PlayButton.setDisable(true);
        player1FoldButton.setDisable(true);

        checkForResults();
    }

    @FXML
    private void handlePlayer1Fold() {
        playLog.appendText("Player 1 folds.\n");
        player1.updateTotalWinnings(-player1.getAnteBet());
        player1Winnings.setText("$" + player1.getTotalWinnings());
        player1PlayButton.setDisable(true);
        player1FoldButton.setDisable(true);

        checkForResults();
    }

    @FXML
    private void handlePlayer2Deal() {
        try {
            player2.setAnteBet(Integer.parseInt(player2AnteBet.getText()));
            player2.setPairPlusBet(Integer.parseInt(player2PairPlusBet.getText()));
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

        playLog.appendText("Player 2 dealt cards.\n");
    }

    @FXML
    private void handlePlayer2Play() {
        int anteBet = Integer.parseInt(player2AnteBet.getText());
        player2PlayBet.setText(String.valueOf(anteBet));  // Play bet equals Ante bet
        player2.setPlayBet(anteBet);
        playLog.appendText("Player 2 places play bet.\n");
        player2PlayButton.setDisable(true);
        player2FoldButton.setDisable(true);

        checkForResults();
    }

    @FXML
    private void handlePlayer2Fold() {
        playLog.appendText("Player 2 folds.\n");
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

            // Debug logging
//            playLog.appendText("Dealer's hand revealed: " + dealerCards.toString() + "\n");
//            playLog.appendText("Current deck state: " + dealer.dealersHand.toString() + "\n");

            // Determine outcomes for both players
            determineOutcome(player1, dealerCards, player1Winnings);
            determineOutcome(player2, dealerCards, player2Winnings);

            playLog.appendText("Results displayed.\n");

            // Only enable the deal buttons, don't reset/clear the cards
            player1DealButton.setDisable(false);
            player2DealButton.setDisable(false);
        }
    }

    private void determineOutcome(Player player, ArrayList<Card> dealerCards, Label winningsLabel) {
        int result = ThreeCardLogic.compareHands(dealerCards, player.getPlayerHand());
        int playerHandRank = ThreeCardLogic.evalHand(player.getPlayerHand());
        int dealerHandRank = ThreeCardLogic.evalHand(dealerCards);
        int winnings = 0;

        String handType = "";
        switch(playerHandRank) {
            case 0: handType = "High Card"; break;
            case 1: handType = "Straight Flush"; break;
            case 2: handType = "Three of a Kind"; break;
            case 3: handType = "Straight"; break;
            case 4: handType = "Flush"; break;
            case 5: handType = "Pair"; break;
        }

        if (result == 2) {  // Player wins
            winnings = player.getAnteBet() + player.getPlayBet();
            playLog.appendText("Player wins with " + handType + "!\n");
            playLog.appendText("Player's hand: " + player.getPlayerHand().toString() + "\n");
            playLog.appendText("Dealer's hand: " + dealerCards.toString() + "\n");
        } else if (result == 1) {  // Dealer wins
            winnings = -player.getAnteBet();
            String dealerHandType = "";
            switch(dealerHandRank) {
                case 0: dealerHandType = "High Card"; break;
                case 1: dealerHandType = "Straight Flush"; break;
                case 2: dealerHandType = "Three of a Kind"; break;
                case 3: dealerHandType = "Straight"; break;
                case 4: dealerHandType = "Flush"; break;
                case 5: dealerHandType = "Pair"; break;
            }
            playLog.appendText("Dealer wins with " + dealerHandType + "\n");
            playLog.appendText("Player's hand: " + player.getPlayerHand().toString() + "\n");
            playLog.appendText("Dealer's hand: " + dealerCards.toString() + "\n");
        } else {  // Push/Tie
            playLog.appendText("Push! Both have " + handType + "\n");
            playLog.appendText("Player's hand: " + player.getPlayerHand().toString() + "\n");
            playLog.appendText("Dealer's hand: " + dealerCards.toString() + "\n");
        }

        // Handle pair plus bet outcome
        if (playerHandRank > 0 && playerHandRank <= 5) {  // Has a pair or better
            int pairPlusWinnings = calculatePairPlusWinnings(playerHandRank, player.getPairPlusBet());
            winnings += pairPlusWinnings;
            if (pairPlusWinnings > 0) {
                playLog.appendText("Won Pair Plus bet with " + handType + "!\n");
            }
        } else {
            winnings -= player.getPairPlusBet();
            playLog.appendText("Lost Pair Plus bet - no pair or better.\n");
        }

        player.updateTotalWinnings(winnings);
        winningsLabel.setText("$" + player.getTotalWinnings());
    }

    private int calculatePairPlusWinnings(int handRank, int pairPlusBet) {
        switch(handRank) {
            case 1: return pairPlusBet * 40;  // Straight Flush
            case 2: return pairPlusBet * 30;  // Three of a Kind
            case 3: return pairPlusBet * 6;   // Straight
            case 4: return pairPlusBet * 3;   // Flush
            case 5: return pairPlusBet * 1;   // Pair
            default: return -pairPlusBet;     // Loss
        }
    }

    private void displayCards(HBox handBox, ArrayList<Card> hand) {
        handBox.getChildren().clear();
        for (Card card : hand) {
            Label cardLabel = new Label(card.toString());
            handBox.getChildren().add(cardLabel);
        }
    }

    private void resetForNextRound() {
        player1DealButton.setDisable(false);
        player2DealButton.setDisable(false);

        dealerHand.getChildren().clear();
        player1Hand.getChildren().clear();
        player2Hand.getChildren().clear();
        playLog.appendText("Ready for next round.\n");
    }

    public void setMainApp(JavaFXTemplate mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleExit() {
        if (mainApp != null) {
            mainApp.showExitScreen(); // Replace exitApplication() with showExitScreen()
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

        playLog.appendText("Fresh start triggered.\n");


    }

    @FXML
    private void handleNewLook() {
        if (mainApp != null) {
            mainApp.applyNewLook();
        }
    }
}
