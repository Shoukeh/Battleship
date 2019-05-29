package jurisHomickis;

import java.awt.Color;
import java.awt.Graphics;

public class Block {
	
	int x, y;
	int w = 380;
	int h = 380;
	
	public Block (int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
	}
	
	//rita ut skärmar
	public void SwitchTurn(Graphics gx) {
		gx.setColor(Color.GRAY);
		gx.fillRect(x, y, h, w);
	}

}
