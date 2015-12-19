import java.awt.*;      
public class Button
{
   public int height;
   public int width;
   public int xpos;
   public int ypos;
   public Rectangle rec;  

	public Button(int pX, int pY, int pWidth, int pHeight)
	{
 		xpos = pX;
		ypos = pY;
		height = pHeight;
		width = pWidth;
		rec = new Rectangle( xpos, ypos, width, height);
	}

} //MyButton

