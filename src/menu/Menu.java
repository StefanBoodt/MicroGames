package menu;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JComponent;
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
		this.pack();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setMainMenu();
	}
	
	/**
	 * Sets the main menu of the program.
	 */
	public void setMainMenu() {
		this.removeAll();
		this.getContentPane().setBackground(Color.BLACK);
	}
	
	/**
	 * Sets the component to be displayed.
	 * @param main The component that has to be displayed next.
	 */
	public void setComponent(JComponent main) {
		this.removeAll();
		this.add(main);
	}
	
	/**
	 * Starts the program.
	 * @param args Ignored.
	 */
	public static void main(String[] args) {
		new Menu();
	}
}
