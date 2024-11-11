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
        player1.setPlayBet(player1.getAnteBet());
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
        player2.setPlayBet(player2.getAnteBet());
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
        if (player1PlayButton.isDisable() && player2PlayButton.isDisable()) {
            ArrayList<Card> dealerCards = dealer.dealDealersHand();
            displayCards(dealerHand, dealerCards);

            determineOutcome(player1, dealerCards, player1Winnings);
            determineOutcome(player2, dealerCards, player2Winnings);

            playLog.appendText("Results displayed.\n");
            resetForNextRound();
        }
    }

    private void determineOutcome(Player player, ArrayList<Card> dealerCards, Label winningsLabel) {
        int result = ThreeCardLogic.compareHands(dealerCards, player.getPlayerHand());
        int winnings = 0;

        if (result == 2) {
            winnings = player.getAnteBet() + player.getPlayBet() + player.getPairPlusBet();
            playLog.appendText("Player wins!\n");
        } else {
            winnings = -player.getAnteBet();
            playLog.appendText("Dealer wins.\n");
        }

        player.updateTotalWinnings(winnings);
        winningsLabel.setText("$" + player.getTotalWinnings());
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
        playLog.appendText("Fresh start triggered.\n");
    }

    @FXML
    private void handleNewLook() {
        if (mainApp != null) {
            mainApp.applyNewLook();
        }
    }
}
