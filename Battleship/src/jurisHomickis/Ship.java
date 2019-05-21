package jurisHomickis;

import java.awt.Color;
import java.awt.Graphics;

public class Ship {
	
	int hp, w, x, y, h;
	
	
	public Ship (int x, int y, int w, int h) {
		this.hp = w/3;
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
					System.out.println("Point 1 overlap");
				} 
				if (Cursor.cursorX-11 + Battleship.GhostW > Battleship.ships.get(i).x - 11 - 42 && Cursor.cursorX - 11 + Battleship.GhostW < Battleship.ships.get(i).x - 11 + Battleship.ships.get(i).w + 44 && Cursor.cursorY - 11 + Battleship.GhostH > Battleship.ships.get(i).y - 11 - 42 && Cursor.cursorY - 11 + Battleship.GhostH < Battleship.ships.get(i).y - 11 + Battleship.ships.get(i).h + 44) {
					overlap = true;
					System.out.println("Point 2 overlap");
				}
			}
		}
		return overlap;
	}

}
