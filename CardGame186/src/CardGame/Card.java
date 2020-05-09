package CardGame;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.io.File;
import java.io.IOException;

public class Card {

	//a card needs to have these be constant throughout its existence, should never change
    private final int rank, suit;
    private final String cardImageLocation = "CardGame186/resources/card.images/";
    private final File cardBackside = new File(cardImageLocation + "back.png");
    
    //these shouldn't change
    public static final String [] ranks = {"Joker", "Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"}; // there is no zero card rank, make it null
    public static final String [] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};

    private Image cardFace = null;
    private static Image cardBack = null;

    public Image getCardFace() {
        return cardFace;
    }

    public Image getCardBack() {
        return cardBack;
    }

    /**
     * Constructs a card (intended for a deck of 52).
     * @param rank the rank of the card
     * @param suit the suit of the card
     */
    public Card(int rank, int suit) {
    	/*
    	 * RANK:
    	 * 	Joker (card is unusable) = 0
    	 * 	Ace = 1
    	 * 	Numbers = (2-10)
    	 * 	Jack = 11
    	 * 	Queen = 12
    	 *  King = 13
    	 * SUITS:
    	 * 	Clubs = 0
    	 * 	Diamonds = 1
    	 * 	Hearts = 2
    	 * 	Spades = 3
    	 */
        this.rank = rank;
        this.suit = suit;

        assignImages();
    }

    /**
     * Helper Method:
     *  Assign a specific image to the card face and back and store it.
     */
    private void assignImages() {
        if (this.rank != 0) {       // If the card is not a Joker

            String pathToImage = cardImageLocation + this.returnRankValue() + "." + this.returnSuitValue() + ".png";

            try {                   // Try assigning the face image
                cardFace = SwingFXUtils.toFXImage(ImageIO.read(new File(pathToImage)),null);
            } catch (IOException e) {
                System.err.printf("Error: Resource not found at: %s \n", pathToImage);
                e.printStackTrace();
            } finally {             // If successful...
                try {               // Try assigning the back image
                	cardBack = SwingFXUtils.toFXImage(ImageIO.read(cardBackside),null);
                } catch (IOException e) {
                    System.err.printf("Error: Resource not found at: %s \n", cardBackside.toString());
                    e.printStackTrace();
                }
            }
        } else {                    // If the card is a Joker
            try {                   // Try assigning the same back.png to both sides (Joker exclusive)
                cardFace = SwingFXUtils.toFXImage(ImageIO.read(cardBackside),null);
                cardBack = SwingFXUtils.toFXImage(ImageIO.read(cardBackside),null);
            } catch (IOException e) {
                System.err.printf("Error: Resource not found at: %s \n", cardBackside.toString());
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns a string with the rank of the card.
     * @return string of the rank
     */
    public String returnRank() {
    	return ranks[rank];
    } 
    
    /**
     * Returns a integer value for the rank of the card.
     * RANK:
     * Ace = 1
     * Numbers = (2-10)
     * Jack = 11
     * Queen = 12
     * King = 13
     * @return value of the rank of this card
     */
    public int returnRankValue() {
    	return rank;
    }  
    
    /**
     * Returns a string with the suit of the card.
     * @return string of the suit
     */
    public String returnSuit() {
    	return suits[suit];
    }

    /**
     * Returns a integer value for the suit of the card.
	 * SUITS:
	 * 	Clubs = 0
	 * 	Diamonds = 1
	 * 	Hearts = 2
	 * 	Spades = 4
     * @return value of the suit of this card
     */
    
    public int returnSuitValue() {
    	return suit;
    }
    
    /**
     * A user-readable string with the values of the card
     * @return the card's suit and rank
     */
    public String returnCardName() {
    	if (returnRankValue() != 0) {
    		return (ranks[rank] + " of " + suits[suit]);
    	} else {return ranks[rank];}
    	
    }
    
    /**
     * Checks to see which card is higher tiered in both suits and ranks, compared to another card
     * @param that another card to compare 
     * @return returns 1 if this ("the caller" card) wins, -1 if that ("the called" card) wins, and 0 if they are equivalent
     */
    public int comparedTo(Card that) {
        if (this.suit < that.suit) {
            return -1;
        }
        if (this.suit > that.suit) {
            return 1;
        }
        if (this.rank < that.rank) {
            return -1;
        }
        if (this.rank > that.rank) {
            return 1;
        }
        return 0;
    }

    /**
     * Returns an array of the card's face and back images.
     * @return array with cardFace at index 0 and cardBack at index 1.
     */
    public Image [] returnCardImages () {
        return new Image[]{cardFace, cardBack};
    }

    /**
     * Verifies whether or not the card is a Joker card (unusable)
     * @return true if Joker, false if not
     */
    public boolean isJoker () {
        return this.rank == 0;
    }
}
