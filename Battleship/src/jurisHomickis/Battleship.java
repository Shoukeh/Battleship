package jurisHomickis;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Battleship {

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
				Grid.WH = 10;										//Sätta WidthHeigth variablen i Grid klassen till 10
				Grid.DrawGrid(gx); 									//Rita en Grid på första spelarens panel
			}
			
		};
		panel_Game.setBounds(6, 6, 381, 381);
		frmBattleship.getContentPane().add(panel_Game);
		
		JPanel panel_Game2 = new JPanel() {
			public void paint(Graphics gx) { 
				Grid.WH = 10; 										//Sätta WidthHeigth variablen i Grid klassen till 10
				Grid.DrawGrid(gx); 									//Rita en Grid på andra spelarens panel
			}
		};
		panel_Game2.setBounds(612, 6, 381, 381);
		frmBattleship.getContentPane().add(panel_Game2);
	}
}
