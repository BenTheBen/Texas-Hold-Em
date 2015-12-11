
import java.awt.*;


public class Player
{

   public String name, display;
   public int number, xPos, yPos, x, handSize, totalVal;		
   public Card[] hand;
   public boolean isAlive;
   


   public Player(int playerNumber, int xxPos,int yyPos)
   {
     handSize = 4;
     hand = new Card[handSize];
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
