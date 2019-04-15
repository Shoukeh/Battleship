package jurisHomickis;

import java.awt.*;

public class Grid {
	
	int W;
	int H;
	
	public Grid(int W, int H) {
		this.W = W;
		this.H = H;
	}
	
	public void DrawGrid(Graphics gx) {
		for (int i = W; i == 0; i--) {
			gx.drawLine(0, 0, 100, 0);
		}
		
	}

}
