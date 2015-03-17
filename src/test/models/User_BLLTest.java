
package test.models;

import static org.junit.Assert.*;
import models.User_BLL;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 *
 */
public class User_BLLTest {
	
	private User_BLL userBLL;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		userBLL = new User_BLL();
	}

	@Test
	public void testInsertNewUser() throws Exception {

		boolean result = userBLL.createUser("testUsername", "pass");
		assertTrue(result);
	}

	@Test
	public void testIsUserExist() throws Exception {
		
		boolean Actual_result = userBLL.isUserExist("AppDemo");
		assertTrue(Actual_result);
	}

	@Test
	public void testIsUsernameMatchPassword() throws Exception {
		
		boolean Actual_result = userBLL.validatePin("AppDemo", "appdemo");
		assertTrue(Actual_result);
	
	}
	

}
