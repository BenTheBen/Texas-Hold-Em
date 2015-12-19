
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class arrayDemo extends Applet implements Runnable, MouseListener, KeyListener, MouseMotionListener
{
   public Card[] deck;	
   public Card hold;
   public Table table;
   public int rand, valcount, suitcount, count, dealtCards, drawCount, minCount;
   public boolean drawTable;
   public Player[] players;
   public Button moveOn;
   public Image moveOnImage;
   public Thread thread;
   
   public void init()
   {
      deck = new Card[52];	
      players = new Player[4];
      table = new Table(200);
      moveOn = new Button(300,500,50,40);
      moveOnImage = getImage(getDocumentBase(), "Proceed.png");
      setSize(850,600);
      drawTable = false;
      addKeyListener(this);
      addMouseListener(this);
      addMouseMotionListener(this);
      drawCount = 2; //Don't touch it: needs to be 2 or else Null Pointer; adds one more right before painting to work
      
      for(suitcount=0;suitcount<4;suitcount++) //creating the cards
      {
         for(valcount=0;valcount<13;valcount++)
         {
            deck[count] = new Card(suitcount,valcount);
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
      for(int x=0;x<4;x++)
      {  //writing pairs
         for(int y=0;y<2;y++)
         {
          players[x].hand[y].xpos = players[x].xPos-50;
          players[x].hand[y].ypos = players[x].yPos-45-(15*y);
         }
         if(players[x].pairingString[0] != null)
         {
          System.out.println(players[x].name + " has a pair of " + players[x].pairingString[0] + "'s.");
          g.fillRect(players[x].hand[0].xpos, players[x].hand[0].ypos, 10, 10);
         }
         if(players[x].pairingString[1] != null)
         {
          System.out.println(players[x].name + " has a pair of " + players[x].pairingString[1] + "'s.");
          g.fillRect(players[x].hand[1].xpos, players[x].hand[1].ypos, 10, 10);
         }
       
      }
      for(int x=0;x<4;x++) //drawing players and their hands
      {
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
            g.drawImage(moveOnImage, moveOn.xpos, moveOn.ypos, moveOn.width, moveOn.height, this);
         }
      }
      for(int x=0;x<4;x++)
      {
       if(players[x].winner == true)
       {
         g.drawString(players[x].name + " wins!", 425, 300);
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
    public void decideWinner()
   {   
      for(int x=0;x<4;x++)
       {
        players[x].totalVal = players[x].pairingVal[0] + players[x].pairingVal[1];
        System.out.println(players[x].name + players[x].totalVal);
       }
       //Looping didn't work well here
          if(players[0].totalVal >= players[1].totalVal && players[0].totalVal >= players[2].totalVal && players[0].totalVal >= players[3].totalVal && players[0].totalVal > 0)
           {
             players[0].winner = true;
           }
          if(players[1].totalVal >= players[0].totalVal && players[1].totalVal >= players[2].totalVal && players[1].totalVal >= players[3].totalVal && players[1].totalVal > 0)
           {
             players[1].winner = true;
           }
          if(players[2].totalVal >= players[0].totalVal && players[2].totalVal >= players[1].totalVal && players[2].totalVal >= players[3].totalVal && players[2].totalVal > 0)
           {
             players[2].winner = true;
           }
          if(players[3].totalVal >= players[0].totalVal && players[3].totalVal >= players[1].totalVal && players[3].totalVal >= players[2].totalVal && players[3].totalVal > 0)
           {
             players[3].winner = true;
           }
  
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
              players[x].pairingVal[players[x].countPair] = table.slot[z].trueVal + 1;
              players[x].countPair++;
             }
           }
         }
       }
       
   }
   
   public void run()
   {
      for(int x=0;x<3;x++)
      {
         giveTime(0);
         hitTable(x);
         repaint();
         eval(x);
      }
      decideWinner();
   
   }
   
   
   
      //Mouse Stuff
   public void mouseDragged(MouseEvent e) {
   	
   }
   public void mouseMoved(MouseEvent e){
   
   }
   public void mousePressed(MouseEvent e) 
   {
    int mouseX = e.getX();
    int mouseY = e.getY();
   }
   public void mouseReleased(MouseEvent e) 
   {
   
   }
   public void mouseEntered(MouseEvent e) 
   {
   }
   public void mouseExited(MouseEvent e) 
   {
   }
   public void mouseClicked(MouseEvent e) 
   {
      //System.out.println("Mouse clicked (# of clicks: "+ e.getClickCount() + ")");
      
   }
   public void keyPressed( KeyEvent event ) 
   {
      String keyin;
      keyin = ""+event.getKeyText( event.getKeyCode()); 
      //System.out.println("Key pressed "+keyin);
   }//keyPressed()
   public void keyReleased( KeyEvent event ) 
   {
      String keyin;
      keyin = ""+event.getKeyText( event.getKeyCode()); 
      //System.out.println ("Key released: "+ keyin);
   }//keyReleased()
   public void keyTyped( KeyEvent event ) 
   {
      char keyin;
      keyin = event.getKeyChar();  
      //System.out.println ("Key Typed: "+ keyin);
   }//keyTyped()
   
}//Applet