import java.util.ArrayList;

public class Player {
    ArrayList<Card> playerHand;
    int anteBet;
    int playBet;
    int pairPlusBet;
    int totalWinnings;

    Player(){
        playerHand = new ArrayList<>();
        anteBet = 0;
        playBet = 0;
        pairPlusBet = 0;
        totalWinnings = 0;
    }
     ArrayList<Card> setPlayerHand(ArrayList<Card> dealHand){
        playerHand = dealHand;
        return new ArrayList<>(playerHand);
     }
     // Getter method for totalWinnings
     int getTotalWinnings(){
        return totalWinnings;
     }

     // Setter method for anteBet
     void setAnteBet(int anteBet){
        this.anteBet = anteBet;
     }

     // Getter method for anteBet
     int getAnteBet(){
        return anteBet;
     }


    // Setter method for playBet
    public void setPlayBet(int playBet) {
        this.playBet = playBet;
    }

    // Getter method for playBet
    public int getPlayBet() {
        return playBet;
    }

    // Getter method for pairPlusBet
    public int getPairPlusBet() {
        return pairPlusBet;
    }

    // Setter method for pairPlusBet
    public void setPairPlusBet(int pairPlusBet) {
        this.pairPlusBet = pairPlusBet;
    }


    // Updates totalWinnings with individual game winnings
    void updateTotalWinnings(int gameWinning){
        totalWinnings += gameWinning;
     }


}
