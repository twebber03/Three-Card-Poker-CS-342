// Base class for a Card
class Card {
    String suit;
    String value;

    // Constructor for Card
    public Card(String suit, String value) {
        this.suit = suit;
        this.value = value;
    }

    // Method to display card information
    @Override
    public String toString() {
        return value + " of " + suit;
    }
}
