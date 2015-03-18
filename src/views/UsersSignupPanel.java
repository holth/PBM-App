package views;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class UsersSignupPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField txtFieldName;
	private JPasswordField pwdFieldPwd;
	private JPasswordField pwdFieldPwdConfirm;
	private JButton btnSignup;
	private JButton btnCancelSignup;
	
	public UsersSignupPanel() {
		
		this.setLayout(null);
		
		JLabel lblWelcome = new JLabel("Sign up");
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
		
		// Label & field for password confirmation
		final JLabel lblPWConfirm = new JLabel("Confirm:");
		lblPWConfirm.setFont(new Font("Tacoma", 0, 12));
		lblPWConfirm.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPWConfirm.setBounds(0, 172, 120, 32);
		this.add(lblPWConfirm);
				
		pwdFieldPwdConfirm = new JPasswordField();
		pwdFieldPwdConfirm.setFont(new Font("Tacoma", 0, 13));
		pwdFieldPwdConfirm.setHorizontalAlignment(SwingConstants.LEFT);
		pwdFieldPwdConfirm.setBounds(130, 172, 220, 32);
		pwdFieldPwdConfirm.setColumns(10);
		this.add(pwdFieldPwdConfirm);
		// end...
		
		// Button for sign up
		btnSignup = new JButton("Sign up");
		btnSignup.setFont(new Font("Tacoma", 0, 13));
		btnSignup.setBounds(130, 224, 110, 32);
		this.add(btnSignup);
		
		btnCancelSignup = new JButton("Cancel");
		btnCancelSignup.setBounds(240, 224, 110, 32);
		this.add(btnCancelSignup);
		// end...
	}
	
	/// Listener methods ///

	public void cancelSignup(ActionListener listener) {
		btnCancelSignup.addActionListener(listener);
	}

	public void signup(ActionListener listener) {
		btnSignup.addActionListener(listener);
	}
	
	public void clearAllFields()
	{
		txtFieldName.setText("");
		pwdFieldPwd.setText("");
		pwdFieldPwdConfirm.setText("");
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
	public char[] getPwd(){
		return pwdFieldPwd.getPassword();
	}
	
	/**
	 * Get the Password confirmation
	 * @return char[]
	 */
	public char[] getPwdConfirm(){
		return pwdFieldPwdConfirm.getPassword();
	}
}
