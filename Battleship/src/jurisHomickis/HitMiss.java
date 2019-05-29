package jurisHomickis;

import java.awt.Color;
import java.awt.Graphics;

public class HitMiss {
	
	int xx, yy, w;
	boolean hit;
	
	//Skapa Hit eller Miss objekt
	public HitMiss(int x, int y, boolean hit) {
		this.xx = x;
		this.yy = y;
		this.hit = hit;
	}
	
	//Rita Hit eller miss
	public void paintHitMiss(Graphics gx) {
		if (hit == true) {
			this.w = 33;
			gx.setColor(Color.RED);
			gx.fillRect(xx-11, yy+1, w, 9);
			gx.fillRect(xx+1, yy-11, 9, w);
		} else {
			this.w = 16;
			gx.setColor(Color.BLUE);
			gx.fillOval(xx-3, yy-3, w, w);
		}
	}
	
	/*
	 * Metoden som kollar om man har träfat eller missat
	 * Först kollar metoden om du försöker skjuta någonstans där du redan har gjort det. Om det är så händer det ingenting.
	 * Sen kollar den om man har träffat något skepp
	 */
	public static int HitOrMiss() {
		int hitID = 1;
		boolean cont = true; //true = man kan fortsätta till nästa, false = man är inte klar
		if (Battleship.PlayerID == 1) {
			for (int j = 0; j < Battleship.markers.size(); j++) {
				if (Cursor.cursorX == Battleship.markers.get(j).xx && Cursor.cursorY == Battleship.markers.get(j).yy) {
					System.out.println("Marker Overlap");
					hitID = 0;
					cont = false;
				} 
			}
		}
		if (Battleship.PlayerID == 3) {
			for (int j = 0; j < Battleship.markers_P2.size(); j++) {
				if (Cursor.cursorX_P2 == Battleship.markers_P2.get(j).xx && Cursor.cursorY_P2 == Battleship.markers_P2.get(j).yy) {
					System.out.println("Marker Overlap");
					hitID = 0;
					cont = false;
				}
			}
		}
		if (cont == true) {
			if (Battleship.PlayerID == 1) {
				for (int i = 0; i < Battleship.ships_P2.size(); i++) {
					if (Cursor.cursorX+606 > Battleship.ships_P2.get(i).x-11 && Cursor.cursorX+606 < Battleship.ships_P2.get(i).x-11+Battleship.ships_P2.get(i).w && Cursor.cursorY > Battleship.ships_P2.get(i).y - 11 && Cursor.cursorY < Battleship.ships_P2.get(i).y - 11 + Battleship.ships_P2.get(i).h) {			
						hitID = 2;
						Battleship.ships_P2.get(i).hp -= 1;
						System.out.println("SHIP: " + Battleship.ships_P2.get(i));
						System.out.println("HP: " + Battleship.ships_P2.get(i).hp);
						System.out.println("INFO: P1 Hit");
					}
				}
			} else if (Battleship.PlayerID == 3) {
				for (int i = 0; i < Battleship.ships.size(); i++) {
					if (Cursor.cursorX_P2-606 > Battleship.ships.get(i).x-11 && Cursor.cursorX_P2-606 < Battleship.ships.get(i).x-11+Battleship.ships.get(i).w) {
						if (Cursor.cursorY_P2 > Battleship.ships.get(i).y - 11 && Cursor.cursorY_P2 < Battleship.ships.get(i).y - 11 + Battleship.ships.get(i).h) {
							hitID = 2;
							Battleship.ships.get(i).hp -= 1;
							System.out.println("SHIP: " + Battleship.ships.get(i));
							System.out.println("HP: " + Battleship.ships.get(i).hp);
							System.out.println("INFO: P2 Hit");
						}
					}
				}
			} 
		}
		//ships.get(i).x
		return hitID; //0: inget hände, 1: Miss, 2: träff
	}

}
