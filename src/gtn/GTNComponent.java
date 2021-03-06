package gtn;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import menu.Menu;

/**
 * This class arranges the GUI for the guess the number game.
 * 
 * @since 0.1
 * @version 0.1
 * 
 * @see Menu
 * @see JComponent
 * 
 * @author stefanboodt
 *
 */
public class GTNComponent extends JPanel implements ActionListener {

	/**
	 * Serial number.
	 */
	private static final long serialVersionUID = 135006579682669859L;
	
	/**
	 * Key to start the GTN game.
	 */
	public static final String ID = "GTN";
	
	/**
	 * The area that records the history.
	 */
	private JTextArea history;
	
	/**
	 * The answerbox.
	 */
	private JTextField answerbox;
	
	/**
	 * The menu to operate on.
	 */
	private Menu menu;
	
	/**
	 * The core to fall back on.
	 */
	private GTNCore core;
	
	/**
	 * Creates a new GTNComponent.
	 * @param menu The menu that started the GTN.
	 */
	public GTNComponent(Menu menu) {
		core = new GTNCore();
		history = new JTextArea();
		answerbox = new JTextField();
		core.setGUI(this);
		answerbox.addActionListener(this);
		history.setEditable(false);
		history.setBackground(Color.WHITE);
		JScrollPane scroller = new JScrollPane(history);
		scroller.setPreferredSize(new 
				Dimension(Menu.DEFAULT_WIDTH,
						(int) (0.9 * Menu.DEFAULT_HEIGHT)));
		answerbox.setPreferredSize(new 
				Dimension(Menu.DEFAULT_WIDTH,
						(int) (0.05 * Menu.DEFAULT_HEIGHT)));
		this.add(scroller);
		this.add(answerbox);
		this.menu = menu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == answerbox) {
			final String answer = answerbox.getText().trim();
			post(answer);
			core.handleInput(answer);
			answerbox.setText("");
		}
	}
	
	/**
	 * Exits the number guessing game.
	 */
	public void exit() {
		menu.setMainMenu();
		history.setText("");
	}
	
	/**
	 * Shows the message to the user.
	 * @param message The message to show.
	 */
	public void post(String message) {
		history.setText(history.getText() + message.trim() + "\n");
	}
	
	/**
	 * Asks for the retry.
	 * @return {@code true} If a retry is preferable.
	 */
	protected boolean askForRetry() {
		return JOptionPane.showConfirmDialog(null,
				"Will you retry?", "Retry", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;
	}

}
