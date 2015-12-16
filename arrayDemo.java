
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class arrayDemo extends Applet implements Runnable
{
   public Card[] deck;	
   public Card hold;
   public Table table;
   public int rand, valcount, suitcount, count, dealtCards, drawCount, minCount;
   public boolean drawTable;
   public Player[] players;
   public Thread thread;
   
   public void init()
   {
      deck = new Card[52];	
      players = new Player[4];
      table = new Table(200);
      setSize(850,600);
      drawTable = false;
      drawCount = 2; //Don't touch it: needs to be 2 or else Null Pointer; adds one more right before painting to work
      
      for(suitcount=0;suitcount<4;suitcount++) //creating the cards
      {
         for(valcount=0;valcount<13;valcount++)
         {
            deck[count] = new Card(suitcount,valcount,0,0);
            count = count+1;
         }
      }
      count = 0;
      for(int x=0;x<52;x++) //shuffling the cards
      {
         rand = (int)(Math.random()*52);
         hold = deck[x];
         deck[x] = deck[rand];
         deck[rand] = hold;
      }
      for(int x=0;x<4;x++) //giving out the cards to players
      {
         players[x] = new Player(x,(x*200)+100, 500);
         for(int y=0;y<2;y++)
         {
            players[x].hand[y] = deck[dealtCards];
            dealtCards++;
         }
      }
      for(int x=0;x<5;x++) //giving cards to the Table
      {
         table.slot[x] = deck[dealtCards];
         table.slot[x].xpos = (x*120);
         dealtCards++;
      }
      
      thread = new Thread(this);
      thread.start();
   }//init()

   public void paint(Graphics g)
   {
    
      for(int x=0;x<4;x++) //drawing players and their hands
      {
         g.fillRect(players[x].xPos,players[x].yPos,10,10);
         g.drawString(players[x].name, players[x].xPos-10, players[x].yPos+30);
         for(int y=0;y<2;y++)
         {
            g.drawString(players[x].hand[y].val + " of " + players[x].hand[y].suit, players[x].xPos-35, players[x].yPos-35-(15*y));
         }
      }
      if(drawTable == true) //drawing the table's hand, but only a few at a time (3 cards --> 4 --> 5)
      {
         for(int x=0;x<drawCount;x++)
         {
            g.drawString(table.slot[x].val + " of " + table.slot[x].suit, table.slot[x].xpos + 100, table.yPos);
         }
      }
   }// paint()
   
   public void hitTable(int loops)
   {
      drawTable = true;
      drawCount++;
      if(loops == 0)
      {
      minCount = 0;
      }
      if(loops == 1)
      {
      minCount = 3;
      }
      if(loops == 2)
      {
      minCount = 4;
      }
   }
   public void giveTime(int length)
   {
      try {
         thread.sleep(length);
      }
      catch (Exception e){ }
   }
   
   public void eval(int lastCard)
   {
      
      for(int x=0;x<4;x++)
       {
        for(int y=0;y<2;y++)
         {
          for(int z=minCount;z<drawCount;z++)
           {
            if(players[x].hand[y].val == table.slot[z].val)
             {
              players[x].pairingString[players[x].countPair] = table.slot[z].val;
              players[x].pairingVal[players[x].countPair] = table.slot[z].trueVal;
              players[x].countPair++;
             }
           }
         }
       }
       
   }
   public void printEval()
   {
      for(int x=0;x<4;x++)
      {
         if(players[x].pairingString[0] != null)
         {
          System.out.println(players[x].name + " has a pair of " + players[x].pairingString[0] + "'s.");
         }
         if(players[x].pairingString[1] != null)
         {
          System.out.println(players[x].name + " has a pair of " + players[x].pairingString[1] + "'s.");
         }
       
      }
   }
   public void run()
   {
      for(int x=0;x<3;x++)
      {
         giveTime(5000);
         hitTable(x);
         repaint();
         eval(x);
      }
      printEval();
   
   }
}//Applet