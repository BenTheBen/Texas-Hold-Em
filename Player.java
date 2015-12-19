
import java.awt.*;


public class Player
{

   public String name;
   public int number, xPos, yPos, totalVal, countPair;		
   public Card[] hand;
   public int[] pairingVal, tripletVal;
   public String[] pairingString, tripletString;
   public boolean isAlive, winner;
   


   public Player(int playerNumber, int xxPos,int yyPos)
   {
     winner = false;
     hand = new Card[2];
     pairingVal = new int[2];
     tripletVal = new int[2];
     pairingString = new String[2];
     tripletString = new String[2];
     number = playerNumber;
     xPos = xxPos;
     yPos = yyPos;
     
  
     if(number==0)
     {
     name="Ben";
     }
     if(number==1)
     {
     name="Connor";
     }
     if(number==2)
     {
     name="Nate";
     }
     if(number==3)
     {
     name="Brad";
     }
   } // constructor

	








} 
