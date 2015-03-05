package models;

import java.security.MessageDigest;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
public class User_BLL {
	private User_Service user_DAL;
	public User_BLL() throws Exception {
		this.user_DAL = new User_Service();
	}
	public boolean createUser(String username, String password)
			throws Exception {
		if (this.user_DAL.isUserExist(username))
			throw new Exception("User Already Exist");
		else if (this.user_DAL.insertNewUser(username, this.HashToSHA256(password)))
			return true;
		return false;
	}
	public boolean isUserExist(String username) throws Exception {
		return this.user_DAL.isUserExist(username);
	}
	public boolean validatePin(String username, String password)
			throws Exception {
		return this.user_DAL.isUsernameMatchPassword(username, this.HashToSHA256(password));
	}
	public String HashToSHA256(String password) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		String hex = (new HexBinaryAdapter()).marshal(md.digest(password
				.getBytes()));
		return hex;
	}
}
