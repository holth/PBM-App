package test.integration;

import test.helpers.TestHelper;
import views.*;
import controllers.*;
import models.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class SignupUserTest extends TestHelper{
	
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
	 * Test case: Signup User
	 * @throws Exception 
	 */
	@Test
	public void signupUser() throws Exception {
		
		usersController.signup("testName", "testPWD", "testPWD");
		
		//The database should store the valid username and password
		boolean result = usersBLL.isUserExist("testName");
		assertTrue(result);
		
		//The UsersFrame window should display usersLoginPanel
		assertTrue(((UsersLoginPanel) getField(usersController, "loginPanel")).isShowing());
		
	}

}
