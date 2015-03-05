package views;

import java.awt.*;

import javax.swing.*;

public class UsersFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JPanel mainPanel;

	public UsersFrame() {

		this.getContentPane().setBackground(new Color(242, 242, 242));
		this.setBounds(380, 80, 400, 380);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setTitle("Personal Budget Manager");
		
		mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 600, 400);
		this.getContentPane().add(mainPanel);
		
	}
}
