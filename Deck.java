/*****************************************
Deck.java  -- Poker Game
@author Bruk Zewdie
@UNI bbz2103
******************************************/


public class Deck 
{
	
	private Card[] cards;
	private int top; // the index of the top of the deck

	// add more instance variables if needed
	
	public Deck()
    {
		// make a 52 card deck here
		cards = new Card[52];
        top = 0;
        int count=0; 
        for(int s=1;s<=4;s++)
        {
            for(int r=1;r<=13;r++)
            {
            cards[count]=new Card(s,r);
            count++;
            }
        }  
        
        
	}
	
	public void shuffle(){
        
        for(int i=0;i<500;i++)  //500 swaps will be enough for shuffling.
                                
        {
                    
        //randomly generate 2 index numbers so that
        //the cards at those index numbers can be swapped with each other.
		int randomIndex1 = (int) (Math.random()*52);
        int randomIndex2 = (int) (Math.random()*52);
        
        //swap the two cards on the randomly generated indexe numbers
        Card tempCard = cards[randomIndex1];
        cards[randomIndex1]=cards[randomIndex2];
        cards[randomIndex2]=tempCard;
        }   
        
        // shuffle the deck here
	}
	
	public Card deal(){
        if (top>51)
        {
            this.shuffle();
            top=0; //resetting the deck when top>51.
        }
        top = top+1;
        return cards[top-1]; //top-1 because top has already been incremented.
		
	}
	
    public void resetTop()
    {
        top = 0;
    }
	// add more methods here if needed

}
