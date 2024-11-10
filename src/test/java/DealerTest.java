import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class DealerTest {
    private Dealer dealer;

    @BeforeEach
    public void setUp() {
        dealer = new Dealer();
    }

    // Test 5: Check that the dealer's deck has 52 cards initially
    @Test
    public void testDealerDeckInitialization() {
        assertEquals(52, dealer.theDeck.size(), "Dealer's deck should start with 52 cards");
    }

    // Test 6: Check that dealHand deals exactly 3 cards
    @Test
    public void testDealHandSize() {
        ArrayList<Card> hand = dealer.dealHand();
        assertEquals(3, hand.size(), "dealHand should deal exactly 3 cards");
    }

    // Test 7: Check that cards are removed from the deck when dealt
    @Test
    public void testDealHandReducesDeckSize() {
        dealer.dealHand();
        assertEquals(49, dealer.theDeck.size(), "Deck size should reduce by 3 after dealing a hand");
    }

    // Test 8: Check that dealHand reshuffles when fewer than 35 cards are left
    @Test
    public void testReshuffleWhenLowCards() {
        // Deal multiple hands to reduce deck size below 34 cards
        for (int i = 0; i < 7; i++) {
            dealer.dealHand();
        }
        // Now the deck should reshuffle and return 52 cards
        dealer.dealHand();
        assertEquals(49, dealer.theDeck.size(), "Deck should reshuffle to 52 cards if fewer than 35 cards left");
    }

    // Test 9: Check that reshuffling results in a unique order compared to the initial
    @Test
    public void testDeckOrderChangesAfterReshuffle() {
        ArrayList<Card> firstHand = new ArrayList<>(dealer.theDeck);
        dealer.theDeck.newDeck(); // Reshuffle deck
        ArrayList<Card> secondHand = new ArrayList<>(dealer.theDeck);
        assertNotEquals(firstHand, secondHand, "Deck order should change after reshuffling");
    }

    // Test 10: Check that the dealer’s hand is cleared between deals
    @Test
    public void testDealersHandClearedBetweenDeals() {
        ArrayList<Card> firstHand = dealer.dealHand();
        ArrayList<Card> secondHand = dealer.dealHand();
        assertNotSame(firstHand, secondHand, "Each dealHand call should return a new hand");
    }
}
