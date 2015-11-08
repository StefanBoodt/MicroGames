package menu;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MenuTest {
	
	/**
	 * The Menu under test.
	 */
	private Menu menu;

	/**
	 * Does some set up.
	 * @throws Exception If the set up fails.
	 */
	@Before
	public void setUp() throws Exception {
		setMenu(new Menu());
	}
	
	/**
	 * Sets the menu under test.
	 * @param menu The menu under test.
	 */
	protected void setMenu(Menu menu) {
		this.menu = menu;
	}

	/**
	 * Tests if the menu is visible.
	 */
	@Test
	public void testVisible() {
		assertEquals(true, menu.isVisible());
	}

}
