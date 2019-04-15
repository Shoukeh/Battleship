package jurisHomickis;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;

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
		frmBattleship.setIconImage(Toolkit.getDefaultToolkit().getImage("/Users/jurishomickis/Documents/memes/PFP.png"));
		frmBattleship.setTitle("BATTLESHIP");
		frmBattleship.setBounds(100, 100, 450, 300);
		frmBattleship.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
