package jurisHomickis;

import java.awt.*;

public class Grid {
	
	static int WH;						//WH betyder Width and Heighth, alltså höjd och bredd (antalet cells, t.ex. 10x10). Används för att rita en grid.
	static int WHpx = 380;			    //WH i pixels.
	static int CellWH = WHpx/10;		//WH i pixel på en cell. 
	static int posX = 0; 					//positionen till första X koordinaten 
	static int posY = 0; 					//positionen till första Y koordinaten
	
	public static void DrawGrid(Graphics gx) {			//metoden för att rita en grid
		for (int i = 0; i < WH+1; i++) {				//loopen som körs WH+1 antal gånger (eftersom en grid med 10 celler behöver 11 linjer)
			gx.setColor(Color.BLACK);					//sätta färgen till svart
			gx.drawLine(posX, CellWH*i, WHpx+posX, CellWH*i);	//rita horizontala linjer
			gx.drawLine(CellWH*i+posX, posY, CellWH*i+posX, WHpx);	//rita vertikala linjer
		}
		
	}

}
