package menu;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gtn.GTNComponent;

/**
 * This class generates the general window of the microgames program.
 * 
 * @since 0.1
 * @version 0.1
 * 
 * @see JFrame
 * @see ActionListener
 * 
 * @author stefanboodt
 *
 */
public class Menu extends JFrame implements ActionListener {

	/**
	 * Serial number.
	 */
	private static final long serialVersionUID = -5685652752362930028L;
	
	/**
	 * The default width.
	 */
	public static final int DEFAULT_WIDTH = 1000;
	
	/**
	 * The default height.
	 */
	public static final int DEFAULT_HEIGHT = 600;
	
	/**
	 * The menu used to direct the people and start the app.
	 */
	private final JComponent menu;
	
	/**
	 * The cards to flip with the different layouts.
	 */
	private JPanel cards;
	
	/**
	 * The ID for this class.
	 */
	public static final String ID = "Main Menu";
	
	/**
	 * Creates a new menu for the game.
	 */
	public Menu() {
		cards = new JPanel(new CardLayout());
		menu = new JPanel();
		this.setTitle("MicroGames");
		this.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
		this.pack();
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		createMainMenu();
		this.add(cards);
		this.setVisible(true);
	}
	
	/**
	 * Creates the main menu.
	 */
	private void createMainMenu() {
		menu.setBackground(Color.BLACK);
		cards.add(menu, ID);
		addCard("Guess the number", new GTNComponent(this),
				GTNComponent.ID);
		setMainMenu();
	}
	
	/**
	 * Sets the main menu of the program. Equivalent to
	 * {@link setComponent(String)} with {@link ID} as
	 * value.
	 */
	public void setMainMenu() {
		
		setComponent(ID);
	}
	
	/**
	 * Adds Component comp under name id to a button with text
	 * buttontext.
	 * @param buttontext The text on the button.
	 * @param comp The component to add.
	 * @param id The Id of the component.
	 */
	public void addCard(String buttontext, JComponent comp,
			String id) {
		JButton button = new JButton(buttontext);
		button.setActionCommand(id);
		button.setSize(DEFAULT_WIDTH/6, DEFAULT_HEIGHT/4);
		button.addActionListener(this);
		menu.add(button);
		cards.add(comp, id);
	}
	
	/**
	 * Sets the component to be displayed.
	 * @param id The id used to add the component under.
	 */
	public void setComponent(String id) {
		if (cards.getLayout() instanceof CardLayout) {
			CardLayout cardlayout = (CardLayout) cards.getLayout();
			cardlayout.show(cards, id);
			System.out.println("Now displaying: " + id);
			System.out.println("CardLayout = " + cardlayout.toString());
		} else {
			JOptionPane.showMessageDialog(null, "Internal error:"
					+ " card layout requirement is violated.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Starts the program.
	 * @param args Ignored.
	 */
	public static void main(String[] args) {
		new Menu();
	}

	/**
	 * {@inheritDoc}
	 * This class only checks if it is possible to load a program
	 * with the given id.
	 * @param e The ActionEvent fired.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		setComponent(e.getActionCommand());
	}
}
