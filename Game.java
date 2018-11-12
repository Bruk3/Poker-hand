/*****************************************
Game.java  -- Poker Game
@author Bruk Zewdie
@UNI bbz2103
******************************************/


import java.util.Scanner;
import java.util.Collections;
import java.util.ArrayList;
public class Game {
	
	private Player p;
	private Deck cards;
    private int payout;
    private boolean realGame;
	// you'll probably need some more here
	
	
	public Game(String[] testHand)
    {
        realGame = false;
        p = new Player();
        int rank;
        int suit = -100; //Just for initialization purposes,
                        //the following switch-case statement
                        //will assign suit the proper value.
        for(String encodedCard: testHand)
        {
            
            rank = Integer.parseInt(encodedCard.substring(1));
            switch(encodedCard.substring(0,1))
            {
                case "c":
                    suit = 1;
                    break;
                case  "d":
                    suit = 2;
                    break;
                case "h":
                    suit = 3;
                    break;
                case "s":
                    suit = 4;
                    break;                
            }
           Card singleCard = new Card(suit, rank);
           p.addCard(singleCard);           
            
        }
				
		
	}
	
	public Game()
    {
        // This no-argument constructor is to actually play a normal game
		cards = new Deck();
		p = new Player();
        realGame = true;
	}
	
	public void play()
    {
		// this method should play the game	
		if(!realGame)
        {
            System.out.println(checkHand(p.getHand()));
        }
        else if(realGame)
        {
            String header, decision;
            int input, bet;
            header  = "Alright, alright, alright!!\n";
            header = header + "Let's Play Poker!!!";
            System.out.println(header);
            do //play game until bankroll is less than 5 or they don't
                //want to continue anymore. 
            {
                 System.out.println("You currently have "+p.getBankroll());
                 do //Prompt user until they insert 1 to 5 tokens. 
                 {
                 System.out.println("Please, place a bet between 1 and 5 tokens.");
                 Scanner in = new Scanner(System.in);
                 bet = in.nextInt();
                 }while(bet>5||bet<1);
                 
                 p.bets(bet);
                 cards.shuffle();
                 for(int i=0;i<5;i++)
                 {
                     p.addCard(cards.deal());                     
                 }
                Collections.sort(p.getHand());
                System.out.println("Your cards are: ");
                System.out.println(p.getHand()); //Might create errors
                header = "Do you want to remove any cards? input y or n : ";
                System.out.println(header);
                Scanner stringIn = new Scanner(System.in);
                decision = stringIn.nextLine();
                if(decision.equals("y"))
                {
                    for(int i=0;i<5;i++)
                    {
                        header = "Do you want to remove ";
                        System.out.println(header+p.getHand().get(i));
                        Scanner inputString = new Scanner(System.in);
                        decision = inputString.nextLine();
                        if(decision.equals("n"))
                        {
                            continue;
                        }
                        else if(decision.equals("y"))//if decison is yes. 
                        {
                           p.replaceCard(i,cards.deal());                            
                        }
                    }
                }
                Collections.sort(p.getHand());
                System.out.println("Your new cards are "+p.getHand()); //Might create problems becaue doesn't know how to print a hand.
                System.out.println(checkHand(p.getHand()));
                p.winnings(payout);
                int winning = bet*payout;
                System.out.println("Your payout is "+payout+" tokens.");
                System.out.println("You now have "+p.getBankroll()+
                                  " tokens left.");
                p.removeAllCards();                
                cards.resetTop();
                System.out.println("Do you want to continue playing? y or n.");
                Scanner in = new Scanner(System.in);
                decision = in.nextLine();
            }while (decision.equals("y")&& p.getBankroll()>=5);
            if(p.getBankroll()<5)
            {
               System.out.println("Sorry, you have less than 5"+
                                  "tokens. Game Over");
            }
            System.out.println("Thank you for playing!!!");
            
        }
    	
        
	}
	
	public String checkHand(ArrayList<Card> hand)
    {
		// this method takes an ArrayList of cards
		// as input and then determine what evaluates to and
		// return that as a String
		Collections.sort(hand);
        String handScore = "";

		if (isRoyalFlush(hand))
        {
            handScore = "Royal Flush";
            payout = 250;
        }
        else if (isStraightFlush(hand))
        {
            handScore = "Straight Flush";
            payout = 50;
        }
        else if (isFourOfAKind(hand))
        {
            handScore = "Four of a Kind";
            payout = 25;
        }
        else if (isFullHouse(hand))
        {
            handScore = "Full House";
            payout = 6;
        }
        else if (isFlush(hand))
        {
            handScore = "Flush";
            payout = 5;
        }
        else if (isStraight(hand))
        {
            handScore = "Straight";
            payout = 4;
        }
        else if (isThreeOfAKind(hand))
        {
            handScore = "Three of a Kind";
            payout = 3;
        }
        else if (isTwoPairs(hand))
        {
            handScore = "Two Pairs";
            payout = 2;
        }
        else if (isOnePair(hand))
        {
            handScore = "One Pair";
            payout = 1;
        }
        else
        {
           handScore = "No Pair";
           payout = 0;
        }        
        return handScore;
    }
    
    public boolean isOnePair(ArrayList<Card> hand)
    {
        
        for(int i=1;i<5;i++)
        {
            if(hand.get(i).getRank()==hand.get(i-1).getRank())
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean isTwoPairs(ArrayList<Card> hand)
    {
        //pair variable counts the number of pairs in the hand.
        
        int pair = 0;
        for(int i=1;i<5;i++)
        {
            if(hand.get(i).getRank()==hand.get(i-1).getRank())
            {
                i++; //skip over one index, to avoid counting Three
                //of a kind as two pairs.
                pair++;
            }
        }
        if(pair==2)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean isThreeOfAKind(ArrayList<Card> hand)
    {
        //Check if there are three consecutive cards with the
        //same rank in the hand. If the cards have the same 
        //rank, they will be consecutive because the hand
        //that is passed as an argument has already been sorted.
       
        for(int i=2;i<5;i++)
        {
            if(hand.get(i).getRank()==hand.get(i-1).getRank()&&
               hand.get(i-1).getRank()==hand.get(i-2).getRank())              
            {
                return true;
            }
            
        }
        return false;
    }
        
    public boolean isFourOfAKind(ArrayList<Card> hand)
    {
        //Same logic like three of a kind. Checks for 4 consecutive
        //values with the same rank.
        for(int i=3;i<5;i++)
        {
            if(hand.get(i).getRank()==hand.get(i-1).getRank()&&
              hand.get(i-1).getRank()==hand.get(i-2).getRank()&&
               hand.get(i-2).getRank()==hand.get(i-3).getRank())
            {
                return true;
            }
        }
        return false;
    }
    
    public boolean isFlush(ArrayList<Card> hand)
    {
        //checks if the two consecutive cars have the same suit. 
        //If any pair of the cards have different suits, returns false.
        for(int i=1;i<5;i++)
        {
            if(hand.get(i).getSuit()!=hand.get(i-1).getSuit())
            {
                return false;
            }
        }
        return true;
    }
    
    public boolean isStraight(ArrayList<Card> hand)
    {
        int difference = 0;
        boolean outcome = false;
        
        //Checks for the exceptional straight case where 
        // a 10, a jack, a queen, a king and an ace are considered
        //as straight.
        if(hand.get(0).getRank()==1&&hand.get(1).getRank()==10
           &&hand.get(2).getRank()==11&&hand.get(3).getRank()==12
           &&hand.get(4).getRank()==13)
        {
            return true;
        }          
        
        //Checks for all the other general cases.
        for(int i=1;i<5;i++)
        {
            difference = hand.get(i).getRank()-hand.get(i-1).getRank();
            if(difference==1)
            {
                outcome = true;
            }
            else
            {
               return false; 
            }
        }
        return outcome;
    }
    
    public boolean isFullHouse(ArrayList<Card> hand)
    {
        //Returns true if three of a kind and pair are true
        //and also checks if the pair is different from the
        //
       
        if(isThreeOfAKind(hand))
        {
            //If it's three of a kind, then check for the other 
            //two consecutive cards in the hand. If they are equal 
            //return true. 
            if(hand.get(2).getRank()==hand.get(3).getRank())
                //determines the position of the three equal cards.
            {
                return (hand.get(0).getRank()==hand.get(1).getRank());
            }
            else
            {
                return (hand.get(3).getRank()==hand.get(4).getRank());
            }
        }     
        return false;
    }
    
    public boolean isStraightFlush(ArrayList<Card> hand)
    {
        //Returns true if straight and flush are true.
        if(isStraight(hand)&&isFlush(hand))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean isRoyalFlush(ArrayList<Card> hand)
    {
        //Returns true if StraightFlush is true & if the first card has a 
        //rank of 1 and the last rank has a rank of 13.
        if(isStraightFlush(hand)&&(hand.get(0).getRank()==1)&&
          hand.get(4).getRank()==13)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
}
