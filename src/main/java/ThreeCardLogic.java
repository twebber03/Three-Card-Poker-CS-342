import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class ThreeCardLogic {

    public static int evalHand(ArrayList<Card> hand) {

        // Sort the cards by value using the CardValueConverter
        Collections.sort(hand, (c1, c2) -> CardValueConverter.getValue(c1.value) - CardValueConverter.getValue(c2.value));

        // Check for Flush (all cards same suit)
        boolean isFlush = hand.get(0).suit.equals(hand.get(1).suit) && hand.get(1).suit.equals(hand.get(2).suit);

        // Calculate values for straight checking
        int val1 = CardValueConverter.getValue(hand.get(0).value);
        int val2 = CardValueConverter.getValue(hand.get(1).value);
        int val3 = CardValueConverter.getValue(hand.get(2).value);

        // Check for Straight (sequential values)
        boolean isStraight = (val3 == val2 + 1 && val2 == val1 + 1) || (val1 == 2 && val2 == 3 && val3 == 14); // Ace, 2, 3

        if (isFlush && isStraight) {
            return 1; // Straight flush
        }

        // Check for Three of a Kind
        if (val1 == val2 && val2 == val3) {
            return 2; // Three of a kind
        }

        if (isStraight) {
            return 3; // Straight
        }

        if (isFlush) {
            return 4; // Flush
        }

        // Check for Pair
        if (val1 == val2 || val2 == val3 || val1 == val3) {
            return 5; // Pair
        }

        return 0; // High card
    }

    // Seperate bet based on players hand
    public static int evalPPWinnings(ArrayList<Card> hand, int bet) {
        int handRank = evalHand(hand);
        switch (handRank) {
            case 1: return bet * 40;  // Straight Flush
            case 2: return bet * 30;  // Three of a Kind
            case 3: return bet * 6;   // Straight
            case 4: return bet * 3;   // Flush
            case 5: return bet * 1;   // Pair
            default: return 0;        // No winning hand, lose the bet
        }
    }

    public static int compareHands(ArrayList<Card> dealer, ArrayList<Card> player) {
        int dealerRank = evalHand(dealer);
        int playerRank = evalHand(player);

        // Compare hand ranks
        if (dealerRank > playerRank) {
            return 1;  // Dealer wins
        } else if (playerRank > dealerRank) {
            return 2;  // Player wins
        } else {
            // Ranks are equal, compare the highest card values
            // Assuming hands are sorted by evalHand method
            Collections.sort(dealer, (c1, c2) -> CardValueConverter.getValue(c1.value) - CardValueConverter.getValue(c2.value));
            Collections.sort(player, (c1, c2) -> CardValueConverter.getValue(c1.value) - CardValueConverter.getValue(c2.value));

            for (int i = 2; i >= 0; i--) {  // Start comparison from the highest card
                int dealerCardValue = CardValueConverter.getValue(dealer.get(i).value);
                int playerCardValue = CardValueConverter.getValue(player.get(i).value);
                if (dealerCardValue > playerCardValue) {
                    return 1;  // Dealer wins
                } else if (playerCardValue > dealerCardValue) {
                    return 2;  // Player wins
                }
            }
            // If all cards are equal after ranking and individual comparison
            return 0;  // Tie
        }
    }
}