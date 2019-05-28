package jurisHomickis;

import java.awt.Color;
import java.awt.Graphics;

public class Ship {
	
	int hp, w, x, y, h, ID;
	
	
	public Ship (int x, int y, int w, int h, int ID) {
		this.ID = ID;
		switch (ID) {
		 case 1:
			 this.hp = 4;
			 break;
		 case 2:
			 this.hp = 3;
			 break;
		 case 3:
			 this.hp = 2;
			 break;
		 case 4:
			 this.hp = 1;
			 break;
		}
		this.w = w;
		this.h = h;
		this.x = x;
		this.y = y;
	}
	
	public void paintShip(Graphics gx) {
		gx.setColor(Color.BLACK);
		gx.fillOval(x-11, y-11, w, h);
		
	}
	
	public static boolean checkOverlap() {
		boolean overlap = false;
		for (int i = 0; i < Battleship.ships.size(); i++) {
			if (Battleship.ghostActive == true) {
				if (Cursor.cursorX-11 > Battleship.ships.get(i).x - 11 - 42 && Cursor.cursorX - 11 < Battleship.ships.get(i).x - 11 + Battleship.ships.get(i).w + 44 && Cursor.cursorY - 11 > Battleship.ships.get(i).y - 11 - 42 && Cursor.cursorY - 11 < Battleship.ships.get(i).y - 11 + Battleship.ships.get(i).h + 44) {
					overlap = true;
					System.out.println("ERROR: P1: Invalid Position (POINT 1 OVERLAP)");
				} 
				if (Cursor.cursorX-11 + Battleship.GhostW > Battleship.ships.get(i).x - 11 - 42 && Cursor.cursorX - 11 + Battleship.GhostW < Battleship.ships.get(i).x - 11 + Battleship.ships.get(i).w + 44 && Cursor.cursorY - 11 + Battleship.GhostH > Battleship.ships.get(i).y - 11 - 42 && Cursor.cursorY - 11 + Battleship.GhostH < Battleship.ships.get(i).y - 11 + Battleship.ships.get(i).h + 44) {
					overlap = true;
					System.out.println("ERROR: P1: Invalid Position (POINT 2 OVERLAP)");
				}
			}
		}
		if (Cursor.cursorX-11 + Battleship.GhostW > 380 || Cursor.cursorX - 11 + Battleship.GhostW < 5) {
			overlap = true;
			System.out.println("ERROR: P1: Invalid Position (POINT 2 X OUT OF BOUNDS)");
		}
		if (Cursor.cursorY - 11 + Battleship.GhostH < 5 || Cursor.cursorY + Battleship.GhostH > 400) {
			overlap = true;
			System.out.println("ERROR: P1: Invalid Position (POINT 2 Y OUT OF BOUNDS)");
		}
		return overlap;
	}
	
	public static boolean checkOverlap_P2() {
		boolean overlap = false;
		for (int i = 0; i < Battleship.ships_P2.size(); i++) {
			if (Battleship.ghostActive == true) {
				if (Cursor.cursorX_P2-11 > Battleship.ships_P2.get(i).x - 11 - 42 && Cursor.cursorX_P2 - 11 < Battleship.ships_P2.get(i).x - 11 + Battleship.ships_P2.get(i).w + 44 && Cursor.cursorY_P2 - 11 > Battleship.ships_P2.get(i).y - 11 - 42 && Cursor.cursorY_P2 - 11 < Battleship.ships_P2.get(i).y - 11 + Battleship.ships_P2.get(i).h + 44) {
					overlap = true;
					System.out.println("ERROR: P2: Invalid Position (POINT 1 OVERLAP)");
				} 
				if (Cursor.cursorX_P2-11 + Battleship.GhostW > Battleship.ships_P2.get(i).x - 11 - 42 && Cursor.cursorX_P2 - 11 + Battleship.GhostW < Battleship.ships_P2.get(i).x - 11 + Battleship.ships_P2.get(i).w + 44 && Cursor.cursorY_P2 - 11 + Battleship.GhostH > Battleship.ships_P2.get(i).y - 11 - 42 && Cursor.cursorY_P2 - 11 + Battleship.GhostH < Battleship.ships_P2.get(i).y - 11 + Battleship.ships_P2.get(i).h + 44) {
					overlap = true;
					System.out.println("ERROR: P2: Invalid Position (POINT 2 OVERLAP)");
				}
			}
		}
		if (Cursor.cursorX_P2-11 + Battleship.GhostW > 990 || Cursor.cursorX_P2 - 11 + Battleship.GhostW < 600) {
			overlap = true;
			System.out.println("ERROR: P2: Invalid Position (POINT 2 X OUT OF BOUNDS)");
		}
		if (Cursor.cursorY_P2 - 11 + Battleship.GhostH < 5 || Cursor.cursorY_P2 + Battleship.GhostH > 400) {
			overlap = true;
			System.out.println("ERROR: P2: Invalid Position (POINT 2 Y OUT OF BOUNDS)");
		}
		return overlap;
	}

}
