package test.views;

import static org.junit.Assert.*;

import org.junit.Test;

import views.*;

public class TestUsersFrame {
	
	UsersFrame usersFrame = new UsersFrame();

	@Test
	public void testUsersFrame() {
		
		assertSame("Personal Budget Manager", usersFrame.getTitle());

		// TODO: mainPanel exist
		// TODO: loginPanel exist
		// TODO: signupPanel exist
		// TODO: cLayout show login as default	
	}
	
	public void testGetLayout() {
		
		// TODO: assert return of cLayout
	}
	
	public void testGetLoginPanel() {
		
		// TODO: assert return of loginPanel
	}
	
	public void testGetSignupPanel() {
		
		// TODO: assert return of signupPanel
	}
	
	public void testShowSignupPanel() {
		
		// TODO: cLayout to show signupPanel
	}
	
	public void testShowLoginPanel() {
		
		// TODO: cLayout to show loginPanel
	}
	
} // EOF
