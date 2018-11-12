/*****************************************
Player.java  -- Poker Game
@author Bruk Zewdie
@UNI bbz2103
******************************************/

import java.util.ArrayList;
import java.util.Collections;


public class Player 
{
	
		
	private ArrayList<Card> hand; // the player's cards
	private double bankroll;
    private double bet;

	
		
	public Player()
    {		
	    hand = new ArrayList<Card>();
        bankroll = 200;  // Let the default start value of bankroll be 100.
        bet = 0;
          
	}

	public void addCard(Card c)
    {
        hand.add(c);
        Collections.sort(hand);
    }

	public void removeCard(int index)   
         
    {
	    hand.remove(index);// remove the card c from the player's hand
        Collections.sort(hand);
    }
    	
    public void bets(double amt)
    {
            bet = amt;
            bankroll = bankroll - amt;
            // player makes a bet
    }

    public void winnings(double odds)  
    {
        bankroll = bankroll+(odds * bet);
    }
    
    public double getBankroll()
    {
            return bankroll;            
    }
    
    public ArrayList<Card> getHand()
    {
       return hand;
    }
    public void replaceCard(int index, Card c)
    {
        hand.set(index, c);
    }
    public void removeAllCards()
    {
        hand.clear();
    }

    
}


