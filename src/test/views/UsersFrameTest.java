package test.views;

import static org.junit.Assert.*;
import java.awt.CardLayout;
import javax.swing.JPanel;

import org.junit.*;

import views.*;

public class UsersFrameTest extends ViewsTestHelper {
	
	UsersFrame usersFrame;
	
	@Before
	public void setUp(){
		usersFrame = new UsersFrame();
	}

	/**
	 * Test the default components of UsersFrame class.
	 * UsersFrame must contain a title, a mainPanel,
	 * a loginPanel, a signupPanel and a CardLyout.
	 * The loginPanel must appear by default on start.
	 * @throws Exception
	 */
	@Test
	public void testDefaultComponents() throws Exception {
		
		// Application title equal to "Personal Budget Manager"
		assertSame("Personal Budget Manager", usersFrame.getTitle());

		// mainPanel exists
		assertTrue(((JPanel) getField(usersFrame, "mainPanel")).isVisible());
		
		// loginPanel exists
		assertNotNull(usersFrame.getLoginPanel());
		
		// signupPanel exists
		assertNotNull(usersFrame.getSignupPanel());
		
		// CardLayout exists and shows loginPanel by default	
		assertNotNull(usersFrame.getLayout());
		assertTrue(((UsersLoginPanel) getField(usersFrame, "loginPanel")).isShowing());
		assertFalse(((UsersSignupPanel) getField(usersFrame, "signupPanel")).isShowing());
	}
	
	/**
	 * Test the getLayout() method of UsersFrame class.
	 * The method must return a CardLayout object.
	 */
	@Test
	public void testGetLayout() {
		
		CardLayout cl = new CardLayout();
		assertSame(cl.getClass(), usersFrame.getLayout().getClass());
	}
	
	/**
	 * Test the getLoginPanel() method of UsersFrame class.
	 * The method must return a UsersLoginPanel object.
	 */
	@Test
	public void testGetLoginPanel() {
		
		UsersLoginPanel lp = new UsersLoginPanel();
		assertSame(lp.getClass(), usersFrame.getLoginPanel().getClass());
	}
	
	/**
	 * Test the getSignupPanel() method of UsersFrame class.
	 * The method must return a UsersSignupPanel object.
	 */
	@Test
	public void testGetSignupPanel() {
		
		UsersSignupPanel sp = new UsersSignupPanel();
		assertSame(sp.getClass(), usersFrame.getSignupPanel().getClass());
	}
	
	/**
	 * Test the showSignupPanel() method of UsersFrame class.
	 * The method must show the signupPanel and hide the
	 * loginPanel.
	 * @throws Exception
	 */
	@Test
	public void testShowSignupPanel() throws Exception {
		
		usersFrame.showSignupPanel();
		
		// signupPanel must be visible
		assertTrue(((UsersSignupPanel) getField(usersFrame, "signupPanel")).isShowing());
		
		// loginPanel cannot be visible
		assertFalse(((UsersLoginPanel) getField(usersFrame, "loginPanel")).isShowing());
	}
	
	/**
	 * Test the showLoginPanel() method of UsersFrame class.
	 * The method must show the loginPanel and hide the
	 * signupPanel.
	 * @throws Exception
	 */
	@Test
	public void testShowLoginPanel() throws Exception {
		
		usersFrame.showLoginPanel();
		
		// loginPanel must be visible
		assertTrue(((UsersLoginPanel) getField(usersFrame, "loginPanel")).isShowing());
		
		// signupPanel cannot be visible
		assertFalse(((UsersSignupPanel) getField(usersFrame, "signupPanel")).isShowing());
	}
	
} // EOF
