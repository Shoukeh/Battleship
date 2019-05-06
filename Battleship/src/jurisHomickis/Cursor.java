package jurisHomickis;

import java.awt.Color;
import java.awt.Graphics;

public class Cursor {
	//Declare variables for the cursor
	static int cursorX = 14;
	static int cursorY = 14;
	static int cursorD = 10;
	
	//Method to draw the cursor
	public static void drawCursor (Graphics gx) {
		gx.setColor(Color.RED);
		gx.fillOval(cursorX,cursorY, cursorD, cursorD);
	}
}
