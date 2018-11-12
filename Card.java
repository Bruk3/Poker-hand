/*****************************************
Card.java  -- Poker Game
@author Bruk Zewdie
@UNI bbz2103
******************************************/

public class Card implements Comparable<Card>{
	
	private int suit; // use integers 1-4 to encode the suit
	private int rank; // use integers 1-13 to encode the rank
	
	public Card(int s, int r){
		//make a card with suit s and value v
		suit = s;
        rank = r;
	}
	
	public int compareTo(Card c)
    {
		// use this method to compare cards so they 
		// may be easily sorted
		if(this.rank<c.rank)
        {
            return -1;
        }
        else if(this.rank>c.rank)
        {
            return 1;
        }
		else if(this.suit<c.suit)
        {
            return -1;
        }
        else
        {
            return 1;
        }

	}
	
	public String toString()
    {
		// use this method to easily print a Card object
		String suitName;
        String cardName;
        switch(suit)
        {
            case 1:
                suitName="clubs";
                break;
            case 2:
                suitName="diamonds";
                break;
            case 3:
                suitName="hearts";
                break;
            case 4:
                suitName="spades"; 
                break;
            default:
                suitName="Invalid suit integer.";   
        }        
        
		if (rank<=10&&rank>1)
        {
            cardName = ""+rank+" of "+suitName;
        }
        else if (rank==1)
        {
            cardName = "ace of "+suitName;
        }
        else if (rank==11)
        {
            cardName = "jack of "+suitName;
        }
        else if (rank==12)
        {
            cardName = "queen of "+suitName;
        }
        else
        {
           cardName = "king of "+suitName;
        }
        
        return cardName;
	}
	
	public int getRank()
    {
        return rank;
    }
    public int getSuit()
    {
        return suit;
    }

}
