package jurisHomickis;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Battleship {
	
	boolean yeet = false;

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
		frmBattleship.setBounds(100, 100, 1000, 414);
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
				
				//Debug
				ShipBuilder.DrawModels(gx);
				
				ShipBuilder.DrawCV(gx);
				repaint();
				
			}
			
		};
		panel_Game.setBounds(6, 6, 988, 381);
		frmBattleship.getContentPane().add(panel_Game);
		panel_Game.setLayout(null);
		
		JButton btnStart = new JButton("Start");
		btnStart.setBounds(439, 6, 113, 29);
		panel_Game.add(btnStart);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(439, 38, 113, 29);
		panel_Game.add(btnClear);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(439, 68, 113, 29);
		panel_Game.add(btnExit);
	}
}
