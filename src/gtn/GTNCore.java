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
	 * The random number generator.
	 */
	private Random random;
	
	/**
	 * Maximum number.
	 */
	private static final int MAX_NUMBER = 100000;
	
	/**
	 * The number that is the current value.
	 */
	private int number;
	
	/**
	 * The number of guesses.
	 */
	private int guesses;
	
	/**
	 * Keeps track of whether or not to continue.
	 */
	private boolean continues;

	/**
	 * Generates a new Guess The Number Core Instance.
	 */
	public GTNCore() {
		random = new Random();
		getNextNumber();
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
		System.out.println(message);
	}
	
	/**
	 * Asks if a retry is preffered or if the game should stop.
	 */
	protected void retry() {
		final boolean retry = askForRetry();
		if (retry) {
			getNextNumber();
			gameloop();
		} else {
			exit();
		}
	}
	
	/**
	 * Asks for the retry.
	 * @return {@code true} If a retry is preferable.
	 */
	boolean askForRetry() {
		return JOptionPane.showConfirmDialog(null,
				"Will you retry?", "Retry", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;
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
	
	public void gameloop() {
		while (continues) {
			handleInput();
		}
	}
	
	/**
	 * Exits the number guessing game.
	 */
	public void exit() {
		continues = false;
	}
	
	protected void handleInput() {
		Scanner sc = new Scanner(System.in);
		try {
			guess(sc.nextInt());
		} catch(InputMismatchException e) {
			post("Only numbers are allowed. The Secret number is"
					+ " between 0 and " + MAX_NUMBER);
		} finally {
			sc.close();
		}
	}
}
