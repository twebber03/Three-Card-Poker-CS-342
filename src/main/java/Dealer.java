import java.util.ArrayList;

public class Dealer {
    Deck theDeck;
    ArrayList<Card> dealersHand;

    Dealer(){
        theDeck = new Deck(); // Initializes randomized sorted deck of 52 cards
        dealersHand = new ArrayList<>(); // Initializes empty hand
    }

    //    will return an ArrayList<Card> of three cards removed from
    //        theDeck.

    public ArrayList<Card> dealDealersHand(){
        dealersHand.clear();
        dealersHand = dealHand();
        return new ArrayList<>(dealersHand); // return copy of dealersHand, like a snapshot
    }

    public ArrayList<Card> dealHand(){
        if(theDeck.size() <= 34){ // check to see if there are less then 34 cards left, if so randomize the deck
            System.out.println("Not enough cards left. Reshuffling a new deck.");
            theDeck.newDeck();
        }

        ArrayList<Card> hand = new ArrayList<>();

        for(int i = 0; i < 3; i++){
            hand.add(theDeck.remove(0));
        }

        return new ArrayList<>(hand);
    }
}
