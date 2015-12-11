
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class arrayDemo extends Applet implements Runnable
{
   public Card[] deck;	
   public Card hold;
   public Table table;
   public int rand, x, y, valcount, suitcount, count, dealtCards;
   public Player[] players;
   public Thread thread;
   
   public void init()
   {
      deck = new Card[52];	
      players = new Player[4];
   	setSize(850,600);
      for(suitcount=0;suitcount<4;suitcount=suitcount+1) //creating the cards
      {
         for(valcount=0;valcount<13;valcount=valcount+1)
         {
            deck[count] = new Card(suitcount,valcount,0,0,true);
            count = count+1;
         }
      
      }
      for(x=0;x<52;x=x+1) //shuffling the cards
         {
         rand = (int)(Math.random()*52);
         hold = deck[x];
         deck[x] = deck[rand];
         deck[rand] = hold;
         }
      System.out.println("The size of the deck is  "+deck.length);
      System.out.println();
      count = 0;
      for(x=0;x<4;x=x+1) //giving out the cards to players
      {
         players[x] = new Player(x,(x*200)+100, 500);
            for(y=0;y<4;y++)
             {
             players[x].hand[y] = deck[count];
             count++;
             dealtCards++;
             }
      }
      count = 0;
      
      thread = new Thread(this);
      thread.start();
   }//init()

   public void paint(Graphics g)
   {
    
    for(x=0;x<4;x=x+1)
      {

       g.fillRect(players[x].xPos,players[x].yPos,10,10);
       g.drawString(players[x].name, players[x].xPos-10, players[x].yPos+30);
       for(y=0;y<players[x].handSize;y = y+1)
            {
             g.drawString(players[x].hand[y].val + " of " + players[x].hand[y].suit, players[x].xPos-35, players[x].yPos-35-(15*y));
            }
      }
   }// paint()
   
   public void hitTable(int period)
   {
      if(period==1)
      {
         for(int x=0;x<3;x++)
         {
             table.slot[x] = deck[dealtCards];
             dealtCards++;
          }
      }
      if(period==2)
      {
         table.slot[3] = deck[dealtCards];
         dealtCards++;  
      }
      if(period==3)
      {
         table.slot[4] = deck[dealtCards];
         dealtCards++;
      }
   
   }
   
   public void run()
   {
   
   
   
   
   
   
   //////////////////////////////
      try {
            thread.sleep(10);
         }
         catch (Exception e){ }
   }
}//Applet