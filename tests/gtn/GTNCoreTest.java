package gtn;

import static org.junit.Assert.assertEquals;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
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
		GTNCore mocked = mock(GTNCore.class);
		when(mocked.askForRetry()).thenReturn(true);
		when(mocked.getNumber()).thenCallRealMethod();
		when(mocked.getNumberOfGuesses()).thenCallRealMethod();
		doCallRealMethod().when(mocked).exit();
		doCallRealMethod().when(mocked).getNextNumber();
		mocked.guess(-1);
		mocked.guess(-102);
		mocked.guess(mocked.getNumber());
		assertEquals(0, mocked.getNumberOfGuesses());
	}

	/**
	 * Test the immidiatly correct guess number of guesses.
	 */
	@Test
	public void testCorrectGuess() {
		GTNCore mocked = mock(GTNCore.class);
		// Still seems to return true, as value is reset.
		when(mocked.askForRetry()).thenReturn(false);
		when(mocked.getNumber()).thenCallRealMethod();
		when(mocked.getNumberOfGuesses()).thenCallRealMethod();
		doCallRealMethod().when(mocked).post(anyString());
		doCallRealMethod().when(mocked).exit();
		doCallRealMethod().when(mocked).getNextNumber();
		mocked.guess(mocked.getNumber());
		assertEquals(1, mocked.getNumberOfGuesses());
	}

}
