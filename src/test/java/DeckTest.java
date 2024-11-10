import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;

public class DeckTest {
    private Deck deck;

    @BeforeEach
    public void setUp() {
        deck = new Deck();
    }

    // Test 1: Check that the deck has 52 cards after initialization
    @Test
    public void testDeckInitialization() {
        assertEquals(52, deck.size(), "Deck should have 52 cards after initialization");
    }

    // Test 2: Check that the deck contains no duplicate cards
    @Test
    public void testNoDuplicateCards() {
        HashSet<Card> cardSet = new HashSet<>(deck); // Set will remove duplicates if any
        assertEquals(52, cardSet.size(), "Deck should contain unique cards only");
    }

    // Test 3: Check that `newDeck` properly resets to 52 cards
    @Test
    public void testNewDeck() {
        deck.remove(0); // Remove a card
        deck.newDeck(); // Reset deck
        assertEquals(52, deck.size(), "newDeck should reset the deck to 52 cards");
    }

    // Test 4: Ensure shuffle changes the order of cards
    @Test
    public void testShuffleChangesOrder() {
        Deck unshuffledDeck = new Deck();
        deck.newDeck(); // Reset deck to original order
        boolean isShuffled = false;

        for (int i = 0; i < 5; i++) {
            deck.newDeck();
            if (!deck.equals(unshuffledDeck)) {
                isShuffled = true;
                break;
            }
        }
        assertTrue(isShuffled, "Shuffling should change the order of cards");
    }
}
