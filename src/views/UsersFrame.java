package views;

import java.awt.*;

import javax.swing.*;

public class UsersFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel mainPanel;
	private UsersLoginPanel loginPanel = new UsersLoginPanel();
	private UsersSignupPanel signupPanel = new UsersSignupPanel();
	
	private CardLayout cLayout = new CardLayout();
	
	/**
	 * Constructor
	 */
	public UsersFrame() {

		this.getContentPane().setBackground(new Color(242, 242, 242));
		this.setBounds(380, 80, 400, 380);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setTitle("Personal Budget Manager");
		
		mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 600, 400);
		this.getContentPane().add(mainPanel);
		
		mainPanel.setLayout(cLayout); 			// set main panel as container
		mainPanel.add(loginPanel, "login");	
		mainPanel.add(signupPanel, "signup");
		
		cLayout.show(mainPanel, "login");		// show login by default
	}
	
	/// Getter methods ///
	
	public CardLayout getLayout() {
		return cLayout;
	}
	
	public UsersLoginPanel getLoginPanel() {
		return loginPanel;
	}
	
	public UsersSignupPanel getSignupPanel() {
		return signupPanel;
	}
	
	/// Setter methods ///
	
	public void showSignupPanel() {
		signupPanel.clearAllFields();
		cLayout.show(mainPanel, "signup");
	}
	
	public void showLoginPanel() {
		loginPanel.clearAllFields();
		cLayout.show(mainPanel, "login");
	}
	
	
}
