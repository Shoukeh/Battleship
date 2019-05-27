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
import javax.swing.JLabel;

public class Battleship {

	//Variable set up
	//General Game vars
	static boolean cursorShipSelect = false;
	static boolean gameStart = false;
	static int PlayerID = 0;
	float Turn = -2;
	boolean turnDone = false;
	
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
	static int CountBB = 1;
	static int CountCL = 1;
	static int CountDD = 1;
	
	static int CountCV_P2 = 1;
	static int CountBB_P2 = 1;
	static int CountCL_P2 = 1;
	static int CountDD_P2 = 1;
	
	//Mouse state boolean
	static boolean mouseOccupied = false;

	private JFrame frmBattleship;
	
	//Array med alla skeppar, oavsett typen
	static ArrayList<Ship> ships = new ArrayList<Ship>();
	static ArrayList<Ship> ships_P2 = new ArrayList<Ship>();
	
	static ArrayList<Ship> shipsSunk = new ArrayList<Ship>();
	static ArrayList<Ship> shipsSunk_P2 = new ArrayList<Ship>();
	
	//Arrays med alla "hits" och "miss"
	static ArrayList<HitMiss> markers = new ArrayList<HitMiss>();
	static ArrayList<HitMiss> markers_P2 = new ArrayList<HitMiss>();
	
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
		
		JButton btnClearBoardP1 = new JButton("Clear Board: P1");
		JButton btnClearBoardP2 = new JButton("Clear Board: P2");
		JButton btnAdvanceState = new JButton("Start");
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
				
				//Start the game
				if (Turn == 0) {
					gameStart = true;
					btnClearBoardP1.setEnabled(false);
					btnClearBoardP2.setEnabled(false);
				}
				
				//Rita "ship builder" meny:
				ShipBuilder.DrawModels(gx, PlayerID);
				
				//Rita ghosts
				if (ghostCV == true || ghostBB == true || ghostCL == true || ghostDD == true) {
					if (PlayerID == 1) {
						ShipBuilder.DrawGhostShip(gx, Cursor.cursorX, Cursor.cursorY, GhostW, GhostH);
					} else if (PlayerID == 3) {
						ShipBuilder.DrawGhostShip(gx, Cursor.cursorX_P2, Cursor.cursorY_P2, GhostW, GhostH);
					}
				}
				
				if (gameStart == true) {
					for (int i = 0; i < ships.size(); i++) {
						if (ships.get(i).hp == 0) {
							ships.get(i).x += 606;
							shipsSunk.add(ships.remove(i));
						}
					}
					for (int i = 0; i < ships_P2.size(); i++) {
						if (ships_P2.get(i).hp == 0) {
							ships_P2.get(i).x -= 606;
							shipsSunk_P2.add(ships_P2.remove(i));
						}
					}
					switch (PlayerID) {
						case 1:
							for (int j = 0; j < shipsSunk_P2.size(); j++) {
								shipsSunk_P2.get(j).paintShip(gx);
							}
							break;
						case 3:
							for (int j = 0; j < shipsSunk.size(); j++) {
								shipsSunk.get(j).paintShip(gx);
							}
							break;
					}
				}
				
				//Rita skeppar
				if (gameStart == false) {
					for (int i = 0; i < ships.size(); i++) {
						ships.get(i).paintShip(gx);		//rita ut alla skeppar som finns med i ArrayList ships
					}
					for (int i = 0; i < ships_P2.size(); i++) {
						ships_P2.get(i).paintShip(gx);		//rita ut alla skeppar som finns med i ArrayList ships_P2
					}
				}
				for (int i = 0; i < blocks.size(); i++) {
					blocks.get(i).SwitchTurn(gx);		//rita ut "blocks" som gör så att andra spelaren inte ser fiendets grid
				}
				
				//Rita HitMiss markers
				switch (PlayerID) {
				case 1:
					for (int i = 0; i < markers.size(); i++) {
						markers.get(i).paintHitMiss(gx);
					}
					break;
				case 3:
					for (int i = 0; i < markers_P2.size(); i++) {
						markers_P2.get(i).paintHitMiss(gx);
					}
					break;
				}
				
				//Rita Cursor
				switch (PlayerID) {
					case 1:
						Cursor.drawCursor(gx);
						break;
					case 3:
						Cursor.drawCursorP2(gx);
						break;
				}
				
				//Experiments
				if (gameStart == false) {
					if (CountCV+CountBB+CountCL+CountDD == 0) btnAdvanceState.setEnabled(true);
					if ((int)Turn == 0) btnAdvanceState.setEnabled(false);
					if (CountCV_P2+CountBB_P2+CountCL_P2+CountDD_P2 == 0) btnAdvanceState.setEnabled(true);
				}

				//Set focus to the game panel
				setFocusable(true);
				requestFocusInWindow();
				
				//DEBUG
				System.out.println("INFO: Turn: "+(int)Turn+"\nDEBUG: panel code exec");	
			}	
		};

		/* KOD FÖR CURSORs FÖRFLYTTNING */
		//Kod för att hantera cursor x+ riktning 
		panel_Game.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
					//call x+ handler method
					if (gameStart == false) {
						if(PlayerID == 1) {
							Cursor.cursorRightPreP1();
						} else if (PlayerID == 3) {
							Cursor.cursorRightPreP2();
						}
					} else {
						if(PlayerID == 1) {
							Cursor.cursorRightP1();
						} else if (PlayerID == 3) {
							Cursor.cursorRightP2();
						}
					}
					//Repaint
					panel_Game.repaint();
					//Debug
					System.out.println("DEBUG: RIGHT");
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
						if(PlayerID == 1) {
							Cursor.cursorLeftPreP1();
						} else if (PlayerID == 3) {
							Cursor.cursorLeftPreP2();
						}
					} else {
						if(PlayerID == 1) {
							Cursor.cursorLeftP1();
						} else if (PlayerID == 3) {
							Cursor.cursorLeftP2();
						}
					}
					//Repaint
					panel_Game.repaint();
					//Denug
					System.out.println("DEBUG: LEFT");
				}
			}
		});
		//Kod för att hantera cursor y- riktning 
		panel_Game.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_UP) {
					//call y- handler method
					if (PlayerID == 1) Cursor.cursorUp();
					if (PlayerID == 3) Cursor.cursorUpP2();
					
					//Repaint
					panel_Game.repaint();
					//Debug
					System.out.println("DEBUG: UP");
					if (PlayerID == 1) Cursor.DebugP1();
					if (PlayerID == 3) Cursor.DebugP2();
				}
			}
		});
		//Kod för att hantera cursor y+ riktning 
		panel_Game.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					//call y+ handler method
					if (PlayerID == 1) Cursor.cursorDown();
					if (PlayerID == 3) Cursor.cursorDownP2();
					
					//Repaint
					panel_Game.repaint();
					//Debug
					System.out.println("DEBUG: DOWN");
					if (PlayerID == 1) Cursor.DebugP1();
					if (PlayerID == 3) Cursor.DebugP2();
				}
			}
		});
		
		//Undo knapp
		//skapas mitt i allt för att den blir kallad efter senare.
		JButton btnUndo = new JButton("Undo");
		btnUndo.setEnabled(false);
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (PlayerID == 1) {
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
				} else if (PlayerID == 3) {
					switch(LastShip) {						//Case som kollar vilken typ av skepp ska tas bort, och vilken counter ska återställas
					case "CV":
						CountCV_P2 += 1;						//Återställer CountXX där XX är typen
						if (CountCV_P2 > 1) CountCV_P2 = 1;		//Om på något sätt CountXX är större än den ska vara återställ till dess max värde
						ships_P2.remove(ships_P2.size()-1);		//Ta bort skeppet från ships ArrayList
						btnUndo.setEnabled(false);			//Stäng av knappen
						break;
					case "BB":
						CountBB_P2 += 1;
						if (CountBB_P2 > 2) CountBB_P2 = 2;
						ships_P2.remove(ships_P2.size()-1);
						btnUndo.setEnabled(false);
						break;
					case "CL":
						CountCL_P2 += 1;
						if (CountCL_P2 > 3) CountCL_P2 = 3;
						ships.remove(ships_P2.size()-1);
						btnUndo.setEnabled(false);
						break;
					case "DD":
						CountDD_P2 += 1;
						if (CountDD_P2 > 4) CountDD_P2 = 4;
						ships.remove(ships_P2.size()-1);
						btnUndo.setEnabled(false);
						break;
					} 
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
					if (gameStart == false) {
						int shipID = Cursor.whatShip(Cursor.cursorX, Cursor.cursorY); 	//vilken skepp har man valt från ShipBuilder?
						int shipID_P2 = Cursor.whatShip(Cursor.cursorX_P2, Cursor.cursorY_P2);
						if (mouseOccupied == false) {
							if (PlayerID == 1) {
								mouseOccupied = true;						//Musen är upptagen, dvs att man ska sätta ut grejer
								ghostActive = true;
								switch (shipID) {
								case 1:
									if (CountCV != 0) {  				//Om det finns någon skepp kvar att sätta ut
										ghostCV = true;					//set ghostXX till true för att rita ut den senare i panel koden		
										GhostW = ShipBuilder.L_CV;		//GhostW och GhostH är dåvarande Ghosts bredd och höjd i px
										GhostH = ShipBuilder.ShipW;
									}
									break;
								case 2:
									if (CountBB != 0) {
										ghostBB = true;
										GhostW = ShipBuilder.L_BB;
										GhostH = ShipBuilder.ShipW;
									}
									break;
								case 3:
									if (CountCL != 0) {
										ghostCL = true;
										GhostW = ShipBuilder.L_CL;
										GhostH = ShipBuilder.ShipW;
									}
									break;
								case 4:
									if (CountDD != 0) {
										ghostDD = true;
										GhostW = ShipBuilder.L_DD;
										GhostH = ShipBuilder.ShipW;
									}
									break;
								}
							} else if (PlayerID == 3) {
								mouseOccupied = true;						//Musen är upptagen, dvs att man ska sätta ut grejer
								ghostActive = true;
								switch (shipID_P2) {
								case 1:
									if (CountCV_P2 != 0) {  				//Om det finns någon skepp kvar att sätta ut
										ghostCV = true;						//set ghostXX till true för att rita ut den senare i panel koden		
										GhostW = ShipBuilder.L_CV;			//GhostW och GhostH är dåvarande Ghosts bredd och höjd i px
										GhostH = ShipBuilder.ShipW;
									}
									break;
								case 2:
									if (CountBB_P2 != 0) {
										ghostBB = true;
										GhostW = ShipBuilder.L_BB;
										GhostH = ShipBuilder.ShipW;
									}
									break;
								case 3:
									if (CountCL_P2 != 0) {
										ghostCL = true;
										GhostW = ShipBuilder.L_CL;
										GhostH = ShipBuilder.ShipW;
									}
									break;
								case 4:
									if (CountDD_P2 != 0) {
										ghostDD = true;
										GhostW = ShipBuilder.L_DD;
										GhostH = ShipBuilder.ShipW;
									}
									break;
								}
							}
							System.out.println("DEBUG: ENTER");
							System.out.println("DEBUG: shipID = " + shipID);
						} else if (mouseOccupied == true){
							if (PlayerID == 1) {	
								if (Ship.checkOverlap() == false) {
									mouseOccupied = false;
									ghostActive = false;
									btnUndo.setEnabled(true);
									if (ghostCV == true) {	
										ghostCV = false;
										Ship CV = new Ship(Cursor.cursorX, Cursor.cursorY, GhostW, GhostH);
										ships.add(CV);
										CountCV -= 1;
										LastShip = "CV";
										mouseOccupied = false;
										ghostActive = false;
									} else if (ghostBB == true) {
										ghostBB = false;
										Ship BB = new Ship(Cursor.cursorX, Cursor.cursorY, GhostW, GhostH);
										ships.add(BB);
										CountBB -= 1;
										LastShip = "BB";
										mouseOccupied = false;
										ghostActive = false;
									} else if (ghostCL == true) {
										ghostCL = false;
										Ship CL = new Ship(Cursor.cursorX, Cursor.cursorY, GhostW, GhostH);
										ships.add(CL);
										CountCL -= 1;
										LastShip = "CL";
										mouseOccupied = false;
										ghostActive = false;
									} else if (ghostDD == true) {
										ghostDD = false;
										Ship DD = new Ship(Cursor.cursorX, Cursor.cursorY, GhostW, GhostH);
										ships.add(DD);
										CountDD -= 1;
										LastShip = "DD";
										mouseOccupied = false;
										ghostActive = false;
									}
								} else {
									System.out.println("DEBUG: Placement fail");
								}
							} else if (PlayerID == 3) {
								if (Ship.checkOverlap_P2() == false) {
									mouseOccupied = false;
									ghostActive = false;
									btnUndo.setEnabled(true);
									if (ghostCV == true) {	
										ghostCV = false;
										Ship CV = new Ship(Cursor.cursorX_P2, Cursor.cursorY_P2, GhostW, GhostH);
										ships_P2.add(CV);
										CountCV_P2 -= 1;
										LastShip = "CV";
									} else if (ghostBB == true) {
										ghostBB = false;
										Ship BB = new Ship(Cursor.cursorX_P2, Cursor.cursorY_P2, GhostW, GhostH);
										ships_P2.add(BB);
										CountBB_P2 -= 1;
										LastShip = "BB";
									} else if (ghostCL == true) {
										ghostCL = false;
										Ship CL = new Ship(Cursor.cursorX_P2, Cursor.cursorY_P2, GhostW, GhostH);
										ships_P2.add(CL);
										CountCL_P2 -= 1;
										LastShip = "CL";
									} else if (ghostDD == true) {
										ghostDD = false;
										Ship DD = new Ship(Cursor.cursorX_P2, Cursor.cursorY_P2, GhostW, GhostH);
										ships_P2.add(DD);
										CountDD_P2 -= 1;
										LastShip = "DD";
									}
								} else {
									System.out.println("DEBUG: Placement fail");
								}
							}
							//Debug
							System.out.println("DEBUG: ENTER");
							System.out.println("DEBUG: shipID = " + shipID);
						}
					} else {
						if (turnDone == false) {
							btnAdvanceState.setEnabled(false);
							switch (HitMiss.HitOrMiss()) {
								case 0:
									break;
								case 1:
									if (PlayerID == 1) {
										HitMiss miss = new HitMiss(Cursor.cursorX, Cursor.cursorY, false);
										markers.add(miss);
									}
									if (PlayerID == 3) {
										HitMiss miss = new HitMiss(Cursor.cursorX_P2, Cursor.cursorY_P2, false);
										markers_P2.add(miss);
									}
									turnDone = true;
									btnAdvanceState.setEnabled(true);
									
									break;
								case 2:
									if (PlayerID == 1) {
										HitMiss hit = new HitMiss(Cursor.cursorX, Cursor.cursorY, true);
										markers.add(hit);
									}
									if (PlayerID == 3) {
										HitMiss hit = new HitMiss(Cursor.cursorX_P2, Cursor.cursorY_P2, true);
										markers_P2.add(hit);
									}
									turnDone = false;
									btnAdvanceState.setEnabled(false);
									break;
							}
						}
					}
					panel_Game.repaint();
				}
			}
		});
		
		//KeyListener för knappen R - roterar ghosts
		panel_Game.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {				//Flippa runt värde på GhostW och GhostH 
				if(e.getKeyCode() == KeyEvent.VK_R) {
					//Flip H and W to rotate the ghost ship
					GhostTemp = GhostW;
					GhostW = GhostH;
					GhostH = GhostTemp;
					
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
		
		JLabel lblInfoP1 = new JLabel("Info P1: Start the game");
		lblInfoP1.setBounds(141, 11, 193, 16);
		frmBattleship.getContentPane().add(lblInfoP1);
		
		JLabel lblInfoP2 = new JLabel("Info P2: Start the game");
		lblInfoP2.setBounds(666, 11, 178, 16);
		frmBattleship.getContentPane().add(lblInfoP2);
		
		btnAdvanceState.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/* PlayerID Index:
				 * 1:P1
				 * 2:Switch Period
				 * 3:P2
				 * 4:SwitchPeriod
				 */
				PlayerID += 1;
				Turn += 0.5;
				System.out.println("DEBUG: PlayerID = " + PlayerID);
				switch (PlayerID) {
					case 1:
						blocks.clear();
						//Block B1 = new Block(606, 0, 380, 380);
						//blocks.add(B1);
						btnAdvanceState.setText("Next Turn");
						lblInfoP1.setText("Info P1: Your Turn");
						lblInfoP2.setText("Info P2: P1 Turn");
						btnAdvanceState.setEnabled(false);
						btnClearBoardP2.setEnabled(false);
						break;
					case 2:
						Block B2 = new Block(0, 0, 380, 380);
						blocks.add(B2);
						Block B1 = new Block(606, 0, 380, 380);
						blocks.add(B1);
						btnAdvanceState.setText("Next Turn");
						lblInfoP1.setText("Info P1: TURN THE SCREEN");
						lblInfoP2.setText("Info P2: TURN THE SCREEN");
						btnUndo.setEnabled(false);
						btnClearBoardP1.setEnabled(false);
						btnClearBoardP2.setEnabled(true);
						
						break;
					case 3: 
						blocks.clear();
						//Block B3 = new Block(0, 0, 380, 380);
						//blocks.add(B3);
						btnAdvanceState.setText("Next Turn");
						lblInfoP2.setText("Info P2: Your Turn");
						lblInfoP1.setText("Info P1: P2 Turn");
						btnAdvanceState.setEnabled(false);
						break;
					case 4:
						Block B4 = new Block(606, 0, 380, 380);
						blocks.add(B4);
						Block B3 = new Block(0, 0, 380, 380);
						blocks.add(B3);
						PlayerID = 0;
						btnAdvanceState.setText("Next Turn");
						lblInfoP1.setText("Info P1: TURN THE SCREEN");
						lblInfoP2.setText("Info P2: TURN THE SCREEN");
						btnUndo.setEnabled(false);
						break;	
				}
				turnDone = false;
				panel_Game.repaint();
			}
		});
		btnAdvanceState.setBounds(331, 6, 117, 29);
		frmBattleship.getContentPane().add(btnAdvanceState);
		
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
		
		btnClearBoardP2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ships_P2.clear();			//ta bort alla skeppar från grid
				CountCV_P2 = 1;
				CountBB_P2 = 2;
				CountCL_P2 = 3;
				CountDD_P2 = 4;
				panel_Game.repaint();
			}
		});
		btnClearBoardP2.setBounds(843, 6, 151, 29);
		frmBattleship.getContentPane().add(btnClearBoardP2);
		
		btnRegainFocus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		//Knapp för att fokusera på panelen för att kunna flytta cursor.
				panel_Game.requestFocusInWindow();
			}
		});
	}
}
