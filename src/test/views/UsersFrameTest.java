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

	@Test
	public void testDefaultComponents() throws Exception {
		
		// Application title = Personal Budget Manager
		assertSame("Personal Budget Manager", usersFrame.getTitle());

		// mainPanel exists
		assertTrue(((JPanel) getField(usersFrame, "mainPanel")).isVisible());
		
		// loginPanel exists
		assertNotNull(usersFrame.getLoginPanel());
		
		// signupPanel exists
		assertNotNull(usersFrame.getSignupPanel());
		
		// CardLayout exists and shows login as default	
		assertNotNull(usersFrame.getLayout());
		assertTrue(((UsersLoginPanel) getField(usersFrame, "loginPanel")).isShowing());
		assertFalse(((UsersSignupPanel) getField(usersFrame, "signupPanel")).isShowing());
	}
	
	@Test
	public void testGetLayout() {
		
		// CardLayout exist
		CardLayout cl = new CardLayout();
		assertSame(cl.getClass(), usersFrame.getLayout().getClass());
	}
	
	@Test
	public void testGetLoginPanel() {
		
		// JPanel, loginPanel, exist
		UsersLoginPanel lp = new UsersLoginPanel();
		assertSame(lp.getClass(), usersFrame.getLoginPanel().getClass());
	}
	
	@Test
	public void testGetSignupPanel() {
		
		// JPanel, signupPanel, exist
		UsersSignupPanel sp = new UsersSignupPanel();
		assertSame(sp.getClass(), usersFrame.getSignupPanel().getClass());
	}
	
	@Test
	public void testShowSignupPanel() throws Exception {
		
		usersFrame.showSignupPanel();
		
		// signupPanel must be visible
		assertTrue(((UsersSignupPanel) getField(usersFrame, "signupPanel")).isShowing());
		
		// loginPanel cannot be visible
		assertFalse(((UsersLoginPanel) getField(usersFrame, "loginPanel")).isShowing());
	}
	
	@Test
	public void testShowLoginPanel() throws Exception {
		
		usersFrame.showLoginPanel();
		
		// loginPanel must be visible
		assertTrue(((UsersLoginPanel) getField(usersFrame, "loginPanel")).isShowing());
		
		// signupPanel cannot be visible
		assertFalse(((UsersSignupPanel) getField(usersFrame, "signupPanel")).isShowing());
	}
	
} // EOF
