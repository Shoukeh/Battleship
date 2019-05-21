package jurisHomickis;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Battleship {

	static boolean cursorShipSelect = false;
	static boolean gameStart = false;
	
	static boolean ghostCV = false;
	static boolean ghostBB = false;
	static boolean ghostCL = false;
	static boolean ghostDD = false;
	
	static boolean draw_CV = false;
	static boolean draw_BB = false;
	static boolean draw_CL = false;
	static boolean draw_DD = false;
	
	int GhostW, GhostH, GhostTemp;
	
	static boolean mouseOccupied = false;

	private JFrame frmBattleship;
	
	ArrayList<Ship> ships = new ArrayList<Ship>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Battleship window = new Battleship();
					window.frmBattleship.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public Battleship() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBattleship = new JFrame();
		frmBattleship.setTitle("BATTLESHIP");
		frmBattleship.setBounds(100, 100, 1000, 458);
		frmBattleship.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBattleship.getContentPane().setLayout(null);
		
		
		JPanel panel_Game = new JPanel(){
			public void paint(Graphics gx) {
				//Rita Grid för P1
				Grid.posX = 0;										//X coord till Griden
				Grid.WH = 10;										//Sätta WidthHeigth variablen i Grid klassen till 10
				Grid.DrawGrid(gx); 									//Rita en Grid för den första spelaren
				
				//Rita Grid för P2
				Grid.posX = 606;									//X coord till den andra Griden
				Grid.WH = 10;
				Grid.DrawGrid(gx);
				
				//Rita "ship builder" meny:
				ShipBuilder.DrawModels(gx);
				
				//Rita Cursor
				Cursor.drawCursor(gx);
				
				//Rita ghosts
				if (ghostCV == true) {
					ShipBuilder.DrawGhostShip(gx, Cursor.cursorX, Cursor.cursorY, GhostW, GhostH);
				}
				
				if (ghostBB == true) {
					ShipBuilder.DrawGhostShip(gx, Cursor.cursorX, Cursor.cursorY, GhostW, GhostH);
				}
				
				if (ghostCL == true) {
					ShipBuilder.DrawGhostShip(gx, Cursor.cursorX, Cursor.cursorY, GhostW, GhostH);
				}
				
				if (ghostDD == true) {
					ShipBuilder.DrawGhostShip(gx, Cursor.cursorX, Cursor.cursorY, GhostW, GhostH);
				}
				
				for (int i = 0; i < ships.size(); i++) {
					ships.get(i).paintShip(gx);		//paint actual ships
				}
				
				setFocusable(true);
				requestFocusInWindow();
				
				//DEBUG
				System.out.println("DEBUG: Main panel code done");
				
			}
			
		};
		
		
		//panel_Game.setFocusable(true);
		//panel_Game.requestFocusInWindow();

		/* KOD FÖR CURSORs FÖRFLYTTNING */
		//Kod för att flytta cursor åt höger 
		panel_Game.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					
					if (gameStart == false) {
						Cursor.cursorRightPre();
					} else {
						
					}
					//Repaint
					panel_Game.repaint();
					//Debug
					System.out.println("DEBUG: RIGHT");
					System.out.println("DEBUG: x: " + Cursor.cursorX + "\nDEBUG: y: " + Cursor.cursorY );
				}
			}
		});
		//Kod för att flytta cursor åt vänster
		panel_Game.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					//Prevent the cursor from going off screen
					if (gameStart == false) {
						Cursor.cursorLeftPre();
					} else {
						
					}
					//Repaint
					panel_Game.repaint();
					//Denug
					System.out.println("DEBUG: LEFT");
					System.out.println("DEBUG: x: " + Cursor.cursorX + "\nDEBUG: y: " + Cursor.cursorY );
				}
			}
		});
		//Kod för att flytta cursor uppåt
		panel_Game.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_UP) {
					//Prevent the cursor from going off screen
					if (gameStart == false) {
						Cursor.cursorUpPre();
					} else {
						
					}
					//Repaint
					panel_Game.repaint();
					//Debug
					System.out.println("DEBUG: UP");
					System.out.println("DEBUG: x: " + Cursor.cursorX + "\nDEBUG: y: " + Cursor.cursorY );
				}
			}
		});
		//Kod för att flytta cursor neråt
		panel_Game.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					//Prevent the cursor from going off screen
					if (gameStart == false) {
						Cursor.cursorDownPre();
					} else {
						
					}
					//Repaint
					panel_Game.repaint();
					//Debug
					System.out.println("DEBUG: DOWN");
					System.out.println("DEBUG: x: " + Cursor.cursorX + "\nDEBUG: y: " + Cursor.cursorY );
				}
			}
		});
		
		
		/*ENTER KNAPPEN - KEY LISTENER*/
		panel_Game.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					int shipID = Cursor.whatShip(Cursor.cursorX, Cursor.cursorY);
					if (mouseOccupied == false) {	
						switch (shipID) {
						case 1:
							ghostCV = true;
							mouseOccupied = true;
							GhostW = ShipBuilder.L_CV;
							GhostH = ShipBuilder.ShipW;
							break;
						case 2:
							ghostBB = true;
							mouseOccupied = true;
							GhostW = ShipBuilder.L_BB;
							GhostH = ShipBuilder.ShipW;
							break;
						case 3:
							ghostCL = true;
							mouseOccupied = true;
							GhostW = ShipBuilder.L_CL;
							GhostH = ShipBuilder.ShipW;
							break;
						case 4:
							ghostDD = true;
							mouseOccupied = true;
							GhostW = ShipBuilder.L_DD;
							GhostH = ShipBuilder.ShipW;
							break;
						}
						
						
					} else if (mouseOccupied == true){
						if (ghostCV == true) {
							ghostCV = false;
							draw_CV = true;
							mouseOccupied = false;
							Ship CV = new Ship(Cursor.cursorX, Cursor.cursorY, GhostW, GhostH);
							ships.add(CV);
							System.out.println("owo");
						} else if (ghostBB == true) {
							ghostBB = false;
							draw_BB = true;
							mouseOccupied = false;
							Ship BB = new Ship(Cursor.cursorX, Cursor.cursorY, GhostW, GhostH);
							ships.add(BB);
						} else if (ghostCL == true) {
							ghostCL = false;
							draw_CL = true;
							mouseOccupied = false;
							Ship CL = new Ship(Cursor.cursorX, Cursor.cursorY, GhostW, GhostH);
							ships.add(CL);
						} else if (ghostDD == true) {
							ghostDD = false;
							draw_DD = true;
							mouseOccupied = false;
							Ship DD = new Ship(Cursor.cursorX, Cursor.cursorY, GhostW, GhostH);
							ships.add(DD);
						}
					}
					panel_Game.repaint();
					//Debug
					System.out.println("DEBUG: ENTER");
					System.out.println("DEBUG: shipID = " + shipID);
				}
			}
		});
		
		//KeyListener för knappen R - roterar ghosts
		panel_Game.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_R) {
					//Flip H and W to rotate the ghost ship
					System.out.println(GhostW + " " + GhostH);
					
					GhostTemp = GhostW;
					GhostW = GhostH;
					GhostH = GhostTemp;
					
					System.out.println(GhostW + " " + GhostH);
					
					//Repaint
					panel_Game.repaint();
					//Debug
					System.out.println("DEBUG: Rotate");
				}
			}
		});
		
		
		
		
		panel_Game.setBounds(6, 37, 988, 381);
		frmBattleship.getContentPane().add(panel_Game);
		panel_Game.setLayout(null);
		
		JButton btnRegainFocus = new JButton("Regain Focus");
		btnRegainFocus.setBounds(443, 6, 117, 29);
		frmBattleship.getContentPane().add(btnRegainFocus);
		btnRegainFocus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		//Knapp för att fokusera på panelen för att kunna flytta cursor.
				panel_Game.requestFocusInWindow();
			}
		});
	}
}
