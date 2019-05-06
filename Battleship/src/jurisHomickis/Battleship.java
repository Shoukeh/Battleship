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
				System.out.println("DEBUG: Main panel code done");
				
			}
			
		};
		panel_Game.setFocusable(true);
		panel_Game.requestFocusInWindow();

		//Kod för att flytta cursor åt höger 
		panel_Game.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					
					if (Cursor.cursorX + 38 > 360) {
						cursorShipSelect = true;
						Cursor.cursorX = 485;
						Cursor.cursorY = 130;
					} else {
						cursorShipSelect = false;
						Cursor.cursorX += 38; 
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
					if (Cursor.cursorX - 38 < 0) {
						Cursor.cursorX += 0; 
					} else if (cursorShipSelect == true) {
						cursorShipSelect = false;
						Cursor.cursorX = 356;
						Cursor.cursorY = 242;
					} else {
						Cursor.cursorX -= 38; 
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
					if (Cursor.cursorY - 38 < 0) {
						Cursor.cursorY += 0; 
					} else if (cursorShipSelect == true && Cursor.cursorY - 70 > 110) {
						Cursor.cursorY -= 67;
					} else if (cursorShipSelect == true && Cursor.cursorY - 70 < 110) {
						Cursor.cursorY -= 0;
					} else {
						Cursor.cursorY -= 38; 
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
					if (Cursor.cursorY + 38 > 360) {			
						Cursor.cursorY += 0; 
					} else if (cursorShipSelect == true && Cursor.cursorY + 70 < 380) {
						Cursor.cursorY += 67;
					} else if (cursorShipSelect == true && Cursor.cursorY + 70 > 380) {
						Cursor.cursorY += 0;
					} else if (cursorShipSelect == false) {
						Cursor.cursorY += 38; 
					}
					//Repaint
					panel_Game.repaint();
					//Debug
					System.out.println("DEBUG: DOWN");
					System.out.println("X: " + Cursor.cursorX + "\nY: " + Cursor.cursorY );
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
			public void actionPerformed(ActionEvent e) {
				panel_Game.setFocusable(true);
				panel_Game.requestFocusInWindow();
			}
		});
	}
}
