import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Arrays;

public class ThreeCardLogicTest {
    private ArrayList<Card> playerHand, dealerHand;

    @BeforeEach
    public void setUp() {
        playerHand = new ArrayList<>();
        dealerHand = new ArrayList<>();
    }

    // Test 1: Verify straight flush evaluation
    @Test
    public void testEvalHandStraightFlush() {
        playerHand.addAll(Arrays.asList(new Card("H", "10"), new Card("H", "JACK"), new Card("H", "QUEEN")));
        assertEquals(1, ThreeCardLogic.evalHand(playerHand), "Should identify straight flush");
    }

    // Test 2: Verify three of a kind evaluation
    @Test
    public void testEvalHandThreeOfAKind() {
        playerHand.addAll(Arrays.asList(new Card("H", "10"), new Card("D", "10"), new Card("S", "10")));
        assertEquals(2, ThreeCardLogic.evalHand(playerHand), "Should identify three of a kind");
    }

    // Test 3: Verify straight evaluation
    @Test
    public void testEvalHandStraight() {
        playerHand.addAll(Arrays.asList(new Card("H", "9"), new Card("D", "10"), new Card("S", "JACK")));
        assertEquals(3, ThreeCardLogic.evalHand(playerHand), "Should identify a straight");
    }

    // Test 4: Verify flush evaluation
    @Test
    public void testEvalHandFlush() {
        playerHand.addAll(Arrays.asList(new Card("H", "2"), new Card("H", "5"), new Card("H", "9")));
        assertEquals(4, ThreeCardLogic.evalHand(playerHand), "Should identify a flush");
    }

    // Test 5: Verify pair evaluation
    @Test
    public void testEvalHandPair() {
        playerHand.addAll(Arrays.asList(new Card("H", "5"), new Card("D", "5"), new Card("S", "9")));
        assertEquals(5, ThreeCardLogic.evalHand(playerHand), "Should identify a pair");
    }

    // Test 6: Verify high card evaluation
    @Test
    public void testEvalHandHighCard() {
        playerHand.addAll(Arrays.asList(new Card("H", "3"), new Card("D", "7"), new Card("S", "10")));
        assertEquals(0, ThreeCardLogic.evalHand(playerHand), "Should identify high card only");
    }

    // Test 7: Verify evalPPWinnings with a straight flush
    @Test
    public void testEvalPPWinningsStraightFlush() {
        playerHand.addAll(Arrays.asList(new Card("H", "10"), new Card("H", "JACK"), new Card("H", "QUEEN")));
        assertEquals(400, ThreeCardLogic.evalPPWinnings(playerHand, 10), "Payout for straight flush incorrect");
    }

    // Test 8: Compare two hands where player wins
    @Test
    public void testCompareHandsPlayerWins() {
        playerHand.addAll(Arrays.asList(new Card("H", "10"), new Card("H", "JACK"), new Card("H", "QUEEN")));
        dealerHand.addAll(Arrays.asList(new Card("H", "2"), new Card("D", "5"), new Card("S", "9")));
        assertEquals(2, ThreeCardLogic.compareHands(dealerHand, playerHand), "Player should win with higher hand");
    }

    // Test 9: Compare two hands where dealer wins
    @Test
    public void testCompareHandsDealerWins() {
        dealerHand.addAll(Arrays.asList(new Card("H", "10"), new Card("H", "JACK"), new Card("H", "QUEEN")));
        playerHand.addAll(Arrays.asList(new Card("H", "2"), new Card("D", "5"), new Card("S", "9")));
        assertEquals(1, ThreeCardLogic.compareHands(dealerHand, playerHand), "Dealer should win with higher hand");
    }

    // Test 10: Compare two hands for a tie
    @Test
    public void testCompareHandsTie() {
        playerHand.addAll(Arrays.asList(new Card("S", "10"), new Card("H", "JACK"), new Card("D", "QUEEN")));
        dealerHand.addAll(Arrays.asList(new Card("H", "10"), new Card("D", "JACK"), new Card("S", "QUEEN")));
        assertEquals(0, ThreeCardLogic.compareHands(dealerHand, playerHand), "Should be a tie");
    }
    // Test 11: Verify evalPPWinnings with a three of a kind
    @Test
    public void testEvalPPWinningsThreeOfAKind() {
        playerHand.addAll(Arrays.asList(new Card("H", "7"), new Card("D", "7"), new Card("S", "7")));
        assertEquals(300, ThreeCardLogic.evalPPWinnings(playerHand, 10), "Payout for three of a kind incorrect");
    }

    // Test 12: Verify evalPPWinnings with a straight
    @Test
    public void testEvalPPWinningsStraight() {
        playerHand.addAll(Arrays.asList(new Card("H", "3"), new Card("D", "4"), new Card("S", "5")));
        assertEquals(60, ThreeCardLogic.evalPPWinnings(playerHand, 10), "Payout for straight incorrect");
    }

    // Test 13: Verify evalPPWinnings with a flush
    @Test
    public void testEvalPPWinningsFlush() {
        playerHand.addAll(Arrays.asList(new Card("C", "3"), new Card("C", "8"), new Card("C", "KING")));
        assertEquals(30, ThreeCardLogic.evalPPWinnings(playerHand, 10), "Payout for flush incorrect");
    }

    // Test 14: Verify evalPPWinnings with a pair
    @Test
    public void testEvalPPWinningsPair() {
        playerHand.addAll(Arrays.asList(new Card("H", "5"), new Card("D", "5"), new Card("S", "10")));
        assertEquals(10, ThreeCardLogic.evalPPWinnings(playerHand, 10), "Payout for pair incorrect");
    }

    // Test 15: Verify evalPPWinnings with a high card (no winnings)
    @Test
    public void testEvalPPWinningsHighCard() {
        playerHand.addAll(Arrays.asList(new Card("H", "2"), new Card("D", "5"), new Card("S", "8")));
        assertEquals(0, ThreeCardLogic.evalPPWinnings(playerHand, 10), "No payout for high card, should be 0");
    }

    // Test 16: Verify compareHands with three equal high cards (tie)
    @Test
    public void testCompareHandsEqualHighCards() {
        playerHand.addAll(Arrays.asList(new Card("H", "KING"), new Card("D", "3"), new Card("S", "8")));
        dealerHand.addAll(Arrays.asList(new Card("C", "KING"), new Card("H", "3"), new Card("D", "8")));
        assertEquals(0, ThreeCardLogic.compareHands(dealerHand, playerHand), "Should be a tie with equal high cards");
    }

    // Test 17: Verify compareHands where player has a pair, dealer has high card
    @Test
    public void testCompareHandsPlayerPairDealerHighCard() {
        playerHand.addAll(Arrays.asList(new Card("H", "5"), new Card("D", "5"), new Card("S", "9")));
        dealerHand.addAll(Arrays.asList(new Card("H", "2"), new Card("D", "7"), new Card("C", "10")));
        assertEquals(2, ThreeCardLogic.compareHands(dealerHand, playerHand), "Player should win with a pair over high card");
    }

    // Test 18: Verify evalHand with an Ace-high straight flush
    @Test
    public void testEvalHandAceHighStraightFlush() {
        playerHand.addAll(Arrays.asList(new Card("S", "10"), new Card("S", "JACK"), new Card("S", "QUEEN")));
        assertEquals(1, ThreeCardLogic.evalHand(playerHand), "Should identify Ace-high straight flush");
    }

    // Test 19: Verify evalHand with an Ace-high straight
    @Test
    public void testEvalHandAceHighStraight() {
        playerHand.addAll(Arrays.asList(new Card("H", "10"), new Card("S", "JACK"), new Card("D", "QUEEN")));
        assertEquals(3, ThreeCardLogic.evalHand(playerHand), "Should identify Ace-high straight");
    }

    // Test 20: Verify evalPPWinnings with a very high bet and a three of a kind
    @Test
    public void testEvalPPWinningsHighBetThreeOfAKind() {
        playerHand.addAll(Arrays.asList(new Card("C", "KING"), new Card("D", "KING"), new Card("S", "KING")));
        assertEquals(3000, ThreeCardLogic.evalPPWinnings(playerHand, 100), "Payout for three of a kind with high bet incorrect");
    }

}
