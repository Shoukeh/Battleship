package jurisHomickis;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
	
	
	static boolean mouseOccupied = false;

	private JFrame frmBattleship;

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
					ShipBuilder.DrawGhostShip(gx, Cursor.cursorX, Cursor.cursorY, 144);
				}
				
				if (ghostBB == true) {
					ShipBuilder.DrawGhostShip(gx, Cursor.cursorX, Cursor.cursorY, 106);
				}
				
				if (ghostCL == true) {
					ShipBuilder.DrawGhostShip(gx, Cursor.cursorX, Cursor.cursorY, 68);
				}
				
				if (ghostDD == true) {
					ShipBuilder.DrawGhostShip(gx, Cursor.cursorX, Cursor.cursorY, 30);
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
							break;
						case 2:
							ghostBB = true;
							break;
						case 3:
							ghostCL = true;
							break;
						case 4:
							ghostDD = true;
							break;
						}
						mouseOccupied = true;
						
					} else if (mouseOccupied == true){
						switch (shipID) {
						case 1:
							ghostCV = false;
							draw_CV = true;
							break;
						case 2:
							ghostBB = false;
							draw_BB = true;
							break;
						case 3:
							ghostCL = false;
							draw_CL = true;
							break;
						case 4:
							ghostDD = false;
							draw_DD = true;
							break;
						}
					}
					//Debug
					System.out.println("DEBUG: ENTER");
					System.out.println("DEBUG: shipID = " + shipID);
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
