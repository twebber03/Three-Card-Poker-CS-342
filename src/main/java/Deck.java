import java.util.ArrayList;
import java.util.Collections;

public class Deck extends ArrayList<Card> {

    String[] suits = {"H", "D", "C", "S"};
    String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "JACK", "QUEEN", "KING", "ACE"};

    // Constructor to initialize the deck
    // add method adds object to Object[] array
    public Deck() {
        for(String suit : suits){
            for(String value : values){
                Card card = new Card(suit, value);
                add(card);
            }
        }

        shuffle();

    }

    public void newDeck(){
        this.clear();
        for(String suit : suits){
            for(String value : values){
                Card card = new Card(suit, value);
                add(card);
            }
        }

        shuffle();
    }


    // Method to shuffle the deck
    private void shuffle() {
        Collections.shuffle(this); // Shuffle the cards in the deck
    }

    public void print(){
        for(Card card : this){
            System.out.println(card);
        }
    }

}
