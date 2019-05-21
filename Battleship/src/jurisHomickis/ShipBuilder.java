package jurisHomickis;

import java.awt.*;

public class ShipBuilder {
	
	static int centerX = 491;						//Centerpunkten i X coord för panel_Game; används som referens
	static int centerY = 187;						//Centerpunkten i Y coord för panel_Game; samma användning som centerX
	static int ShipX = 0;							//Skeppets X och Y position.
	static int ShipY = 0;
	static int ShipW = 30;							//Width för alla skeppar i px
	static int L_DD = 30;							//Längder för olika skeppar i px
	static int L_CL = 68;
	static int L_BB = 106;
	static int L_CV = 144;
	static int spacer = 65; 						//mellanrum mellan skeppar
	static int TextX = centerX-40;					//X koordinat för texten
	
	public static void DrawDD (Graphics gx) {		//Metoder för att rita skeppar
		gx.setColor(Color.BLACK);
		gx.fillOval(ShipX, ShipY, L_DD, ShipW);
	}
	public static void DrawCL (Graphics gx) {
		gx.setColor(Color.BLACK);
		gx.fillOval(ShipX, ShipY, L_CL, ShipW);
	}
	public static void DrawBB (Graphics gx) {
		gx.setColor(Color.BLACK);
		gx.fillOval(ShipX, ShipY, L_BB, ShipW);
	}
	public static void DrawCV (Graphics gx) {
		gx.setColor(Color.BLACK);
		gx.fillOval(ShipX, ShipY, L_CV, ShipW);
	}
	
	public static void DrawModels (Graphics gx) {
		//Metod som ritar skeppar mellan båda grids. 
		//Ska användas för att "bygga" flottan på grids.
		ShipX = centerX-(L_CV/2);						//mata in X värdet för skeppet
		ShipY = centerY-spacer;							//mata in Y värdet för skeppet 
		DrawCV(gx);										//kalla metoden för att rita ut en skepp, i detta fall en CV (Carrier)
		gx.drawString("Remaining: 1", TextX, ShipY+45);
		
		ShipX = centerX-(L_BB/2);
		ShipY += spacer;
		DrawBB(gx);
		gx.drawString("Remaining: 1", TextX, ShipY+45);

		ShipX = centerX-(L_CL/2);
		ShipY += spacer;
		DrawCL(gx);
		gx.drawString("Remaining: 1", TextX, ShipY+45);
		
		ShipX = centerX-(L_DD/2);
		ShipY += spacer;
		DrawDD(gx);
		gx.drawString("Remaining: 1", TextX, ShipY+45);
		
	}
	
	public static void DrawGhostShip (Graphics gx, int ghostX, int ghostY, int ghostW, int ghostH) { //Metod för att rita "spöken," alltså modellen som ritas för att se vart ma placerar skeppet
		gx.setColor(Color.BLACK);
		gx.fillOval(ghostX-11, ghostY-11, ghostW, ghostH);
	}
}
