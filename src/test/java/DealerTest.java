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
        ArrayList<Card> hand = dealer.dealDealersHand();
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
        // Deplete the deck until it should trigger a reshuffle
        while (dealer.theDeck.size() > 34) {
            dealer.dealHand();
        }

        // The next deal should trigger a reshuffle because we will be below 34 cards
        dealer.dealHand();

        // Check that after reshuffling and dealing one hand, the deck size is reduced by three cards from 52 to 49
        int expectedSizeAfterDeal = 52 - 3; // each deal removes 3 cards
        assertEquals(dealer.theDeck.size(), expectedSizeAfterDeal, "Deck should be reset to 52 cards after reshuffling and should be 49 after dealing 3 cards");
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
        ArrayList<Card> firstHand = dealer.dealDealersHand();
        ArrayList<Card> secondHand = dealer.dealDealersHand();
        assertNotSame(firstHand, secondHand, "Each dealHand call should return a new hand");
    }
}
