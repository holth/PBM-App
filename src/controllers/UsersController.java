package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import views.*;
import models.*;

public class UsersController {
	
	private UsersFrame usersFrame;
	private UsersLoginPanel loginPanel;
	private UsersSignupPanel signupPanel;
	
	/**
	 * Constructor
	 * @param usersFrame
	 */
	public UsersController(UsersFrame usersFrame){
		
		this.usersFrame = usersFrame;
		this.loginPanel = this.usersFrame.getLoginPanel();
		this.signupPanel = this.usersFrame.getSignupPanel();
		
		this.listener();
	}
	
	/**
	 * Validate user information and allow/disallow access to expenses
	 */
	public void login(String username, String password) {
		
		try {
			User_BLL user = new User_BLL();
			
			if(username.equals("") || password.equals("")) { // empty username or password?
				errormessages("Username or password cannot be empty.");
				return;
			}

			else if(!user.isUserExist(username)) { // user in DB?
				errormessages("This username does not exist!");
				return;
			}
			
			if(!user.validatePin(username, password)) { // user with valid password?
				errormessages("Wrong password");
				return;
			} else {
					usersFrame.dispose();
					System.out.println("Logged in as " + username + "!");

					System.out.println("View expenses:");
					
					ExpensesFrame expensesFrame = new ExpensesFrame(username);
					new ExpensesController(expensesFrame);
					
					expensesFrame.setVisible(true);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	/**
	 * Show sign up form
	 */
	public void showSignup() {
		
		System.out.println("Sign up:");
		usersFrame.showSignupPanel();
		
	}
	
	/**
	 * Create user in DB
	 */
	public void signup(String username, String password, String pwdConfirm) {
		
		try {
			User_BLL user = new User_BLL();
			
			// Validate presence of username and password
			if(username.equals("") || password.equals("") || pwdConfirm.equals(""))
			{
				errormessages("ERROR: Signup fields are empty");
				return;
			}
			
			// Validate existence of user in DB
			else if(user.isUserExist(username)== true)
			{
				errormessages("ERROR: User name already exists!");
				return;
			}
			
			// Validate match between password and confirmation
			else if(!password.equals(pwdConfirm))
			{
				errormessages("ERROR: Passaword does not match!");
				return;
			}
			
			//user account creation failure
			else if(user.createUser(username, password) != true)
			{
				errormessages("ERROR: Can't create User");
				return;
			} else {
				
				System.out.println(username + " is created!");
				JOptionPane.showMessageDialog(null, username + " is created!");
				
				System.out.println("Log in:");
				
				usersFrame.showLoginPanel();
			}
			
		} catch(Exception e1) {
			e1.printStackTrace();
		}
		
	}
	
	/**
	 * Cancel the sign up process, return to login
	 */
	public void cancelSignup() {
		
		System.out.println("Log in:");
		usersFrame.showLoginPanel();
		
	}
	
	/**
	 * Listen to buttons
	 */
	private void listener() {
		
		loginPanel.login(new ActionListener() { // on-click of 'login'
			public void actionPerformed(ActionEvent e) {
				String username = loginPanel.getUsername();
				String password = String.valueOf(loginPanel.getPassword());
				login(username, password);
			}
		});
		
		loginPanel.showSignup(new ActionListener() { // on-click of 'sign up now'
			public void actionPerformed(ActionEvent e) {
				showSignup();
			}
		});
		
		signupPanel.signup(new ActionListener() { // on-click of 'sign up now'
			public void actionPerformed(ActionEvent e) {
				String username = signupPanel.getUsername();
				String password = String.valueOf((signupPanel.getPwd()));
				String pwdConfirm = String.valueOf(signupPanel.getPwdConfirm());
				signup(username, password, pwdConfirm);
			}
		});
		
		signupPanel.cancelSignup(new ActionListener() { // on-click of 'sign up now'
			public void actionPerformed(ActionEvent e) {
				cancelSignup();
			}
		});
		
	}
	
	/**
	 * Error Message Dialog
	 * @param errormessage
	 */
	private boolean errormessages(String errormessage)
	{
		JOptionPane.showMessageDialog(null, 
				errormessage, "ERROR", JOptionPane.ERROR_MESSAGE);
		return false;
	}
	
}

