package test.controllers;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

import views.*;
import controllers.UsersController;

/*import javax.swing.JPasswordField;
import javax.swing.JTextField;*/


public class UserControllerTest {
	
	private UsersController users;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		UsersFrame usersFrame = new UsersFrame();
		users = new UsersController(usersFrame);
	}

	
	/**
	 * should display signup panel
	 * should hide login panel
	 */
	@Test
	public void testShowSignup() throws Exception {

		users.showSignup();
		assertTrue(((UsersSignupPanel) getField(users, "signupPanel")).isShowing());
		assertFalse(((UsersLoginPanel) getField(users, "loginPanel")).isShowing());
		
	}
	
	/**
	 * should display login panel
	 * should hide signup panel
	 */
	@Test
	public void testCancelSignup() throws Exception {

		users.cancelSignup();
		assertFalse(((UsersSignupPanel) getField(users, "signupPanel")).isShowing());
		assertTrue(((UsersLoginPanel) getField(users, "loginPanel")).isShowing());
		
	}
	
	/**
	 * should not signup
	 */
	@Test
	public void testSignupFail() throws Exception {
		
		users.showSignup();
		users.signup("", "", "");
		assertFalse(((UsersLoginPanel) getField(users, "loginPanel")).isShowing());
		
		users.showSignup();
		users.signup("username", "pass", "passssss");
		assertFalse(((UsersLoginPanel) getField(users, "loginPanel")).isShowing());

		/*UsersSignupPanel signup = (UsersSignupPanel) getField(users, "signupPanel");
		((JTextField) getField(signup, "txtFieldName")).setText("username");
		((JPasswordField) getField(signup, "pwdFieldPwd")).setText("pass");
		((JPasswordField) getField(signup, "pwdFieldPwdConfirm")).setText("pass");*/
		//users.signup();
		
	}
	
	/**
	 * should signup
	 */
	@Test
	public void testSignupSuccess() throws Exception {
		
		users.showSignup();
		//users.signup("username", "pass", "pass");
		//assertTrue(((UsersLoginPanel) getField(users, "loginPanel")).isShowing());
		
	}
	
	/**
	 * should not login
	 */
	@Test
	public void testLoginFail() throws Exception {
		
		users.login("", "");
		assertTrue(((UsersFrame) getField(users, "usersFrame")).isVisible());
		
		users.login("AppDemo", "wrong password");
		assertTrue(((UsersFrame) getField(users, "usersFrame")).isVisible());
		
	}
	
	/**
	 * should login
	 */
	@Test
	public void testLoginSuccess() throws Exception {
		
		users.login("AppDemo", "appdemo");
		assertFalse(((UsersFrame) getField(users, "usersFrame")).isVisible());
		
	}

	
	/**
  	 * Gets the field value from an instance.  The field we wish to retrieve is
  	 * specified by passing the name.  The value will be returned, even if the
  	 * field would have private or protected access.
  	 */
  	private Object getField( Object instance, String name ) throws Exception
  	{
  		Class c = instance.getClass();

  		// Retrieve the field with the specified name
  		Field f = c.getDeclaredField( name );

  		// *MAGIC* make sure the field is accessible, even if it
  		// would be private or protected
  		f.setAccessible( true );

  		// Return the value of the field for the instance
  		return f.get( instance );
  	}
}
