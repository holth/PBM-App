package views;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class UsersLoginPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField txtFieldName;
	private JPasswordField pwdFieldPwd;
	private JButton btnLogin;
	private JButton btnShowSignup;
	
	public UsersLoginPanel() {
		
		this.setLayout(null);
		
		JLabel lblWelcome = new JLabel("Log in");
		lblWelcome.setFont(new Font("Tacoma", Font.BOLD, 16));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(0, 20, 400, 48);
		this.add(lblWelcome);
		
		// Label & fields for user name
		JLabel lblName = new JLabel("User name:");
		lblName.setFont(new Font("Tacoma", 0, 12));
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(0, 88, 120, 32);
		this.add(lblName);
		
		txtFieldName = new JTextField();
		txtFieldName.setFont(new Font("Tacoma", 0, 13));
		txtFieldName.setBounds(130, 88, 220, 32);
		txtFieldName.setColumns(10);
		this.add(txtFieldName);
		// end...
		
		// Label & field for password
		JLabel lblPW = new JLabel("Password:");
		lblPW.setFont(new Font("Tacoma", 0, 12));
		lblPW.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPW.setBounds(0, 130, 120, 32);
		this.add(lblPW);
		
		pwdFieldPwd = new JPasswordField();
		pwdFieldPwd.setFont(new Font("Tacoma", 0, 13));
		pwdFieldPwd.setHorizontalAlignment(SwingConstants.LEFT);
		pwdFieldPwd.setBounds(130, 130, 220, 32);
		pwdFieldPwd.setColumns(10);
		this.add(pwdFieldPwd);
		// end...
		
		// Button for login
		btnLogin = new JButton("Log in");
		btnLogin.setFont(new Font("Tacoma", 0, 13));
		btnLogin.setBounds(130, 182, 220, 32);
		this.add(btnLogin);
		// end...
		
		// Divider
		final JSeparator separator = new JSeparator();
		separator.setBounds(20, 238, 360, 12);
		this.add(separator);
		// end..
		
		// Label & button for sign up
		final JLabel lblInfo = new JLabel("New User?");
		lblInfo.setFont(new Font("Tacoma", 0, 12));
		lblInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInfo.setBounds(0, 270, 120, 32);
		this.add(lblInfo);
		
		btnShowSignup = new JButton("Sign up now!");
		btnShowSignup.setFont(new Font("Tacoma", 0, 13));
		btnShowSignup.setBounds(130, 270, 220, 32);
		this.add(btnShowSignup);
		// end...
		
	}
	
	/// Listener methods ///
	
	public void showSignup(ActionListener listener) {
		btnShowSignup.addActionListener(listener);
	}
	
	public void login(ActionListener listener) {
		btnLogin.addActionListener(listener);
	}
	
	public void clearAllFields()
	{
		txtFieldName.setText("");
		pwdFieldPwd.setText("");
	}
	
	//*************************************************************************************************
	//**************Getter and Setter methods for login and signup*************************************
	//*************************************************************************************************

	
	/**
	 * Get User Name
	 * @return String
	 */
	public String getUsername(){
		return txtFieldName.getText();
	}

	/**
	 * Get the Password
	 * @return char[]
	 */
	public char[] getPassword(){
		return pwdFieldPwd.getPassword();
	}
	
}
