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
	
	public static int whatShip(int cx, int cy) {		//metod för att lista ut vilken skepp man valde i ShipBuilder. cx = cursors X coord, cy = cursors Y coord
		int shipID = 0; 						//ShipID = vilken skepp cursor står på när man kallade metoden (fungerar endast på ShipBuilder)
		if (cx == 485 && cy == 130) {			//Kolla om cursor står på första skeppen i listan
			shipID = 1;
		} else if (cx == 485 && cy == 197) {	//Kolla om cursor står på andra skeppen i listan
			shipID = 2;
		} else if (cx == 485 && cy == 264) {	//Kolla om cursor står på tredje skeppen i listan
			shipID = 3;
		} else if (cx == 485 && cy == 331) {	//Kolla om cursor står på sista skeppen i listan
			shipID = 4;
		} else {
			shipID = 0;							//I fall kursor inte står någonstans på ShipBuilder
		}
		return shipID;
		
		/*shipID index:
		0 = null, ERROR
		1 = CV
		2 = BB
		3 = CL
		4 = DD
		*/
	}
}
