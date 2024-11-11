// GameController.java
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.ArrayList;

public class GameController {
    @FXML private HBox dealerHand;
    @FXML private HBox playerHand;
    @FXML private Label winningsDisplay;
    @FXML private Label playerBetsLabel;
    @FXML private Button dealButton;
    @FXML private Button placeBetButton;
    @FXML private TextField anteBetField;
    @FXML private TextField pairPlusBetField;

    private Player player;
    private Dealer dealer;
    private JavaFXTemplate mainApp;
    private boolean betPlaced = false;

    public GameController() {
        player = new Player();
        dealer = new Dealer();
    }

    @FXML
    public void initialize() {
        updateWinningsDisplay();
        dealButton.setDisable(true);
    }

    public void setMainApp(JavaFXTemplate mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handlePlaceBet() {
        try {
            int anteBet = Integer.parseInt(anteBetField.getText());
            int pairPlusBet = Integer.parseInt(pairPlusBetField.getText());

            if (anteBet < 5 || anteBet > 25) {
                showError("Ante bet must be between $5 and $25");
                return;
            }

            if (pairPlusBet < 0 || pairPlusBet > 25) {
                showError("Pair Plus bet must be between $0 and $25");
                return;
            }

            player.setAnteBet(anteBet);
            player.setPlayBet(anteBet); // Play bet equals ante bet
            player.setPairPlusBet(pairPlusBet);

            betPlaced = true;
            dealButton.setDisable(false);
            placeBetButton.setDisable(true);
            anteBetField.setDisable(true);
            pairPlusBetField.setDisable(true);

            updateBetsDisplay();
        } catch (NumberFormatException e) {
            showError("Please enter valid numbers for bets");
        }
    }

    @FXML
    private void handleDeal() {
        if (!betPlaced) {
            showError("Please place your bets first!");
            return;
        }

        clearHands();
        dealHands();
        evaluateHands();
        resetForNextRound();
    }

    private void clearHands() {
        dealerHand.getChildren().clear();
        playerHand.getChildren().clear();
    }

    private void dealHands() {
        ArrayList<Card> pHand = dealer.dealHand();
        ArrayList<Card> dHand = dealer.dealDealersHand();

        player.setPlayerHand(pHand);
        displayCards(playerHand, pHand);
        displayCards(dealerHand, dHand);
    }

    private void evaluateHands() {
        ArrayList<Card> dealerCards = dealer.dealersHand;
        ArrayList<Card> playerCards = player.getPlayerHand();

        // Evaluate main game
        int result = ThreeCardLogic.compareHands(dealerCards, playerCards);
        int mainGameWinnings = 0;

        if (result == 2) { // Player wins
            mainGameWinnings = player.getAnteBet() * 2;
            player.updateTotalWinnings(mainGameWinnings);
        }

        // Evaluate Pair Plus
        int pairPlusWinnings = ThreeCardLogic.evalPPWinnings(playerCards, player.getPairPlusBet());
        player.updateTotalWinnings(pairPlusWinnings);

        // Display results
        StringBuilder resultMessage = new StringBuilder();
        resultMessage.append(getResultMessage(result));
        if (mainGameWinnings > 0) {
            resultMessage.append(" Main game win: $").append(mainGameWinnings);
        }
        if (pairPlusWinnings > 0) {
            resultMessage.append(" Pair Plus win: $").append(pairPlusWinnings);
        }

        winningsDisplay.setText(resultMessage.toString());
        updateWinningsDisplay();
    }

    private String getResultMessage(int result) {
        switch (result) {
            case 2: return "Player wins!";
            case 1: return "Dealer wins!";
            default: return "It's a tie!";
        }
    }

    private void displayCards(HBox handBox, ArrayList<Card> hand) {
        for (Card card : hand) {
            Label cardLabel = new Label(card.toString());
            cardLabel.getStyleClass().add("card-label");
            handBox.getChildren().add(cardLabel);
        }
    }

    private void resetForNextRound() {
        betPlaced = false;
        placeBetButton.setDisable(false);
        dealButton.setDisable(true);
        anteBetField.setDisable(false);
        pairPlusBetField.setDisable(false);
        anteBetField.clear();
        pairPlusBetField.clear();
    }

    private void updateWinningsDisplay() {
        playerBetsLabel.setText(String.format("Total Winnings: $%d", player.getTotalWinnings()));
    }

    private void updateBetsDisplay() {
        String betsText = String.format("Ante: $%d | Play: $%d | Pair Plus: $%d",
                player.getAnteBet(), player.getPlayBet(), player.getPairPlusBet());
        playerBetsLabel.setText(betsText);
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }
}