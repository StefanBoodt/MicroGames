package gtn;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the GTNCore class.
 * 
 * @since 0.1
 * @version 0.1
 * 
 * @see GTNCore
 * 
 * @author stefanboodt
 *
 */
public class GTNCoreTest {
	
	/**
	 * The GTNCore under test.
	 */
	private GTNCore gtn;

	/**
	 * Does some set up.
	 * @throws Exception If the set up fails.
	 */
	@Before
	public void setUp() throws Exception {
		setGTNCore(new GTNCore());
	}
	
	/**
	 * Sets the GTNCore to the given core.
	 * @param gtn The new GTNCore.
	 */
	protected void setGTNCore(GTNCore gtn) {
		this.gtn = gtn;
	}
	
	/**
	 * Returns a mock for true retries.
	 * @return A GTNComponent that wants to continue.
	 */
	protected GTNComponent getTrueRetryMock() {
		GTNComponent trueRetry = mock(GTNComponent.class);
		when(trueRetry.askForRetry()).thenReturn(true);
		return trueRetry;
	}
	
	/**
	 * Returns a mock for False retries.
	 * @return A GTNComponent that wants to continue.
	 */
	protected GTNComponent getFalseRetryMock() {
		GTNComponent trueRetry = mock(GTNComponent.class);
		when(trueRetry.askForRetry()).thenReturn(false);
		return trueRetry;
	}
	
	/**
	 * Tests the initial value of the number of guesses.
	 */
	@Test
	public void testGetNumberOfGuesses() {
		assertEquals(0, gtn.getNumberOfGuesses());
	}
	
	/**
	 * Tests the initial value of the number of guesses after
	 * guessing and resetting.
	 */
	@Test
	public void testGetNumberOfGuessesReset() {
		gtn.setGUI(getTrueRetryMock());
		gtn.guess(-1);
		gtn.guess(-102);
		gtn.guess(gtn.getNumber());
		assertEquals(0, gtn.getNumberOfGuesses());
	}

	/**
	 * Test the immidiatly correct guess number of guesses.
	 */
	@Test
	public void testCorrectGuess() {
		gtn.setGUI(getFalseRetryMock());
		gtn.guess(gtn.getNumber());
		assertEquals(1, gtn.getNumberOfGuesses());
	}
	
	/**
	 * Tests the correct reply on a to high guess.
	 */
	@Test
	public void testCorrectMessageSentHigher() {
		GTNComponent mock = getFalseRetryMock();
		gtn.setGUI(mock);
		gtn.guess(-1);
		verify(mock).post("higher");
	}
	
	/**
	 * Tests the correct reply on a to high guess.
	 */
	@Test
	public void testCorrectMessageSentLower() {
		GTNComponent mock = getFalseRetryMock();
		gtn.setGUI(mock);
		gtn.guess(Integer.MAX_VALUE);
		verify(mock).post("lower");
	}
	
	/**
	 * Tests input handling of {@link Integer.MAX_VALUE} string
	 * version.
	 */
	@Test
	public void testHandleInput() {
		GTNComponent mock = getFalseRetryMock();
		gtn.setGUI(mock);
		gtn.handleInput("" + Integer.MAX_VALUE);
		verify(mock).post("lower");
	}
	
	/**
	 * Tests input handling of {@link Integer.MAX_VALUE} string
	 * version.
	 */
	@Test
	public void testHandleInputString() {
		GTNComponent mock = getFalseRetryMock();
		gtn.setGUI(mock);
		gtn.handleInput("Hello");
		verify(mock).post(GTNCore.WRONG_INPUT_MESSAGE);
	}
	
	/**
	 * Tests input handling of {@link Integer.MAX_VALUE} string
	 * version.
	 */
	@Test
	public void testHandleInputNegativeNumber() {
		GTNComponent mock = getFalseRetryMock();
		gtn.setGUI(mock);
		gtn.handleInput("-1");
		verify(mock).post("higher");
	}
	
	/**
	 * Tests input handling of {@link Integer.MAX_VALUE} string
	 * version.
	 */
	@Test
	public void testHandleInputLargeNegativeNumber() {
		GTNComponent mock = getFalseRetryMock();
		gtn.setGUI(mock);
		gtn.handleInput(Integer.MIN_VALUE + "0");
		verify(mock).post(GTNCore.WRONG_INPUT_MESSAGE);
	}
	
	/**
	 * Tests input handling of {@link Integer.MAX_VALUE} string
	 * version.
	 */
	@Test
	public void testHandleInputLargeNegativeNumber2() {
		GTNComponent mock = getFalseRetryMock();
		gtn.setGUI(mock);
		final String num = Integer.MIN_VALUE + "0";
		gtn.handleInput(num);
		verify(mock, times(2)).post(anyString());
	}
	
}
