// Car class with rectangle

//you need this import to use rectangles!
import java.awt.*;


public class Card
{

	//VARIABLE DECLARATION SECTION
	//Here's where you state which variables you are going to use.
   public String suit;
   public String val;	
   public int xpos;				//the x position
   public int ypos;				//the y position
   public boolean isAlive;			//a boolean to denote if the hero is alive or dead.
   public int width;
   public int height;
   public int trueVal;


   public Card(int pSuit, int pValue, int pXpos, int pYpos)
   {
      isAlive = false;
      trueVal = pValue;
      if(pValue==0)
      {
         val = "2";
      }
      if(pValue==1)
      {
         val = "3";
      }
      if(pValue==2)
      
      {
         val = "4";
      }
         
      if(pValue==3)
       
      {
         val = "5";
      }
      if(pValue==4)
      
       
      {
         val = "6";
      }
      if(pValue==5)
      {
         val = "7";
      }
      if(pValue==6)
      {
         val = "8";
      }
      if(pValue==7)
      {
         val = "9";
      }
      if(pValue==8)
      {
         val = "10";
      }
       
      if(pValue==9)
      {
         val = "Jack";
      }
      if(pValue==10)
      {
         val = "Queen";
      }
      if(pValue==11)
      {
         val = "King";
      }
      if(pValue==12)
      {
         val = "Ace";
      }
      
      if(pSuit==0)
      {
         suit = "Hearts";
      }
      if(pSuit==1)
      {
         suit = "Diamonds";
      }
      if(pSuit==2)
      {
         suit = "Clubs";
      }
      if(pSuit==3)
      {
         suit = "Spades";
      }
      
   
   } // constructor

	//The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
	








} 
