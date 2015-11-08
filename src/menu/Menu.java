package menu;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * This class generates the general window of the microgames program.
 * 
 * @since 0.1
 * @version 0.1
 * 
 * @see JFrame
 * 
 * @author stefanboodt
 *
 */
public class Menu extends JFrame {

	/**
	 * Serial number.
	 */
	private static final long serialVersionUID = -5685652752362930028L;
	
	/**
	 * The default width.
	 */
	private static final int DEFAULT_WIDTH = 1000;
	
	/**
	 * The default height.
	 */
	private static final int DEFAULT_HEIGHT = 600;
	
	/**
	 * Creates a new menu for the game.
	 */
	public Menu() {
		this.setVisible(true);
		this.setTitle("MicroGames");
		this.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		this.getContentPane().setBackground(Color.BLACK);
		this.pack();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new Menu();
	}

}
