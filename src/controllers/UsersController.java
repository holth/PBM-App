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
				
				try {
					User_BLL user = new User_BLL();
					
					if(username.equals("") || password.equals("")) { // empty username or password?
						
						JOptionPane.showMessageDialog(null, "Username or password cannot be empty.");
						return;
						
					}

					if(user.isUserExist(username)) { // user in DB?
						
						if(user.validatePin(username, password)) { // user with valid password?
							
							usersFrame.dispose();
							System.out.print("Logged in as " + username);
							
							ExpensesController expenses = new ExpensesController(username); // load expenses controller
							expenses.viewExpenses();
							
						} else {
							JOptionPane.showMessageDialog(null, "Wrong password");
						}
					} else {
						JOptionPane.showMessageDialog(null, "This username does not exist!");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				*/
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
		signupFrame.signup(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					User_BLL user = new User_BLL();
					// Validate presence of username and password
					if(signupFrame.getUsername().equals("") || signupFrame.getPwd().equals("") || signupFrame.getPwdConfirmation().equals("")) {
						JOptionPane.showMessageDialog(null, "Username or passwords cannot be empty.");
						return;
					}
					

					// Validate match between password and confirmation
					if(!signupFrame.getPwd().equals(signupFrame.getPwdConfirmation())) {
						JOptionPane.showMessageDialog(null, "Passwords doest not match.");
						return;
					}
					
					// Validate existence of user in DB
					if(user.isUserExist(signupFrame.getUsername())){
						JOptionPane.showMessageDialog(null, "This username alreay exist!");
						return;
					} else {
						user.createUser(signupFrame.getUsername(), signupFrame.getPwd());
						signupFrame.getFrame().dispose();
						System.out.println(signupFrame.getUsername() + " is created!");
						JOptionPane.showMessageDialog(null, signupFrame.getUsername() + " is created!");
						
						
						login();
						return;
					}
					
				} catch(Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		*/
		
		/**
		 * On click of cancel button
		 * load login frame
		signupFrame.cancel(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				signupFrame.getFrame().dispose();
				System.out.println("Log in:");
				login();
			}
		});
		
		 */
	}
	
}
