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

	//Variable set up
	//General Game vars
	static boolean cursorShipSelect = false;
	static boolean gameStart = false;
	static int PlayerID = 0;
	
	//Ghost placement releated booleans
	static boolean ghostCV = false;
	static boolean ghostBB = false;
	static boolean ghostCL = false;
	static boolean ghostDD = false;
	
	//Placement/placement check variables
	static int GhostW;
	static int GhostH;
	static boolean ghostActive;
	int GhostTemp;
	String LastShip;
	
	//Ship count
	static int CountCV = 1;
	static int CountBB = 2;
	static int CountCL = 3;
	static int CountDD = 4;
	
	static int CountCV_P2 = 1;
	static int CountBB_P2 = 2;
	static int CountCL_P2 = 3;
	static int CountDD_P2 = 4;
	
	//Mouse state boolean
	static boolean mouseOccupied = false;

	private JFrame frmBattleship;
	
	//Array med alla skeppar, oavsett typen
	static ArrayList<Ship> ships = new ArrayList<Ship>();
	
	static ArrayList<Block> blocks = new ArrayList<Block>();

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
				
				//Rita skeppar
				for (int i = 0; i < ships.size(); i++) {
					ships.get(i).paintShip(gx);		//rita ut alla skeppar som finns med i ArrayList ships
				}
				
				for (int i = 0; i < blocks.size(); i++) {
					blocks.get(i).SwitchTurn(gx);		//rita ut alla skeppar som finns med i ArrayList ships
				}
				
				//Rita Cursor
				Cursor.drawCursor(gx);

				//Set focus to the game panel
				setFocusable(true);
				requestFocusInWindow();
				
				//DEBUG
				System.out.println("DEBUG: panel code exec");
				
			}
			
		};
		
		
		//panel_Game.setFocusable(true);
		//panel_Game.requestFocusInWindow();

		/* KOD FÖR CURSORs FÖRFLYTTNING */
		//Kod för att hantera cursor x+ riktning 
		panel_Game.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					//call x+ handler method
					if (gameStart == false) {
						Cursor.cursorRightPreP1();
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
		//Kod för att hantera cursor x- riktning 
		panel_Game.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					//call x- handler method
					if (gameStart == false) {
						Cursor.cursorLeftPreP1();
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
		//Kod för att hantera cursor y- riktning 
		panel_Game.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_UP) {
					//call y- handler method
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
		//Kod för att hantera cursor y+ riktning 
		panel_Game.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					//call y+ handler method
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
		
		//Undo knapp
		//skapas mitt i allt för att den blir kallad efter i en metod längre neråt
		JButton btnUndo = new JButton("Undo");
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(LastShip) {						//Case som kollar vilken typ av skepp ska tas bort, och vilken counter ska återställas
				case "CV":
					CountCV += 1;						//Återställer CountXX där XX är typen
					if (CountCV > 1) CountCV = 1;		//Om på något sätt CountXX är större än den ska vara återställ till dess max värde
					ships.remove(ships.size()-1);		//Ta bort skeppet från ships ArrayList
					btnUndo.setEnabled(false);			//Stäng av knappen
					break;
				case "BB":
					CountBB += 1;
					if (CountBB > 2) CountBB = 2;
					ships.remove(ships.size()-1);
					btnUndo.setEnabled(false);
					break;
				case "CL":
					CountCL += 1;
					if (CountCL > 3) CountCL = 3;
					ships.remove(ships.size()-1);
					btnUndo.setEnabled(false);
					break;
				case "DD":
					CountDD += 1;
					if (CountDD > 4) CountDD = 4;
					ships.remove(ships.size()-1);
					btnUndo.setEnabled(false);
					break;
				} 
				//Repaint
				panel_Game.repaint();
			}
		});
		btnUndo.setBounds(440, 6, 117, 29);
		frmBattleship.getContentPane().add(btnUndo);
		
		/*ENTER KNAPPEN - KEY LISTENER*/
		panel_Game.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					int shipID = Cursor.whatShip(Cursor.cursorX, Cursor.cursorY); 	//vilken skepp har man valt från ShipBuilder?
					if (mouseOccupied == false) {	
						switch (shipID) {
						case 1:
							if (CountCV != 0) {  				//Om det finns någon skepp kvar att sätta ut
								ghostCV = true;					//set ghostXX till true för att rita ut den senare i panel koden		
								mouseOccupied = true;			//Musen är upptagen, dvs att man ska sätta ut grejer
								GhostW = ShipBuilder.L_CV;		//GhostW och GhostH är dåvarande Ghosts bredd och höjd i px
								GhostH = ShipBuilder.ShipW;
								ghostActive = true;
							}
							break;
						case 2:
							if (CountBB != 0) {
								ghostBB = true;
								mouseOccupied = true;
								GhostW = ShipBuilder.L_BB;
								GhostH = ShipBuilder.ShipW;
								ghostActive = true;
							}
							break;
						case 3:
							if (CountCL != 0) {
								ghostCL = true;
								mouseOccupied = true;
								GhostW = ShipBuilder.L_CL;
								GhostH = ShipBuilder.ShipW;
								ghostActive = true;
							}
							break;
						case 4:
							if (CountDD != 0) {
								ghostDD = true;
								mouseOccupied = true;
								GhostW = ShipBuilder.L_DD;
								GhostH = ShipBuilder.ShipW;
								ghostActive = true;
							}
							break;
						}
						
						
					} else if (mouseOccupied == true){
						if (Ship.checkOverlap() == false) {
							if (ghostCV == true) {	
								ghostCV = false;
								mouseOccupied = false;
								Ship CV = new Ship(Cursor.cursorX, Cursor.cursorY, GhostW, GhostH);
								ships.add(CV);
								CountCV -= 1;
								LastShip = "CV";
								ghostActive = false;
								btnUndo.setEnabled(true);
							} else if (ghostBB == true) {
								ghostBB = false;
								mouseOccupied = false;
								Ship BB = new Ship(Cursor.cursorX, Cursor.cursorY, GhostW, GhostH);
								ships.add(BB);
								CountBB -= 1;
								LastShip = "BB";
								ghostActive = false;
								btnUndo.setEnabled(true);
							} else if (ghostCL == true) {
								ghostCL = false;
								mouseOccupied = false;
								Ship CL = new Ship(Cursor.cursorX, Cursor.cursorY, GhostW, GhostH);
								ships.add(CL);
								CountCL -= 1;
								LastShip = "CL";
								ghostActive = false;
								btnUndo.setEnabled(true);
							} else if (ghostDD == true) {
								ghostDD = false;
								mouseOccupied = false;
								Ship DD = new Ship(Cursor.cursorX, Cursor.cursorY, GhostW, GhostH);
								ships.add(DD);
								CountDD -= 1;
								LastShip = "DD";
								ghostActive = false;
								btnUndo.setEnabled(true);
							}
						} else {
							System.out.println("DEBUG: Placement fail");
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
			public void keyReleased(KeyEvent e) {				//Flippa runt värde på GhostW och GhostH 
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
					System.out.println("DEBUG: Player rotated the ghost");
				}
			}
		});
		
		
		
		
		panel_Game.setBounds(6, 37, 988, 381);
		frmBattleship.getContentPane().add(panel_Game);
		panel_Game.setLayout(null);
		
		JButton btnRegainFocus = new JButton("Regain Focus");
		btnRegainFocus.setBounds(550, 6, 117, 29);
		frmBattleship.getContentPane().add(btnRegainFocus);
		
		JButton btnAdvanceState = new JButton("Next Player");
		btnAdvanceState.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* PlayerID Index:
				 * 1:P1
				 * 2:Switch Period
				 * 3:P2
				 * 4:SwitchPeriod
				 */
				PlayerID += 1;
				System.out.println(PlayerID);
				switch (PlayerID) {
					case 1:
						blocks.clear();
						Block B1 = new Block(606, 0, 380, 380);
						blocks.add(B1);
						panel_Game.repaint();
						break;
					case 2:
						Block B2 = new Block(0, 0, 380, 380);
						blocks.add(B2);
						panel_Game.repaint();
						break;
					case 3: 
						blocks.clear();
						Block B3 = new Block(0, 0, 380, 380);
						blocks.add(B3);
						panel_Game.repaint();
						break;
					case 4:
						Block B4 = new Block(606, 0, 380, 380);
						blocks.add(B4);
						PlayerID = 0;
						panel_Game.repaint();
						break;	
				}
				//panel_Game.repaint();
				
			}
		});
		btnAdvanceState.setBounds(331, 6, 117, 29);
		frmBattleship.getContentPane().add(btnAdvanceState);
		
		JButton btnClearBoardP1 = new JButton("Clear Board: P1");
		btnClearBoardP1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ships.clear();			//ta bort alla skeppar från grid
				CountCV = 1;
				CountBB = 2;
				CountCL = 3;
				CountDD = 4;
				panel_Game.repaint();
			}
		});
		btnClearBoardP1.setBounds(6, 6, 136, 29);
		frmBattleship.getContentPane().add(btnClearBoardP1);
		
		JButton btnClearBoardP2 = new JButton("Clear Board: P2");
		btnClearBoardP2.setBounds(843, 6, 151, 29);
		frmBattleship.getContentPane().add(btnClearBoardP2);
		
		btnRegainFocus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		//Knapp för att fokusera på panelen för att kunna flytta cursor.
				panel_Game.requestFocusInWindow();
			}
		});
	}
}
