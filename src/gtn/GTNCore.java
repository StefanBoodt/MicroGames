package gtn;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * This class provides the core behaviour of the guess the number
 * game.
 * 
 * @since 0.1
 * @version 0.1
 * 
 * @see Random
 * 
 * @author stefanboodt
 *
 */
public class GTNCore {
	
	/**
	 * Maximum number.
	 */
	private static final int MAX_NUMBER = 100000;
	
	/**
	 * The error message when an invalid input is inserted.
	 */
	public static final String WRONG_INPUT_MESSAGE = 
			"Only numbers are allowed. The requested number is"
			+ " between 0 and " + MAX_NUMBER;
	
	/**
	 * The random number generator.
	 */
	private Random random;
	
	/**
	 * The number that is the current value.
	 */
	private int number;
	
	/**
	 * The number of guesses.
	 */
	private int guesses;
	
	/**
	 * The GUI used.
	 */
	private GTNComponent gui;

	/**
	 * Generates a new Guess The Number Core Instance.
	 * @param translator the translator used.
	 */
	public GTNCore() {
		random = new Random();
		getNextNumber();
	}
	
	/**
	 * Sets the GUI to post to.
	 * @param gui The gui to post to.
	 */
	public void setGUI(GTNComponent gui) {
		this.gui = gui;
	}
	
	/**
	 * Sets the next number.
	 */
	protected void getNextNumber() {
		number = random.nextInt(MAX_NUMBER);
		guesses = 0;
	}
	
	/**
	 * Code to be executed when a guess is made.
	 * @param guessed The guess made.
	 */
	public void guess(int guessed) {
		guesses++;
		if (guessed == number) {
			post("Congrats you guessed it in "
				+ getNumberOfGuesses() + " times");
			retry();
		} else if (guessed < number) {
			post("higher");
		} else {
			post("lower");
		}
	}
	
	/**
	 * Announces the message to the player.
	 * @param message The message that needs announcing.
	 */
	public void post(String message) {
		if (gui == null) {
			System.out.println(message);
		} else {
			gui.post(message);
		}
	}
	
	/**
	 * Asks if a retry is preffered or if the game should stop.
	 */
	protected void retry() {
		final boolean retry = gui.askForRetry();
		if (retry) {
			getNextNumber();
		} else {
			gui.exit();
		}
	}
	
	/**
	 * Returns the secret number that should be guessed.
	 * @return The number that should be guessed.
	 */
	protected int getNumber() {
		return number;
	}
	
	/**
	 * Returns the number of guesses that has already passed.
	 * @return The number of guesses.
	 */
	protected int getNumberOfGuesses() {
		return guesses;
	}
	
	/**
	 * Parses the message that results in a guess.
	 * @param message The message to parse.
	 */
	protected void handleInput(String message) {
		if (message.matches("-?\\d+")) {
			try {
				guess(Integer.parseInt(message));
			} catch (NumberFormatException e) {
				post(WRONG_INPUT_MESSAGE);
				post("The number " + message + " was not parsable");
			}
		} else {
			post(WRONG_INPUT_MESSAGE);
		}
	}
}
