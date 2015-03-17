package test.models;

import static org.junit.Assert.*;

import java.security.MessageDigest;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import models.User_Service;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 *
 */
public class User_ServiceTest {
	
	private User_Service usr;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		usr = new User_Service();
	}

	@Test
	public void testInsertNewUser() throws Exception {

		boolean result = usr.insertNewUser("testuser", HashToSHA256("abcd"));
		assertTrue(result);
	}

	@Test
	public void testIsUserExist() throws Exception {
		
		boolean Actual_result = usr.isUserExist("testuser");
		
		assertTrue(Actual_result);
	}

	@Test
	public void testIsUsernameMatchPassword() throws Exception {
		
		boolean Actual_result = usr.isUsernameMatchPassword("testuser", HashToSHA256("abcd"));
		assertTrue(Actual_result);
	
	}
	
	@Test
	public void testGetUserIdByUsername() throws Exception {

		int Expected_Id = 1;
		int Actual_Id = usr.getUserIdByUsername("AppDemo");
		assertEquals(Expected_Id,Actual_Id);
		
	}
	
	public String HashToSHA256(String password) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		String hex = (new HexBinaryAdapter()).marshal(md.digest(password.getBytes()));
		return hex;
	}

}
