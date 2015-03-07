package controllers;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import views.*;
import models.*;

public class UsersController {
	
	private UsersFrame usersFrame;
	private UsersLoginPanel loginPanel;
	private UsersSignupPanel signupPanel;
	private CardLayout cLayout = new CardLayout();
	
	public UsersController(){
		
		usersFrame = new UsersFrame();				// load frame
		usersFrame.mainPanel.setLayout(cLayout);	// set layout to CardLayout
		
		loginPanel = new UsersLoginPanel();		// load login
		signupPanel = new UsersSignupPanel();	// load	signup
		
		usersFrame.mainPanel.add(loginPanel, "login");		// add login to layout
		usersFrame.mainPanel.add(signupPanel, "signup");	// add signup to layout
	}
	
	public void login() {

		cLayout.show(usersFrame.mainPanel, "login"); // show login
		
		/// Listener methods ///
		
		loginPanel.showSignup(new ActionListener() { // on-click of sign up
			public void actionPerformed(ActionEvent e) {
				signup();
				return;
			}
		});
		
		loginPanel.login(new ActionListener() { // on-click of log in
			public void actionPerformed(ActionEvent e) {
				
<<<<<<< HEAD
				/*
				 * Delete when testing done!
				 */
				usersFrame.dispose();
				ExpensesController expenses = new ExpensesController("AppDemo"); // load expenses controller
				expenses.viewExpenses();
				return;
				
				/* hiddent to testing
				 * 
				String username = loginPanel.txtFieldName.getText();
				String password = String.valueOf(loginPanel.pwdFieldPwd.getPassword());
=======
				String username = loginPanel.getUsername();
				String password = String.valueOf((loginPanel.getPassword()));
>>>>>>> origin/qicheng
				
				try {
					User_BLL user = new User_BLL();
					
					if(username.equals("") || password.equals("")) { // empty username or password?
						
						errormessages("Username or password cannot be empty.");
						return;
						
					}

					if(!user.isUserExist(username)) { // user in DB?
						errormessages("This username does not exist!");
						return;
					}
					
						
					else if(!user.validatePin(username, password)) { // user with valid password?
						errormessages("Wrong password");
						return;
					}
					else{

							usersFrame.dispose();
							System.out.print("Logged in as " + username);
							
							ExpensesController expenses = new ExpensesController(username); // load expenses controller
							expenses.viewExpenses();
						
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
	}

	public void signup() {
		
		cLayout.show(usersFrame.mainPanel, "signup");
		
		signupPanel.cancelSignup(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
				return;
			}
		});
		
		// final SignupFrame signupFrame = new SignupFrame();
		
		/**
		 * On click of signup button
		 * create new user, load login frame
		 * */
		signupPanel.signup(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					User_BLL user = new User_BLL();
					String username = signupPanel.getUsername();
					String password = String.valueOf((signupPanel.getPwd()));
					String pwdConfirm = String.valueOf(signupPanel.getPwdConfirm());
					
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
					}
					else {
						System.out.println(username + " is created!");
						JOptionPane.showMessageDialog(null, username + " is created!");
						
						login();
					}
					
				} catch(Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		/**
		 * On click of cancel button
		 * load login frame
		 * */
		signupPanel.cancelSignup(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Log in:");
				login();
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
