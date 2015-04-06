package test.integration;


import test.helpers.*;
import views.*;
import controllers.*;
import models.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class LoginUserTest extends TestHelper{
	
	private UsersFrame usersFrame;
	private UsersController usersController;
	private User_BLL usersBLL;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		usersFrame = new UsersFrame();
		usersController = new UsersController(usersFrame);
		usersBLL = new User_BLL();
	}

	/**
	 * Test case: Login User
	 * @throws Exception 
	 */
	@Test
	public void loginUser() throws Exception {
		
		//the model should login a user name and password after signup.
		usersController.login("testName", "testPWD");
		boolean result = usersBLL.validatePin("testName", "testPWD");
		assertTrue(result);
		
		
		//the view should dispose usersFrame
		usersFrame = (UsersFrame) getField(usersController, "usersFrame");
		assertFalse(usersFrame.isShowing());
		
	}

}
