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

}
