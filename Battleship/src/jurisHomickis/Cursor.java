package jurisHomickis;

import java.awt.Color;
import java.awt.Graphics;

public class Cursor {
	//Declare variables for the cursor
	static int cursorX = 14;
	static int cursorY = 14;
	static int cursorD = 10;
	
	static int cursorX_P2 = 14+606;
	static int cursorY_P2 = 14;
	static int cursorD_P2 = 10;
	
	static Color orangered = new Color (255, 180, 0);
	
	//Method to draw the cursor
	public static void drawCursor (Graphics gx) {
		gx.setColor(orangered);
		gx.fillOval(cursorX,cursorY, cursorD, cursorD);
	}
	public static void drawCursorP2 (Graphics gx) {
		gx.setColor(Color.GREEN);
		gx.fillOval(cursorX_P2, cursorY_P2, cursorD, cursorD);
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
	}
		/*shipID index:
		0 = null
		1 = CV
		2 = BB
		3 = CL
		4 = DD
		*/
		
		//Methods for movement
		//RIGHT
		public static void cursorRightPreP1() {
			if (Cursor.cursorX + 38 > 360) {
				Battleship.cursorShipSelect = true;
				Cursor.cursorX = 485;
				Cursor.cursorY = 130;
			} else {
				Battleship.cursorShipSelect = false;
				Cursor.cursorX += 38; 
			}
			DebugP1();
		}
		public static void cursorRightPreP2() {
			if (Cursor.cursorX_P2 + 38 > 990) {
				Cursor.cursorX_P2 += 0;
			} else if (Battleship.cursorShipSelect == true) {
				Battleship.cursorShipSelect = false;
				Cursor.cursorX_P2 = 620;
				Cursor.cursorY_P2 = 242;
			} else {
				Cursor.cursorX_P2 += 38; 
			}
			DebugP2();
		}
		public static void cursorRightP1() {
			if (Cursor.cursorX + 38 > 360) {
				Cursor.cursorX += 0; 
			} else {
				Cursor.cursorX += 38; 
			}
			DebugP1();
		}
		public static void cursorRightP2() {
			if (Cursor.cursorX_P2 + 38 > 990) {
				Cursor.cursorX_P2 += 0; 
			} else {
				Cursor.cursorX_P2 += 38; 
			}
			DebugP2();
		}
		
		//LEFT
		public static void cursorLeftPreP1() {
			if (Cursor.cursorX - 38 < 0) {
				Cursor.cursorX += 0; 
			} else if (Battleship.cursorShipSelect == true) {
				Battleship.cursorShipSelect = false;
				Cursor.cursorX = 356;
				Cursor.cursorY = 242;
			} else {
				Cursor.cursorX -= 38; 
			}
			DebugP1();
		}
		public static void cursorLeftPreP2() {
			if (Cursor.cursorX_P2 - 38 < 620) {
				Battleship.cursorShipSelect = true;
				Cursor.cursorX_P2 = 485;
				Cursor.cursorY_P2 = 130;
			} else {
				Cursor.cursorX_P2 -= 38; 
			}
			DebugP2();
		}
		public static void cursorLeftP1() {
			if (Cursor.cursorX - 38 < 0) {
				Cursor.cursorX += 0; 
			} else {
				Cursor.cursorX -= 38; 
			}
			DebugP1();
		}
		public static void cursorLeftP2() {
			if (Cursor.cursorX_P2 - 38 < 620) {
				Cursor.cursorX_P2 += 0;
			} else {
				Cursor.cursorX_P2 -= 38; 
			}
			DebugP2();
		}
		
		//UP
		public static void cursorUp() {
			if (Cursor.cursorY - 38 < 0) {
				Cursor.cursorY += 0; 
			} else if (Battleship.cursorShipSelect == true && Cursor.cursorY - 70 > 110) {
				Cursor.cursorY -= 67;
			} else if (Battleship.cursorShipSelect == true && Cursor.cursorY - 70 < 110) {
				Cursor.cursorY -= 0;
			} else {
				Cursor.cursorY -= 38; 
			}
		}
		public static void cursorUpP2() {
			if (Cursor.cursorY_P2 - 38 < 0) {
				Cursor.cursorY_P2 += 0; 
			} else if (Battleship.cursorShipSelect == true && Cursor.cursorY_P2 - 70 > 110) {
				Cursor.cursorY_P2 -= 67;
			} else if (Battleship.cursorShipSelect == true && Cursor.cursorY_P2 - 70 < 110) {
				Cursor.cursorY_P2 -= 0;
			} else {
				Cursor.cursorY_P2 -= 38; 
			}
		}
		
		//DOWN
		public static void cursorDown() {
			if (Cursor.cursorY + 38 > 360) {			
				Cursor.cursorY += 0; 
			} else if (Battleship.cursorShipSelect == true && Cursor.cursorY + 70 < 380) {
				Cursor.cursorY += 67;
			} else if (Battleship.cursorShipSelect == true && Cursor.cursorY + 70 > 380) {
				Cursor.cursorY += 0;
			} else if (Battleship.cursorShipSelect == false) {
				Cursor.cursorY += 38; 
			}
		}
		public static void cursorDownP2() {
			if (Cursor.cursorY_P2 + 38 > 360) {			
				Cursor.cursorY_P2 += 0; 
			} else if (Battleship.cursorShipSelect == true && Cursor.cursorY_P2 + 70 < 380) {
				Cursor.cursorY_P2 += 67;
			} else if (Battleship.cursorShipSelect == true && Cursor.cursorY_P2 + 70 > 380) {
				Cursor.cursorY_P2 += 0;
			} else if (Battleship.cursorShipSelect == false) {
				Cursor.cursorY_P2 += 38; 
			}
		}
		
		//DEBUG kod
		public static void DebugP1() {
			System.out.println("DEBUG: P1 pos x: " + Cursor.cursorX + " || y: " + Cursor.cursorY );
		}
		
		public static void DebugP2() {
			System.out.println("DEBUG: P2 pos x: " + Cursor.cursorX_P2 + " || y: " + Cursor.cursorY_P2 );
		}
		
}
