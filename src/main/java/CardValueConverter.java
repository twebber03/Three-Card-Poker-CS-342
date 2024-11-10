import java.util.HashMap;
import java.util.Map;

public class CardValueConverter {
    private static final Map<String, Integer> cardValues = new HashMap<>();

    static {
        // Initialize card values, assuming Ace is high (value 14)
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "JACK", "QUEEN", "KING", "ACE"};
        for (int i = 0; i < values.length - 1; i++) {
            cardValues.put(values[i], i + 2);  // Maps "2" to 2, "3" to 3, ..., "KING" to 13
        }
        cardValues.put("ACE", 14); // Special case for Ace
    }

    public static int getValue(String cardValue) {
        return cardValues.getOrDefault(cardValue.toUpperCase(), -1); // Ensures case-insensitive lookup
    }
}
