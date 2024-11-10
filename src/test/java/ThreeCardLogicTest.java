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
        playerHand.addAll(Arrays.asList(new Card("H", "10"), new Card("H", "J"), new Card("H", "Q")));
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
        playerHand.addAll(Arrays.asList(new Card("H", "9"), new Card("D", "10"), new Card("S", "J")));
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
        playerHand.addAll(Arrays.asList(new Card("H", "10"), new Card("H", "J"), new Card("H", "Q")));
        assertEquals(400, ThreeCardLogic.evalPPWinnings(playerHand, 10), "Payout for straight flush incorrect");
    }

    // Test 8: Compare two hands where player wins
    @Test
    public void testCompareHandsPlayerWins() {
        playerHand.addAll(Arrays.asList(new Card("H", "10"), new Card("H", "J"), new Card("H", "Q")));
        dealerHand.addAll(Arrays.asList(new Card("H", "2"), new Card("D", "5"), new Card("S", "9")));
        assertEquals(2, ThreeCardLogic.compareHands(dealerHand, playerHand), "Player should win with higher hand");
    }

    // Test 9: Compare two hands where dealer wins
    @Test
    public void testCompareHandsDealerWins() {
        dealerHand.addAll(Arrays.asList(new Card("H", "10"), new Card("H", "J"), new Card("H", "Q")));
        playerHand.addAll(Arrays.asList(new Card("H", "2"), new Card("D", "5"), new Card("S", "9")));
        assertEquals(1, ThreeCardLogic.compareHands(dealerHand, playerHand), "Dealer should win with higher hand");
    }

    // Test 10: Compare two hands for a tie
    @Test
    public void testCompareHandsTie() {
        playerHand.addAll(Arrays.asList(new Card("S", "10"), new Card("H", "J"), new Card("D", "Q")));
        dealerHand.addAll(Arrays.asList(new Card("H", "10"), new Card("D", "J"), new Card("S", "Q")));
        assertEquals(0, ThreeCardLogic.compareHands(dealerHand, playerHand), "Should be a tie");
    }
}
